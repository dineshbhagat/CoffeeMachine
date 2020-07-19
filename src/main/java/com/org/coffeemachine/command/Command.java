package com.org.coffeemachine.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Command {
    HELP("help", 0, "to get list of commands"),
    QUIT("quit", 0, "to exit from the application"),
    ORDER("order", 1, "to order beverages, enter beverage name as <beverage1> [<beverage2>, ...] like:\n order hot_coffee green_tea black_tea hot_tea "),
    UNKNOWN("unknown", 0, ""),
    RESET("reset", 0, "to reset machine to initial setting");

    private final String value;
    private final int minArgumentSize;
    private final List<String> arguments;
    private final String helpString;

    Command(String value, int minArgumentSize, String helpString) {
        this.value = value;
        this.minArgumentSize = minArgumentSize;
        this.helpString = helpString;
        arguments = new ArrayList<>();
    }

    public static Map<String, Command> lookup = new HashMap<>();

    static {
        for (Command value : Command.values()) {
            lookup.put(value.value, value);
        }
    }

    public static Command getCommand(String valueStr) {
        return lookup.getOrDefault(valueStr, UNKNOWN);
    }

    public String getValue() {
        return value;
    }

    public int getMinArgumentSize() {
        return minArgumentSize;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public String getHelpString() {
        return helpString;
    }

    public static Map<String, Command> getLookup() {
        lookup.remove(UNKNOWN.value);//for internal use
        return lookup;
    }
}
