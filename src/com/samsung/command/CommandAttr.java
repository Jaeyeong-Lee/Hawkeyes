package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;

public class CommandAttr {

    public static String command;
    public static CommandOption option;

    private static String optionString1;
    private static String optionString2;
    private static String optionString3;
    private static String searchColumn;
    private static String searchCondition;
    private static String modifyColumn;
    private static String modifyCondition;

    public static void setAttrByLine(final String line) {
        parseLine(line.split(ConstCommand.commaDelimiter));

        option = new CommandOption(new SearchOption(searchColumn, searchCondition),
                new SearchOption(modifyColumn, modifyCondition),
                optionString2.replace("-", "").trim(),
                "-p".equals(optionString1));
    }

    private static void parseLine(final String[] tokens) {
        try {
            command = tokens[0];
            optionString1 = tokens[1];
            optionString2 = tokens[2];
            optionString3 = tokens[3];
            searchColumn = tokens[4];
            searchCondition = tokens[5];
            modifyColumn = tokens[6];
            modifyCondition = tokens[7];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}