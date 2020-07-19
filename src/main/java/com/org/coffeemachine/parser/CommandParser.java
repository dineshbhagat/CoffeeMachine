package com.org.coffeemachine.parser;

import com.org.coffeemachine.command.Command;

import static com.org.coffeemachine.constant.Constants.COMMAND_DELIMITER;

public class CommandParser {
    public static Command parse(String commandStr) {
        String[] str = commandStr.trim().split(COMMAND_DELIMITER);
        Command command = Command.getCommand(str[0]);
        int requiredArgumentSize = command.getMinArgumentSize();
        if (requiredArgumentSize == 0) {
            return command;
        }
        int actualArgumentsSize = str.length;
        if (requiredArgumentSize > actualArgumentsSize - 1) {
            return Command.UNKNOWN;
        }
        for (int i = 1; i < str.length; i++) {
            command.getArguments().add(str[i]);
        }
        return command;
    }
}
