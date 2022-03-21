package com.samsung.command;

import com.samsung.database.EmployeeDAO;
import com.samsung.database.PersistentDAO;
import com.samsung.option.CommandOption;

public abstract class Command<T> {

    protected CommandOption commandOption;
    protected String line;

    public Command() {

    }

    public Command(String line, CommandOption commandOption) {
        this.commandOption = commandOption;
        this.line = line;
    }

    public abstract T execute();

    EmployeeDAO employeeDAO;

}
