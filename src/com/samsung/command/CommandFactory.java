package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.constants.ConstEmployee;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;
import java.util.Set;


public class CommandFactory {

    public Command getCommand(String line) {
        try {
            String[] commandToken = line.split(",");

            boolean isPrint = false;
            String optionCode = null;
            SearchOption searchOption = null;
            SearchOption modifyOption = null;

            if (commandToken.length >= 2) {
                isPrint = ("-p".equals(commandToken[1]));
            }
            if (commandToken.length >= 3) {
                optionCode = commandToken[2].replace(ConstEmployee.hyphenDelimiter, "").trim();
            }
            if (commandToken.length >= 5) {
                searchOption = new SearchOption(commandToken[4], commandToken[5]);
            }
            if (commandToken.length >= 7) {
                modifyOption = new SearchOption(commandToken[6], commandToken[7]);
            }

            switch (commandToken[0]) {

                case ConstCommand.add:
                    return new AddCommand(line);
                case ConstCommand.search:
                    return new SearchCommand(
                            new CommandOption(searchOption, optionCode, isPrint));
                case ConstCommand.modify:
                    return new ModifyCommand(
                            new CommandOption(searchOption, modifyOption, optionCode, isPrint));
                case ConstCommand.delete:
                    return new DeleteCommand(
                            new CommandOption(searchOption, optionCode, isPrint));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new Command<Set<Employee>>() {
            @Override
            public Set<Employee> execute() {
                return null;
            }
        };
    }
}
