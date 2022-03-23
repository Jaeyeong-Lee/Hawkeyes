package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.employee.Employee;
import java.util.HashSet;
import java.util.Set;

public class AddCommand extends Command<Set<Employee>> {

    private String commandLine;

    public AddCommand() {
    }

    public  AddCommand(String line) {
        commandLine = line;
    }

    @Override
    public Set<Employee> execute() {
        employeeDAO.add(new Employee(commandLine.split(ConstCommand.commaDelimiter)));
        return new HashSet<>();
    }

    @Override
    public String toString() {
        return ConstCommand.add;
    }
}