package com.org.coffeemachine.constant;

public class Constants {
    private Constants() {
    }

    public static final String DEFAULT_SETTING_FILE = "input.json";

    public static final String COMMAND_DELIMITER = "\\s+";
    public static final String SPACE = " ";
    public static final String NEW_LINE = "\n";
    public static final String COLON = ":";
    public static final String HYPHEN = "- ";
    public static final String PARAMS_STR = "<> mandatory parameters and [] optional parameters " + NEW_LINE;

    // messages
    public static final String FOLLOWING_BEVERAGES_ARE_AVAILABLE = "Following beverages are available : ";
    public static final String BYE = "Bye!";
    public static final String INVALID_COMMAND = "Invalid command";
    public static final String COFFEE_MACHINE = "Coffee Machine";
    public static final String TYPE_HELP_TO_FIND_OUT_VARIOUS_SUPPORTED_COMMANDS = "Type 'help' to find out various supported commands";
    public static final String PREPARED = " is prepared";
    public static final String CAN_NOT_BE_PREPARED = " cannot be prepared because ";
    public static final String NOT_AVAILABLE = " is not available";

    //Ingredients
    public static final String GINGER_SYRUP = "ginger_syrup";
    public static final String HOT_WATER = "hot_water";
    public static final String HOT_MILK = "hot_milk";
    public static final String SUGAR_SYRUP = "sugar_syrup";
    public static final String TEA_LEAVES_SYRUP = "tea_leaves_syrup";
    public static final String GREEN_MIXTURE = "green_mixture";

    //Beverages
    public static final String HOT_TEA = "hot_tea";
    public static final String HOT_COFFEE = "hot_coffee";
    public static final String BLACK_TEA = "black_tea";
    public static final String GREEN_TEA = "green_tea";
}
