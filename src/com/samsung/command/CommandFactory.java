package com.samsung.command;

import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;


public class CommandFactory<T> {

    public Command getCommand(String line) {

        String[] commandToken = line.split(",");

        boolean isPrint = false;
        String optionCode = null;
        SearchOption searchOption = null;
        SearchOption modifyOption = null;

        if (commandToken.length >= 2)
            isPrint = ("-p".equals(commandToken[1])? true : false);
        if (commandToken.length >= 3)
            optionCode = commandToken[2].replace("-","").trim();
        if (commandToken.length >= 5)
            searchOption = new SearchOption(commandToken[4], commandToken[5]);
        if (commandToken.length >= 7)
            modifyOption = new SearchOption(commandToken[6], commandToken[7]);

        switch (commandToken[0]) {

            case "ADD" :
                return new AddCommand<T>(line);
            case "SCH" :
                return new SearchCommand<T>(new CommandOption(searchOption, optionCode, isPrint));
            case "MOD" :
                return new ModifyCommand<T>(new CommandOption(searchOption, modifyOption, optionCode, isPrint));
            case "DEL" :
                return new DeleteCommand<T>(new CommandOption(searchOption, optionCode, isPrint));

        }

        return null;
        // TODO: Blank Set으로 return 필요
        /*
        return new Command<T>() {

            @Override
            public T execute() {
                return null;
            }
        };
        */


    }

}
