package com.samsung.command;

import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class DeleteCommand<E> extends Command<Set<Employee>> {

    public DeleteCommand() {};

    public DeleteCommand(CommandOption commandOption) {
        super(commandOption);
    }

    @Override
    public Set<Employee> execute() {
        return null;
    }

    @Override
    public String toString() {
        return "DEL";
    }
}
