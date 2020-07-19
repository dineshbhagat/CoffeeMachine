package com.org.coffeemachine.processor;

import com.org.coffeemachine.command.Command;
import com.org.coffeemachine.constant.Constants;
import com.org.coffeemachine.parser.CommandParser;
import com.org.coffeemachine.service.CoffeeMachineService;

import static com.org.coffeemachine.command.Command.UNKNOWN;

public class CommandProcessor {
    private final CoffeeMachineService coffeeMachineService;

    public CommandProcessor(CoffeeMachineService coffeeMachineService) {
        this.coffeeMachineService = coffeeMachineService;
    }

    public void process(String commandStr) {
        if (commandStr == null || "".equals(commandStr)) {
            return;
        }
        Command command = CommandParser.parse(commandStr);
        if (UNKNOWN.getValue().equals(command.getValue())) {
            System.out.println(Constants.INVALID_COMMAND);
            return;
        }
        switch (command) {
            case RESET:
                coffeeMachineService.resetMachine("");
                System.out.println("Machine reset success!");
                break;
            case ORDER:
                coffeeMachineService.order(command).forEach(System.out::println);
                break;
            case QUIT:
                coffeeMachineService.doNothingAndExit();
                break;
            default:
                coffeeMachineService.doNothingAndReturnHelpMenu();
        }
        coffeeMachineService.clearArguments(command);
    }
}
