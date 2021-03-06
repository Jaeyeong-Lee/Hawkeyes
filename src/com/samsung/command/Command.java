package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.database.EmployeeDAO;
import com.samsung.option.CommandOption;

public abstract class Command<T> {

    protected CommandOption commandOption;

    public Command() {
        employeeDAO = new EmployeeDAO();
    }

    public Command(CommandOption commandOption) {
        this.commandOption = commandOption;
        employeeDAO = new EmployeeDAO();
    }

    public abstract T execute();

    protected EmployeeDAO employeeDAO;

    public CommandOption getCommandOption() {
        return commandOption;
    }

    @Override
    public String toString() {
        return ConstCommand.command;
    }
}
