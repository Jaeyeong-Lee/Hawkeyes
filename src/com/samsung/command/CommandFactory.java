package com.samsung.command;

import com.samsung.employee.Employee;

public class CommandFactory<T> {

    public Command<T> getCommand(String commandName) throws Exception {

        switch (commandName) {
            /*
            case "ADD" :
                return new AddCommand<T>();
            case "SCH" :
                return new SearchCommand<T>();
            case "MOD" :
                return new ModifyCommand<T>();
            case "DEL" :
                return new DeleteCommand<T>();

             */
        }

        throw new Exception("ERROR::Invalid commandName");
    }

}
