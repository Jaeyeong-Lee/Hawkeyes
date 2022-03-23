package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;

public class CommandAttr {

    private String command;
    private CommandOption option;

    private String optionString1;
    private String optionString2;
    private String optionString3;
    private String searchColumn;
    private String searchCondition;
    private String modifyColumn;
    private String modifyCondition;

    public String getCommand() {
        return command;
    }

    public CommandOption getOption() {
        return option;
    }

    public void setAttrByLine(final String line) {
        parseLine(line.split(ConstCommand.commaDelimiter));

        if (ConstCommand.add.equals(command)) {
            return ;
        }

        option = new CommandOption(new SearchOption(searchColumn, searchCondition),
                new SearchOption(modifyColumn, modifyCondition),
                optionString2.replace("-", "").trim(),
                "-p".equals(optionString1));
    }

    private void parseLine(final String[] tokens) {

        command = tokens[0];

        if (ConstCommand.add.equals(command)) {
            return ;
        }

        optionString1 = tokens[1];
        optionString2 = tokens[2];
        optionString3 = tokens[3];
        searchColumn = tokens[4];
        searchCondition = tokens[5];

        if (tokens.length > 6) {
            modifyColumn = tokens[6];
            modifyCondition = tokens[7];
        }
    }
}