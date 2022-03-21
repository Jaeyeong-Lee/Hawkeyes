package com.samsung.command;

import com.samsung.database.EmployeeDAO;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.List;
import java.util.Set;

public class ModifyCommand<E> extends Command<Set<Employee>> {

    public ModifyCommand() {};

    public ModifyCommand(CommandOption commandOption) {
        super(commandOption);
        commandString = "MOD";
    }

    @Override
    public Set<Employee> execute() {
        return null;
    }

    @Override
    public String toString() {
        return "MOD";
    }
}
