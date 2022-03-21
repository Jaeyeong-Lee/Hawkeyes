package com.samsung.command;

import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class ModifyCommand<E> extends Command<Set<Employee>> {

    public ModifyCommand() {};

    public ModifyCommand(String line, CommandOption commandOption) {
        super(line, commandOption);
    }

    @Override
    public Set<Employee> execute() {
        return null;
    }
}
