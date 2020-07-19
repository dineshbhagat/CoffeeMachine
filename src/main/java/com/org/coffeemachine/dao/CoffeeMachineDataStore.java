package com.org.coffeemachine.dao;

import com.org.coffeemachine.dto.Machine;
import com.org.coffeemachine.entity.Beverage;
import com.org.coffeemachine.entity.Ingredient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public class CoffeeMachineDataStore {

    private static CoffeeMachineDataStore instance = null;
    private final DaoHelper daoHelper = new DaoHelper(this);

    public static synchronized CoffeeMachineDataStore getInstance() {
        if (instance == null) {
            synchronized (CoffeeMachineDataStore.class) {
                if (instance == null) {
                    instance = new CoffeeMachineDataStore();
                }
            }
        }
        return instance;
    }

    private final Map<Ingredient, Integer> totalQuantity = new ConcurrentHashMap<>();

    private final Map<String, Beverage> beverages = new ConcurrentHashMap<>();

    private ExecutorService executorService;

    /**
     * Transform input format to store format
     *
     * @param machine
     */
    public void storeMachine(Machine machine) {
        daoHelper.convertInputDtoToEntity(machine);
    }

    public Map<String, Beverage> getBeverages() {
        return beverages;
    }

    public Beverage getBeverage(String name) {
        return beverages.get(name);
    }

    public Map<Ingredient, Integer> getTotalQuantity() {
        return totalQuantity;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
