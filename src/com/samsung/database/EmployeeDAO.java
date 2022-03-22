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
            addIndex(employeeTable.getCareerLevelIndex(), CareerLevel.nullSaferToString(employee.getCareerLevel()), employee);
            addIndex(employeeTable.getCertiIndex(), Certi.nullSaferToString(employee.getCerti()), employee);
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
            result.addAll(searchByIndex(employeeTable.getEmployeeNumberIndex(), employee.getEmployeeNumber()));
            result.addAll(searchByIndex(employeeTable.getNameIndex(), employee.getName()));
            result.addAll(searchByIndex(employeeTable.getFirstNameIndex(), employee.getFirstName()));
            result.addAll(searchByIndex(employeeTable.getLastNameIndex(), employee.getLastName()));
            result.addAll(searchByIndex(employeeTable.getPhoneNumberIndex(), employee.getPhoneNumber()));
            result.addAll(searchByIndex(employeeTable.getMiddleDigitOfPhoneNumberIndex(), employee.getMiddleDigitOfPhoneNumber()));
            result.addAll(searchByIndex(employeeTable.getLast4DigitOfPhoneNumberIndex(), employee.getLast4DigitOfPhoneNumber()));
            result.addAll(searchByIndex(employeeTable.getBirthIndex(), employee.getBirthDay()));
            result.addAll(searchByIndex(employeeTable.getYearOfBirthIndex(), employee.getYearOfBirth()));
            result.addAll(searchByIndex(employeeTable.getMonthOfBirthIndex(), employee.getMonthOfBirth()));
            result.addAll(searchByIndex(employeeTable.getDayOfBirthIndex(), employee.getDayOfBirth()));
            result.addAll(searchByIndex(employeeTable.getCareerLevelIndex(), CareerLevel.nullSaferToString(employee.getCareerLevel())));
            result.addAll(searchByIndex(employeeTable.getCertiIndex(), Certi.nullSaferToString(employee.getCerti())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Set<Employee> searchByIndex(Map<String, Set<Employee>> index, String indexCondition) {
        if (indexCondition != null) {
            return index.getOrDefault(indexCondition, new HashSet<>());
        }
        return new HashSet<>();
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
                modifyIndex(employeeTable.getCareerLevelIndex(), CareerLevel.nullSaferToString(asIs.getCareerLevel()), CareerLevel.nullSaferToString(toBe.getCareerLevel()), asIs, toBe);
                modifyIndex(employeeTable.getCertiIndex(), Certi.nullSaferToString(asIs.getCerti()), Certi.nullSaferToString(toBe.getCerti()), asIs, toBe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asIsEmployees;
    }

    private void modifyIndex(Map<String, Set<Employee>> index, String asIsIndexCondition, String toBeIndexCondition, Employee asIs, Employee toBe) {
        if (index.containsKey(asIsIndexCondition)) {
            Set<Employee> modifiedMap = index.get(asIsIndexCondition);
            modifiedMap.remove(asIs);

            if (asIs.getEmployeeNumber().equals(toBeIndexCondition)) {
                modifiedMap.add(toBe); // key 가 변하지 않을 경우
            } else {
                index.computeIfAbsent(toBeIndexCondition,
                        useless -> new HashSet<>()).add(toBe);// key 가 변할 경우
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