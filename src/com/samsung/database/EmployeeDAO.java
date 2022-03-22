package com.samsung.database;

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

    private void addIndex(Map<String, Set<Employee>> index, String indexCondition, Employee employeeToAdd){
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

    private Set<Employee> searchByEmployeeNumber(Employee employee) {
        return employeeTable.getEmployeeNumberIndex().getOrDefault(employee.getEmployeeNumber(), new HashSet<>());
    }

    private Set<Employee> searchByName(Employee employee) {
        return employeeTable.getNameIndex().getOrDefault(employee.getName(), new HashSet<>());
    }

    private Set<Employee> searchByFirstName(Employee employee) {
        return employeeTable.getFirstNameIndex().getOrDefault(employee.getFirstName(), new HashSet<>());
    }

    private Set<Employee> searchByLastName(Employee employee) {
        return employeeTable.getLastNameIndex().getOrDefault(employee.getLastName(), new HashSet<>());
    }

    private Set<Employee> searchByPhoneNumber(Employee employee) {
        return employeeTable.getPhoneNumberIndex().getOrDefault(employee.getPhoneNumber(), new HashSet<>());
    }

    private Set<Employee> searchByMiddleDigitOfPhoneNumber(Employee employee) {
        return employeeTable.getMiddleDigitOfPhoneNumberIndex().getOrDefault(employee.getMiddleDigitOfPhoneNumber(), new HashSet<>());
    }

    private Set<Employee> searchByLast4DigitOfPhoneNumber(Employee employee) {
        return employeeTable.getLast4DigitOfPhoneNumberIndex().getOrDefault(employee.getLast4DigitOfPhoneNumber(), new HashSet<>());
    }

    private Set<Employee> searchByBirth(Employee employee) {
        return employeeTable.getBirthIndex().getOrDefault(employee.getBirthDay(), new HashSet<>());
    }

    private Set<Employee> searchByYearOfBirth(Employee employee) {
        return employeeTable.getYearOfBirthIndex().getOrDefault(employee.getYearOfBirth(), new HashSet<>());
    }

    private Set<Employee> searchByMonthOfBirth(Employee employee) {
        return employeeTable.getMonthOfBirthIndex().getOrDefault(employee.getMonthOfBirth(), new HashSet<>());
    }

    private Set<Employee> searchByDayOfBirth(Employee employee) {
        return employeeTable.getDayOfBirthIndex().getOrDefault(employee.getDayOfBirth(), new HashSet<>());
    }

    private Set<Employee> searchByCareerLevel(Employee employee) {
        return employeeTable.getCareerLevelIndex().getOrDefault(employee.getCareerLevel().toString(), new HashSet<>());
    }

    private Set<Employee> searchByCerti(Employee employee) {
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

    private void deleteEmptyKey(Set<Employee> employees) {
        for (Employee employeeToDelete : employees) {
            if (Utility.isSetNotNullAndEmpty(employeeTable.getEmployeeNumberIndex().get(employeeToDelete.getEmployeeNumber()))) {
                employeeTable.getEmployeeNumberIndex().remove(employeeToDelete.getEmployeeNumber());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getNameIndex().get(employeeToDelete.getName()))) {
                employeeTable.getNameIndex().remove(employeeToDelete.getName());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getFirstNameIndex().get(employeeToDelete.getFirstName()))) {
                employeeTable.getFirstNameIndex().remove(employeeToDelete.getFirstName());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getLastNameIndex().get(employeeToDelete.getLastName()))) {
                employeeTable.getLastNameIndex().remove(employeeToDelete.getLastName());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getPhoneNumberIndex().get(employeeToDelete.getPhoneNumber()))) {
                employeeTable.getPhoneNumberIndex().remove(employeeToDelete.getPhoneNumber());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getMiddleDigitOfPhoneNumberIndex().get(employeeToDelete.getMiddleDigitOfPhoneNumber()))) {
                employeeTable.getMiddleDigitOfPhoneNumberIndex().remove(employeeToDelete.getMiddleDigitOfPhoneNumber());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getLast4DigitOfPhoneNumberIndex().get(employeeToDelete.getLast4DigitOfPhoneNumber()))) {
                employeeTable.getLast4DigitOfPhoneNumberIndex().remove(employeeToDelete.getLast4DigitOfPhoneNumber());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getBirthIndex().get(employeeToDelete.getBirthDay()))) {
                employeeTable.getBirthIndex().remove(employeeToDelete.getBirthDay());
            }
            if (Utility.isSetNotNullAndEmpty((employeeTable.getYearOfBirthIndex().get(employeeToDelete.getYearOfBirth())))) {
                employeeTable.getYearOfBirthIndex().remove(employeeToDelete.getYearOfBirth());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getMonthOfBirthIndex().get(employeeToDelete.getMonthOfBirth()))) {
                employeeTable.getMonthOfBirthIndex().remove(employeeToDelete.getMonthOfBirth());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getDayOfBirthIndex().get(employeeToDelete.getDayOfBirth()))) {
                employeeTable.getDayOfBirthIndex().remove(employeeToDelete.getDayOfBirth());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getCareerLevelIndex().get(employeeToDelete.getCareerLevel().toString()))) {
                employeeTable.getCareerLevelIndex().remove(employeeToDelete.getCareerLevel().toString());
            }
            if (Utility.isSetNotNullAndEmpty(employeeTable.getCertiIndex().get(employeeToDelete.getCerti().toString()))) {
                employeeTable.getCertiIndex().remove(employeeToDelete.getCerti().toString());
            }
        }
    }

    private void deleteValue(Set<Employee> employees) {
        for (Employee employeeToDelete : employees) {
            employeeTable.getEmployeeNumberIndex().get(employeeToDelete.getEmployeeNumber())
                    .remove(employeeToDelete);
            employeeTable.getNameIndex().get(employeeToDelete.getName())
                    .remove(employeeToDelete);
            employeeTable.getFirstNameIndex().get(employeeToDelete.getFirstName())
                    .remove(employeeToDelete);
            employeeTable.getLastNameIndex().get(employeeToDelete.getLastName())
                    .remove(employeeToDelete);
            employeeTable.getPhoneNumberIndex().get(employeeToDelete.getPhoneNumber())
                    .remove(employeeToDelete);
            employeeTable.getMiddleDigitOfPhoneNumberIndex()
                    .get(employeeToDelete.getMiddleDigitOfPhoneNumber())
                    .remove(employeeToDelete);
            employeeTable.getLast4DigitOfPhoneNumberIndex()
                    .get(employeeToDelete.getLast4DigitOfPhoneNumber())
                    .remove(employeeToDelete);
            employeeTable.getBirthIndex().get(employeeToDelete.getBirthDay())
                    .remove(employeeToDelete);
            employeeTable.getYearOfBirthIndex().get(employeeToDelete.getYearOfBirth())
                    .remove(employeeToDelete);
            employeeTable.getMonthOfBirthIndex().get(employeeToDelete.getMonthOfBirth())
                    .remove(employeeToDelete);
            employeeTable.getDayOfBirthIndex().get(employeeToDelete.getDayOfBirth())
                    .remove(employeeToDelete);
            employeeTable.getCareerLevelIndex()
                    .get(employeeToDelete.getCareerLevel().toString()).remove(employeeToDelete);
            employeeTable.getCertiIndex().get(employeeToDelete.getCerti().toString())
                    .remove(employeeToDelete);
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
