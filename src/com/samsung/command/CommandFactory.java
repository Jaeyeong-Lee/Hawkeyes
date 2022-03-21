package com.samsung.command;

import com.samsung.database.EmployeeDAO;
import com.samsung.database.PersistentDAO;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;

public class CommandFactory<T> {

    public Command getCommand(String line) throws Exception {

        String[] commandToken = line.split(",");

        boolean isPrint = false;
        String optionCode = null;
        SearchOption searchOption = null;
        SearchOption modifyOption = null;

        if (commandToken.length >= 2)
            isPrint = ("-p".equals(commandToken[1])? true : false);
        if (commandToken.length >= 3)
            optionCode = commandToken[2];
        if (commandToken.length >= 4)
            searchOption = new SearchOption(commandToken[4], commandToken[5]);
        if (commandToken.length >= 7)
            modifyOption = new SearchOption(commandToken[6], commandToken[7]);

        switch (commandToken[0]) {

            case "ADD" :
                return new AddCommand<T>(line,null);
            case "SCH" :
                return new SearchCommand<T>(line, new CommandOption(searchOption, optionCode, isPrint));
            case "MOD" :
                return new ModifyCommand<T>(line, new CommandOption(searchOption, modifyOption, optionCode, isPrint));
            case "DEL" :
                return new DeleteCommand<T>(line, new CommandOption(searchOption, optionCode, isPrint));

        }

        throw new Exception("ERROR::Invalid commandName");
    }

}
