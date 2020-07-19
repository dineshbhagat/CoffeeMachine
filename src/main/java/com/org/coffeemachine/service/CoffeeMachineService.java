package com.org.coffeemachine.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coffeemachine.command.Command;
import com.org.coffeemachine.dao.CoffeeMachineDataStore;
import com.org.coffeemachine.dto.Input;
import com.org.coffeemachine.dto.Machine;
import com.org.coffeemachine.entity.Beverage;
import com.org.coffeemachine.entity.Ingredient;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.org.coffeemachine.constant.Constants.*;

public class CoffeeMachineService {

    private static final Logger LOG = Logger.getLogger(CoffeeMachineService.class.getName());
    private final ObjectMapper objectMapper;
    private final CoffeeMachineDataStore coffeeMachineDataStore;

    public CoffeeMachineService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.coffeeMachineDataStore = CoffeeMachineDataStore.getInstance();
    }

    // we can reset machine by multiple ways, here we have input file, hence assuming file way to reset it.
    public void resetMachine(String fileName) {

        fileName = ("".equals(fileName) || fileName == null) ? DEFAULT_SETTING_FILE : fileName;
        // we can provide diff paths as well.
        Optional<Machine> machineOpt = readInitialSettingsFromFile(fileName);

        if (!machineOpt.isPresent()) {
            LOG.severe("Unable to reset the machine");
            return;
        }
        Machine machine = machineOpt.get();
        if (machine.getTotalItemsQuantity() == null ||
                machine.getBeverages() == null ||
                machine.getOutlets() == null) {
            LOG.severe("Invalid initial setting for the machine");
            return;
        }
        coffeeMachineDataStore.storeMachine(machine);
    }

    private Optional<Machine> readInitialSettingsFromFile(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            fileName = DEFAULT_SETTING_FILE;
        }

        try {
            ClassLoader classLoader = getClass().getClassLoader();

            URL resource = classLoader.getResource(fileName);
            if (resource == null) {
                throw new IllegalArgumentException("file is not found!");
            }
            FileReader file = new FileReader(resource.getFile());
            Input input = objectMapper.readValue(file, Input.class);
            return Optional.of(input.getMachine());
        } catch (JsonParseException e) {
            LOG.severe("Unable to parse json from file at : " + fileName);
            return Optional.empty();
        } catch (JsonMappingException e) {
            LOG.severe("Unable to find json mapping from file at : " + fileName);
            return Optional.empty();
        } catch (IOException e) {
            LOG.severe("Unable to find or open file at : " + fileName);
            return Optional.empty();
        }
    }

    public Set<String> getAvailableBeverages() {
        return coffeeMachineDataStore.getBeverages().keySet();
    }

    public void doNothingAndExit() {
        ExecutorService service = coffeeMachineDataStore.getExecutorService();
        if (!service.isShutdown()) {
            service.shutdownNow().forEach(Runnable::run);
        }
        System.out.println(BYE);
        // In worst case(when executor service is not blocked)
        System.exit(0);
    }

    public void doNothingAndReturnHelpMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append(PARAMS_STR);
        Map<String, Command> map = Command.getLookup();
        for (Map.Entry<String, Command> en : map.entrySet()) {
            sb.append(HYPHEN)
                    .append(en.getKey())
                    .append(COLON)
                    .append(SPACE)
                    .append(en.getValue().getHelpString())
                    .append(NEW_LINE);
        }
        //enter all beverage list,
        sb.append(FOLLOWING_BEVERAGES_ARE_AVAILABLE).append(NEW_LINE);
        for (String beverage : getAvailableBeverages()) {
            sb.append(beverage).append(NEW_LINE);
        }
        System.out.println(sb.toString());
    }

    public Set<String> order(Command command) {
        ExecutorService service = coffeeMachineDataStore.getExecutorService();

        Map<Ingredient, Integer> total = coffeeMachineDataStore.getTotalQuantity();
        List<Beverage> l = command.getArguments()
                .stream()
                .map(coffeeMachineDataStore::getBeverage)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Callable<String>> futures = new ArrayList<>();
        for (Beverage beverage : l) {
            futures.add(() -> {
                // System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getThreadGroup());

                //in case current thread is not interrupted
                if (!Thread.currentThread().isInterrupted()) {
                    synchronized (total) {
                        //in case of rollback of order due to 1 or more ingredient not fulfill the order, should add all subtracted values
                        Map<Ingredient, Integer> forRollback = new ConcurrentHashMap<>();
                        StringBuffer sb = new StringBuffer();
                        for (Map.Entry<Ingredient, Integer> ingredientIntegerEntry : beverage.getIngredientQuantity().entrySet()) {

                            Integer qty = total.get(ingredientIntegerEntry.getKey());
                            // When inventory not available or inventory is not sufficient
                            if (qty == null || qty < ingredientIntegerEntry.getValue()) {
                                for (Map.Entry<Ingredient, Integer> entry : forRollback.entrySet()) {
                                    total.put(entry.getKey(), entry.getValue());
                                }
                                return sb.append(beverage.getName())
                                        .append(CAN_NOT_BE_PREPARED)
                                        .append(ingredientIntegerEntry.getKey().getName())
                                        .append(NOT_AVAILABLE)
                                        .toString();
                            }
                            forRollback.put(ingredientIntegerEntry.getKey(), qty);
                            total.put(ingredientIntegerEntry.getKey(), qty - ingredientIntegerEntry.getValue());
                        }
                        return sb.append(beverage.getName()).append(PREPARED).toString();
                    }
                }
                return "null";
            });
        }

        Set<String> set = new CopyOnWriteArraySet<>();
        try {
            service.invokeAll(futures).forEach(f -> {
                try {
                    String s = f.isDone() ? f.get() : "Not completed yet";
                    // System.out.println(s);
                    set.add(s);
                } catch (InterruptedException | ExecutionException e) {
                    LOG.severe("Thread interrupted " + Thread.currentThread().getName());
                }
            });
        } catch (InterruptedException e) {
            LOG.severe("Thread interrupted " + Thread.currentThread().getName());
        }
        clearArguments(command);
        return set;
    }

    /**
     * Should invoke at the end of each method which use command, to avoid side-effect of arguments
     *
     * @param command
     */
    public void clearArguments(Command command) {
        command.getArguments().clear();
    }
}

// order hot_coffee green_tea black_tea hot_tea