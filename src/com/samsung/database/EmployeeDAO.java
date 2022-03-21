package com.samsung.database;

import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;
import com.samsung.employee.EmployeeManager;

import java.util.*;

public class EmployeeDAO extends PersistentDAO<Employee> {
    private EmployeeTable employeeTable = EmployeeTable.getInstance();

    @Override
    public int add(Employee employee) {
        try {
            employeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getNameIndex().computeIfAbsent(employee.getName(), useless -> new HashSet<>()).add(employee);
            employeeTable.getFirstNameIndex().computeIfAbsent(employee.getFirstName(), useless -> new HashSet<>()).add(employee);
            employeeTable.getLastNameIndex().computeIfAbsent(employee.getLastName(), useless -> new HashSet<>()).add(employee);
            employeeTable.getPhoneNumberIndex().computeIfAbsent(employee.getPhoneNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getLast4DigitOfPhoneNumberIndex().computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getBirthIndex().computeIfAbsent(employee.getBirthDay(), useless -> new HashSet<>()).add(employee);
            employeeTable.getYearOfBirthIndex().computeIfAbsent(employee.getYearOfBirth(), useless -> new HashSet<>()).add(employee);
            employeeTable.getMonthOfBirthIndex().computeIfAbsent(employee.getMonthOfBirth(), useless -> new HashSet<>()).add(employee);
            employeeTable.getDayOfBirthIndex().computeIfAbsent(employee.getDayOfBirth(), useless -> new HashSet<>()).add(employee);
            employeeTable.getCareerLevelIndex().computeIfAbsent(employee.getCareerLevel().toString(), useless -> new HashSet<>()).add(employee);
            employeeTable.getCertiIndex().computeIfAbsent(employee.getCerti().toString(), useless -> new HashSet<>()).add(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Set<Employee> search(Employee employee) {
        Set<Employee> result = new HashSet<>();
        try {
            Optional.ofNullable(employee.getEmployeeNumber()).ifPresent(useless -> result.addAll(searchByEmployeeNumber(employee)));
            Optional.ofNullable(employee.getName()).ifPresent(useless -> result.addAll(searchByName(employee)));
            Optional.ofNullable(employee.getFirstName()).ifPresent(useless -> result.addAll(searchByFirstName(employee)));
            Optional.ofNullable(employee.getLastName()).ifPresent(useless -> result.addAll(searchByLastName(employee)));
            Optional.ofNullable(employee.getPhoneNumber()).ifPresent(useless -> result.addAll(searchByPhoneNumber(employee)));
            Optional.ofNullable(employee.getMiddleDigitOfPhoneNumber()).ifPresent(useless -> result.addAll(searchByMiddleDigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getLast4DigitOfPhoneNumber()).ifPresent(useless -> result.addAll(searchByLast4DigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getBirthDay()).ifPresent(useless -> result.addAll(searchByBirth(employee)));
            Optional.ofNullable(employee.getYearOfBirth()).ifPresent(useless -> result.addAll(searchByYearOfBirth(employee)));
            Optional.ofNullable(employee.getMonthOfBirth()).ifPresent(useless -> result.addAll(searchByMonthOfBirth(employee)));
            Optional.ofNullable(employee.getDayOfBirth()).ifPresent(useless -> result.addAll(searchByDayOfBirth(employee)));
            Optional.ofNullable(employee.getCareerLevel()).ifPresent(useless -> result.addAll(searchByCareerLevel(employee)));
            Optional.ofNullable(employee.getCerti())
                    .ifPresent(useless -> result
                            .addAll(searchByCerti(employee)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Employee> searchByEmployeeNumber(Employee employee) {
        return employeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByName(Employee employee) {
        return employeeTable.getNameIndex().computeIfAbsent(employee.getName(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByFirstName(Employee employee) {
        return employeeTable.getFirstNameIndex().computeIfAbsent(employee.getFirstName(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByLastName(Employee employee) {
        return employeeTable.getLastNameIndex().computeIfAbsent(employee.getLastName(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByPhoneNumber(Employee employee) {
        return employeeTable.getPhoneNumberIndex().computeIfAbsent(employee.getPhoneNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByMiddleDigitOfPhoneNumber(Employee employee) {
        return employeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByLast4DigitOfPhoneNumber(Employee employee) {
        return employeeTable.getLast4DigitOfPhoneNumberIndex().computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByBirth(Employee employee) {
        return employeeTable.getBirthIndex().computeIfAbsent(employee.getBirthDay(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByYearOfBirth(Employee employee) {
        return employeeTable.getYearOfBirthIndex().computeIfAbsent(employee.getYearOfBirth(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByMonthOfBirth(Employee employee) {
        return employeeTable.getMonthOfBirthIndex().computeIfAbsent(employee.getMonthOfBirth(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByDayOfBirth(Employee employee) {
        return employeeTable.getDayOfBirthIndex().computeIfAbsent(employee.getDayOfBirth(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByCareerLevel(Employee employee) {
        return employeeTable.getCareerLevelIndex().computeIfAbsent(employee.getCareerLevel().toString(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByCerti(Employee employee) {
        return employeeTable.getCertiIndex().computeIfAbsent(employee.getCerti().toString(), useless -> new HashSet<>());
    }

    @Override
    public Set<Employee> delete(Employee employee) {
        Set<Employee> employees = null;
        try {
            employees = search(employee);
            for (Employee employeeToDelete : employees) {
                employeeTable.getEmployeeNumberIndex().remove(employeeToDelete.getEmployeeNumber());
                employeeTable.getNameIndex().remove(employeeToDelete.getName());
                employeeTable.getFirstNameIndex().remove(employeeToDelete.getFirstName());
                employeeTable.getLastNameIndex().remove(employeeToDelete.getLastName());
                employeeTable.getPhoneNumberIndex().remove(employeeToDelete.getPhoneNumber());
                employeeTable.getMiddleDigitOfPhoneNumberIndex().remove(employeeToDelete.getMiddleDigitOfPhoneNumber());
                employeeTable.getLast4DigitOfPhoneNumberIndex().remove(employeeToDelete.getLast4DigitOfPhoneNumber());
                employeeTable.getBirthIndex().remove(employeeToDelete.getBirthDay());
                employeeTable.getYearOfBirthIndex().remove(employeeToDelete.getYearOfBirth());
                employeeTable.getMonthOfBirthIndex().remove(employeeToDelete.getMonthOfBirth());
                employeeTable.getDayOfBirthIndex().remove(employeeToDelete.getDayOfBirth());
                employeeTable.getCareerLevelIndex().remove(employeeToDelete.getCareerLevel().toString());
                employeeTable.getCertiIndex().remove(employeeToDelete.getCerti().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Set<Employee> modify(Employee asIsEmployee, Employee toBeEmployee) {
        return null;
    }
}
