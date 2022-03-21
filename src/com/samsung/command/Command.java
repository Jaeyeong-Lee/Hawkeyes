package com.samsung.command;

import com.samsung.database.EmployeeDAO;
import com.samsung.database.PersistentDAO;
import com.samsung.option.CommandOption;

public abstract class Command<T> {

    protected CommandOption commandOption;

    public Command() {}

    public Command(CommandOption commandOption) {
        this.commandOption = commandOption;
        employeeDAO = new EmployeeDAO();

    }

    public abstract T execute();

    EmployeeDAO employeeDAO;

}
