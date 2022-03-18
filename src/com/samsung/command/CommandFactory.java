package com.samsung.command;

import com.samsung.employee.Employee;

public class CommandFactory<T> {

    public Command<Employee> getCommand(String commandName) throws Exception {

        switch (commandName) {
            case "ADD" :
                return new AddCommand<Employee>();
            case "SCH" :
                return new SearchCommand<Employee>();
            case "MOD" :
                return new ModifyCommand<Employee>();
            case "DEL" :
                return new DeleteCommand<Employee>();
        }

        throw new Exception("ERROR::Invalid commandName");
    }

}
