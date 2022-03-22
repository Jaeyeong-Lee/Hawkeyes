package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;


class CommandAttr {

    public static String command;
    public static CommandOption option;

    private static String option1;
    private static String option2;
    private static String option3;
    private static String searchColumn;
    private static String searchCondition;
    private static String modifyColumn;
    private static String modifyCondition;

    public static void setAttrByLine(final String line) {
        parseLine(line.split(ConstCommand.commaDelimiter));

        option = new CommandOption(new SearchOption(searchColumn, searchCondition),
                new SearchOption(modifyColumn, modifyCondition), option2.replace("-", "").trim(),
                "-p".equals(option1));
    }

    private static void parseLine(final String[] tokens) {
        try {
            command = tokens[0];
            option1 = tokens[1];
            option2 = tokens[2];
            option3 = tokens[3];
            searchColumn = tokens[4];
            searchCondition = tokens[5];
            modifyColumn = tokens[6];
            modifyCondition = tokens[7];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}

public class CommandFactory<T> {

    public Command getCommand(String line) {
        try {
            CommandAttr.setAttrByLine(line);

            switch (CommandAttr.command) {

                case "ADD":
                    return new AddCommand<T>(line);
                case "SCH":
                    return new SearchCommand<T>(CommandAttr.option);
                case "MOD":
                    return new ModifyCommand<T>(CommandAttr.option);
                case "DEL":
                    return new DeleteCommand<T>(CommandAttr.option);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new Command<T>() {
            @Override
            public T execute() {
                return null;
            }
        };
    }

}
