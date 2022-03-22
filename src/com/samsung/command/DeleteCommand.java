package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class DeleteCommand extends Command<Set<Employee>> {

    public DeleteCommand() {
    }

    public DeleteCommand(CommandOption commandOption) {
        super(commandOption);
    }

    @Override
    public Set<Employee> execute() {
        return employeeDAO.delete(commandOption.convertSearchOptionToEmployee());
    }

    @Override
    public String toString() {
        return ConstCommand.delete;
    }
}
