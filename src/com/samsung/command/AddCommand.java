package com.samsung.command;

import com.samsung.employee.Employee;

import java.util.Set;

public class AddCommand<T> extends Command<Set<Employee>> {

    private String commandLine;

    public AddCommand() {
    }

    public AddCommand(String line) {
        commandLine = line;
    }

    @Override
    public Set<Employee> execute() {
        employeeDAO.add(new Employee(commandLine.split(",")));
        return null; //TODO Null return 좋은 방법 확인
    }

    @Override
    public String toString() {
        return "ADD";
    }
}