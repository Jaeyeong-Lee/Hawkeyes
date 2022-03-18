package com.samsung.command;

import com.samsung.employee.Employee;

import java.util.List;
import java.util.Set;

public class AddCommand<E> extends Command<Set<Employee>>{

    @Override
    public Set<Employee> execute() {
        return null;
    }

}
