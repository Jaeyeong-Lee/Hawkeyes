package com.samsung.command;

import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class SearchCommand<T> extends Command<Set<Employee>> {

    public SearchCommand() {
    }

    public SearchCommand(CommandOption option) {
        super(option);
    }

    @Override
    public Set<Employee> execute() {
        return employeeDAO.search(commandOption.convertSearchOptionToEmployee());
    }

    @Override
    public String toString() {
        return "SCH";
    }
}
