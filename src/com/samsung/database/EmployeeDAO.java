package com.samsung.database;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;
import com.samsung.employee.EmployeeManager;
import com.samsung.util.Utility;

import java.util.*;

public class EmployeeDAO extends PersistentDAO<Employee> {

    private EmployeeTable employeeTable = EmployeeTable.getInstance();

    @Override
    public int add(Employee employee) {
        try {
            addIndex(employeeTable.getEmployeeNumberIndex(), employee.getEmployeeNumber(), employee);
            addIndex(employeeTable.getNameIndex(), employee.getName(), employee);
            addIndex(employeeTable.getFirstNameIndex(), employee.getFirstName(), employee);
            addIndex(employeeTable.getLastNameIndex(), employee.getLastName(), employee);
            addIndex(employeeTable.getPhoneNumberIndex(), employee.getPhoneNumber(), employee);
            addIndex(employeeTable.getMiddleDigitOfPhoneNumberIndex(), employee.getMiddleDigitOfPhoneNumber(), employee);
            addIndex(employeeTable.getLast4DigitOfPhoneNumberIndex(), employee.getLast4DigitOfPhoneNumber(), employee);
            addIndex(employeeTable.getBirthIndex(), employee.getBirthDay(), employee);
            addIndex(employeeTable.getYearOfBirthIndex(), employee.getYearOfBirth(), employee);
            addIndex(employeeTable.getMonthOfBirthIndex(), employee.getMonthOfBirth(), employee);
            addIndex(employeeTable.getDayOfBirthIndex(), employee.getDayOfBirth(), employee);
            addIndex(employeeTable.getCareerLevelIndex(), employee.getCareerLevel().toString(), employee);
            addIndex(employeeTable.getCertiIndex(), employee.getCerti().toString(), employee);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    private void addIndex(Map<String, Set<Employee>> index, String indexCondition, Employee employeeToAdd) {
        index.computeIfAbsent(indexCondition, useless -> new HashSet<>()).add(employeeToAdd);
    }

    @Override
    public Set<Employee> search(Employee employee) {
        Set<Employee> result = new HashSet<>();
        try {
            Optional.ofNullable(employee.getEmployeeNumber())
                    .ifPresent(useless -> result.addAll(searchByEmployeeNumber(employee)));
            Optional.ofNullable(employee.getName())
                    .ifPresent(useless -> result.addAll(searchByName(employee)));
            Optional.ofNullable(employee.getFirstName())
                    .ifPresent(useless -> result.addAll(searchByFirstName(employee)));
            Optional.ofNullable(employee.getLastName())
                    .ifPresent(useless -> result.addAll(searchByLastName(employee)));
            Optional.ofNullable(employee.getPhoneNumber())
                    .ifPresent(useless -> result.addAll(searchByPhoneNumber(employee)));
            Optional.ofNullable(employee.getMiddleDigitOfPhoneNumber()).ifPresent(
                    useless -> result.addAll(searchByMiddleDigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getLast4DigitOfPhoneNumber())
                    .ifPresent(useless -> result.addAll(searchByLast4DigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getBirthDay())
                    .ifPresent(useless -> result.addAll(searchByBirth(employee)));
            Optional.ofNullable(employee.getYearOfBirth())
                    .ifPresent(useless -> result.addAll(searchByYearOfBirth(employee)));
            Optional.ofNullable(employee.getMonthOfBirth())
                    .ifPresent(useless -> result.addAll(searchByMonthOfBirth(employee)));
            Optional.ofNullable(employee.getDayOfBirth())
                    .ifPresent(useless -> result.addAll(searchByDayOfBirth(employee)));
            Optional.ofNullable(employee.getCareerLevel())
                    .ifPresent(useless -> result.addAll(searchByCareerLevel(employee)));
            Optional.ofNullable(employee.getCerti())
                    .ifPresent(useless -> result.addAll(searchByCerti(employee)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Employee> searchByEmployeeNumber(Employee employee) {
        return employeeTable.getEmployeeNumberIndex().getOrDefault(employee.getEmployeeNumber(), new HashSet<>());
    }

    public Set<Employee> searchByName(Employee employee) {
        return employeeTable.getNameIndex().getOrDefault(employee.getName(), new HashSet<>());
    }

    public Set<Employee> searchByFirstName(Employee employee) {
        return employeeTable.getFirstNameIndex().getOrDefault(employee.getFirstName(), new HashSet<>());
    }

    public Set<Employee> searchByLastName(Employee employee) {
        return employeeTable.getLastNameIndex().getOrDefault(employee.getLastName(), new HashSet<>());
    }

    public Set<Employee> searchByPhoneNumber(Employee employee) {
        return employeeTable.getPhoneNumberIndex().getOrDefault(employee.getPhoneNumber(), new HashSet<>());
    }

    public Set<Employee> searchByMiddleDigitOfPhoneNumber(Employee employee) {
        return employeeTable.getMiddleDigitOfPhoneNumberIndex().getOrDefault(employee.getMiddleDigitOfPhoneNumber(), new HashSet<>());
    }

    public Set<Employee> searchByLast4DigitOfPhoneNumber(Employee employee) {
        return employeeTable.getLast4DigitOfPhoneNumberIndex().getOrDefault(employee.getLast4DigitOfPhoneNumber(), new HashSet<>());
    }

    public Set<Employee> searchByBirth(Employee employee) {
        return employeeTable.getBirthIndex().getOrDefault(employee.getBirthDay(), new HashSet<>());
    }

    public Set<Employee> searchByYearOfBirth(Employee employee) {
        return employeeTable.getYearOfBirthIndex().getOrDefault(employee.getYearOfBirth(), new HashSet<>());
    }

    public Set<Employee> searchByMonthOfBirth(Employee employee) {
        return employeeTable.getMonthOfBirthIndex().getOrDefault(employee.getMonthOfBirth(), new HashSet<>());
    }

    public Set<Employee> searchByDayOfBirth(Employee employee) {
        return employeeTable.getDayOfBirthIndex().getOrDefault(employee.getDayOfBirth(), new HashSet<>());
    }

    public Set<Employee> searchByCareerLevel(Employee employee) {
        return employeeTable.getCareerLevelIndex().getOrDefault(employee.getCareerLevel().toString(), new HashSet<>());
    }

    public Set<Employee> searchByCerti(Employee employee) {
        return employeeTable.getCertiIndex().getOrDefault(employee.getCerti().toString(), new HashSet<>());
    }

    @Override
    public Set<Employee> delete(Employee employee) {
        Set<Employee> employees = null;
        try {
            employees = search(employee);
            deleteValue(employees);
            deleteEmptyKey(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    private void deleteValue(Set<Employee> employees) {
        for (Employee employeeToDelete : employees) {
            deleteIndexValue(employeeTable.getEmployeeNumberIndex(), employeeToDelete.getEmployeeNumber(), employeeToDelete);
            deleteIndexValue(employeeTable.getNameIndex(), employeeToDelete.getName(), employeeToDelete);
            deleteIndexValue(employeeTable.getFirstNameIndex(), employeeToDelete.getFirstName(), employeeToDelete);
            deleteIndexValue(employeeTable.getLastNameIndex(), employeeToDelete.getLastName(), employeeToDelete);
            deleteIndexValue(employeeTable.getPhoneNumberIndex(), employeeToDelete.getPhoneNumber(), employeeToDelete);
            deleteIndexValue(employeeTable.getMiddleDigitOfPhoneNumberIndex(), employeeToDelete.getMiddleDigitOfPhoneNumber(), employeeToDelete);
            deleteIndexValue(employeeTable.getLast4DigitOfPhoneNumberIndex(), employeeToDelete.getLast4DigitOfPhoneNumber(), employeeToDelete);
            deleteIndexValue(employeeTable.getBirthIndex(), employeeToDelete.getBirthDay(), employeeToDelete);
            deleteIndexValue(employeeTable.getYearOfBirthIndex(), employeeToDelete.getYearOfBirth(), employeeToDelete);
            deleteIndexValue(employeeTable.getMonthOfBirthIndex(), employeeToDelete.getMonthOfBirth(), employeeToDelete);
            deleteIndexValue(employeeTable.getDayOfBirthIndex(), employeeToDelete.getDayOfBirth(), employeeToDelete);
            deleteIndexValue(employeeTable.getCareerLevelIndex(), CareerLevel.nullSaferToString(employeeToDelete.getCareerLevel()), employeeToDelete);
            deleteIndexValue(employeeTable.getCertiIndex(), Certi.nullSaferToString(employeeToDelete.getCerti()), employeeToDelete);
        }
    }

    private void deleteIndexValue(Map<String, Set<Employee>> index, String indexCondition, Employee employeeToDelete) {
        index.get(indexCondition).remove(employeeToDelete);
    }

    private void deleteEmptyKey(Set<Employee> employees) {
        for (Employee employeeToDelete : employees) {
            deleteEmptyKeyByIndex(employeeTable.getEmployeeNumberIndex(), employeeToDelete.getEmployeeNumber());
            deleteEmptyKeyByIndex(employeeTable.getNameIndex(), employeeToDelete.getName());
            deleteEmptyKeyByIndex(employeeTable.getFirstNameIndex(), employeeToDelete.getFirstName());
            deleteEmptyKeyByIndex(employeeTable.getLastNameIndex(), employeeToDelete.getLastName());
            deleteEmptyKeyByIndex(employeeTable.getPhoneNumberIndex(), employeeToDelete.getPhoneNumber());
            deleteEmptyKeyByIndex(employeeTable.getMiddleDigitOfPhoneNumberIndex(), employeeToDelete.getMiddleDigitOfPhoneNumber());
            deleteEmptyKeyByIndex(employeeTable.getLast4DigitOfPhoneNumberIndex(), employeeToDelete.getLast4DigitOfPhoneNumber());
            deleteEmptyKeyByIndex(employeeTable.getBirthIndex(), employeeToDelete.getBirthDay());
            deleteEmptyKeyByIndex(employeeTable.getYearOfBirthIndex(), employeeToDelete.getYearOfBirth());
            deleteEmptyKeyByIndex(employeeTable.getMonthOfBirthIndex(), employeeToDelete.getMonthOfBirth());
            deleteEmptyKeyByIndex(employeeTable.getDayOfBirthIndex(), employeeToDelete.getDayOfBirth());
            deleteEmptyKeyByIndex(employeeTable.getCareerLevelIndex(), CareerLevel.nullSaferToString(employeeToDelete.getCareerLevel()));
            deleteEmptyKeyByIndex(employeeTable.getCertiIndex(), Certi.nullSaferToString(employeeToDelete.getCerti()));
        }
    }

    private void deleteEmptyKeyByIndex(Map<String, Set<Employee>> index, String indexCondition) {
        if (Utility.isSetNotNullAndEmpty(index.get(indexCondition))) {
            index.remove(indexCondition);
        }
    }

    @Override
    public Set<Employee> modify(Employee asIsEmployee, Employee toBeEmployee) {
        Set<Employee> asIsEmployees = null;
        EmployeeManager employeeManager = new EmployeeManager();
        try {
            asIsEmployees = search(asIsEmployee);
            for (Employee asIs : asIsEmployees) {
                Employee toBe = employeeManager.overWrite(asIs, toBeEmployee);

                modifyIndex(employeeTable.getEmployeeNumberIndex(), asIs.getEmployeeNumber(), toBe.getEmployeeNumber(), asIs, toBe);
                modifyIndex(employeeTable.getNameIndex(), asIs.getName(), toBe.getName(), asIs, toBe);
                modifyIndex(employeeTable.getFirstNameIndex(), asIs.getFirstName(), toBe.getFirstName(), asIs, toBe);
                modifyIndex(employeeTable.getLastNameIndex(), asIs.getLastName(), toBe.getLastName(), asIs, toBe);
                modifyIndex(employeeTable.getPhoneNumberIndex(), asIs.getPhoneNumber(), toBe.getPhoneNumber(), asIs, toBe);
                modifyIndex(employeeTable.getMiddleDigitOfPhoneNumberIndex(), asIs.getMiddleDigitOfPhoneNumber(), toBe.getMiddleDigitOfPhoneNumber(), asIs, toBe);
                modifyIndex(employeeTable.getLast4DigitOfPhoneNumberIndex(), asIs.getLast4DigitOfPhoneNumber(), toBe.getLast4DigitOfPhoneNumber(), asIs, toBe);
                modifyIndex(employeeTable.getBirthIndex(), asIs.getBirthDay(), toBe.getBirthDay(), asIs, toBe);
                modifyIndex(employeeTable.getYearOfBirthIndex(), asIs.getYearOfBirth(), toBe.getYearOfBirth(), asIs, toBe);
                modifyIndex(employeeTable.getMonthOfBirthIndex(), asIs.getMonthOfBirth(), toBe.getMonthOfBirth(), asIs, toBe);
                modifyIndex(employeeTable.getDayOfBirthIndex(), asIs.getDayOfBirth(), toBe.getDayOfBirth(), asIs, toBe);
                modifyIndex(employeeTable.getCareerLevelIndex(), asIs.getCareerLevel().toString(), toBe.getCareerLevel().toString(), asIs, toBe);
                modifyIndex(employeeTable.getCertiIndex(), asIs.getCerti().toString(), toBe.getCerti().toString(), asIs, toBe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asIsEmployees;
    }

    private void modifyIndex(Map<String, Set<Employee>> index, String asIsIndexCondition, String toBeIndexCondition, Employee asIs, Employee toBe) {
        if (index.containsKey(asIsIndexCondition)) {
            Set<Employee> modifiedMap = index.get(asIsIndexCondition);
            if (modifiedMap.contains(asIs)) {
                modifiedMap.remove(asIs);
            }

            if (asIs.getEmployeeNumber().equals(toBeIndexCondition)) {
                modifiedMap.add(toBe); // key가 변하지 않을 경우
            } else {
                index.computeIfAbsent(toBeIndexCondition,
                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
            }

            if (modifiedMap.size() == 0) {
                index.remove(asIsIndexCondition);
            }
        }
    }

    public void deleteAll() {
        employeeTable.initialize();
    }
}