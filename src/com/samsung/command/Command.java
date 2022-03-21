package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.EmployeeDAO;
import com.samsung.database.PersistentDAO;
import com.samsung.employee.Employee;
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
    protected String commandString;

    @Override
    public String toString() {
        return "COM";
    }
  
    public CommandOption getCommandOption() {
        return this.commandOption;
    }

    public String getCommandString() {
        return this.commandString;

    }
}
