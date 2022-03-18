package com.samsung.command;

import com.samsung.database.EmployeeDAO;
import com.samsung.option.CommandOption;

public abstract class Command<T> {

    private CommandOption commandOption;
    public abstract T execute();

    EmployeeDAO employeeDAO;

}
