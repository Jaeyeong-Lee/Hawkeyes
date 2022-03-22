package com.samsung.command;

import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class ModifyCommand<T> extends Command<Set<Employee>> {

    public ModifyCommand() {
    }

    public ModifyCommand(CommandOption commandOption) {
        super(commandOption);
    }

    @Override
    public Set<Employee> execute() {
        return employeeDAO.modify(commandOption.convertSearchOptionToEmployee(), commandOption.convertModifyOptionToEmployee());
    }

    @Override
    public String toString() {
        return "MOD";
    }
}
