package com.org.coffeemachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coffeemachine.command.Command;
import com.org.coffeemachine.constant.Constants;
import com.org.coffeemachine.processor.CommandProcessor;
import com.org.coffeemachine.service.CoffeeMachineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoffeeMachineApplication {
    private static final Logger LOG = Logger.getLogger(CoffeeMachineApplication.class.getName());

    /**
     * Output 1
     * hot_tea is prepared
     * hot_coffee is prepared
     * green_tea cannot be prepared because green_mixture is not available
     * black_tea cannot be prepared because item hot_water is not sufficient
     * <p>
     * Or
     * <p>
     * Output 2
     * hot_tea is prepared
     * black_tea is prepared
     * green_tea cannot be prepared because green_mixture is not available
     * hot_coffee cannot be prepared because item hot_water is not sufficient
     * <p>
     * Or
     * <p>
     * Output 3
     * hot_coffee is prepared
     * black_tea is prepared
     * green_tea cannot be prepared because green_mixture is not available
     * hot_tea cannot be prepared because item hot_water is not sufficient
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Constants.COFFEE_MACHINE);
        System.out.println(Constants.TYPE_HELP_TO_FIND_OUT_VARIOUS_SUPPORTED_COMMANDS);

        ObjectMapper objectMapper = new ObjectMapper();
        CoffeeMachineService coffeeMachineService = new CoffeeMachineService(objectMapper);
        coffeeMachineService.resetMachine("");
        // Interactive console for user input
        String command = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                command = in.readLine().trim();
                CommandProcessor commandProcessor = new CommandProcessor(coffeeMachineService);
                commandProcessor.process(command);
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Error in processing command : " + command, e);
            }
        } while (!Command.QUIT.getValue().equalsIgnoreCase(command));
    }
}
