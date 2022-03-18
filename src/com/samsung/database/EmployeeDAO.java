package com.samsung.database;

import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.*;

public class EmployeeDAO extends PersistentDAO<Employee> {
    @Override
    public int add(Employee employee) {
        return -1;
    }

    @Override
    public Set<Employee> search(Employee employee) {
        return null;
    }

    @Override
    public Set<Employee> delete(Employee employee) {
        return null;
    }

    @Override
    public Set<Employee> modify(Employee asIsEmployee, Employee toBeEmployee) {
        return null;
    }
}
