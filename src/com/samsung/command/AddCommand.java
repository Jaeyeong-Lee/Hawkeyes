package com.samsung.command;

import com.samsung.employee.Employee;

import java.util.Set;

public class AddCommand<E> extends Command<Set<Employee>> {

    private String commandLine;

    public AddCommand() {
    }

    public AddCommand(String line) {
        commandLine = line;
    }

    @Override
    public Set<Employee> execute() {

        // TODO: Exception 추가 추후 고민
        if (employeeDAO.add(new Employee(commandLine.split(","))) != 0) {
            return null;
            // throw new Exception("ERROR:::Error ouucred while DAO.add()");
        }

        return null;
    }

    @Override
    public String toString() {
        return "ADD";
    }
}
