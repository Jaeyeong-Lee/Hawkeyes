package com.samsung.database;

import com.samsung.employee.Employee;

import java.util.*;

public class EmployeeDAO extends PersistentDAO<Employee> {
    @Override
    public int add(Employee employee) {
        return -1;
    }

    @Override
    public List<Employee> search(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> delete(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> modify(Employee asIsEmployee, Employee toBeEmployee) {
        return null;
    }
}
