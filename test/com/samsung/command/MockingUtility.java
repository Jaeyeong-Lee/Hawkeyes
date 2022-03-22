package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;

import java.util.*;

public class MockingUtility {

    public void mockAllIndexingTables(List<Employee> fakeEmployeeList,
            EmployeeTable testEmployeeTable) {

        for (Employee employee : fakeEmployeeList) {
            addIndex(testEmployeeTable.getEmployeeNumberIndex(), employee.getEmployeeNumber(), employee);
            addIndex(testEmployeeTable.getNameIndex(), employee.getName(), employee);
            addIndex(testEmployeeTable.getFirstNameIndex(), employee.getFirstName(), employee);
            addIndex(testEmployeeTable.getLastNameIndex(), employee.getLastName(), employee);
            addIndex(testEmployeeTable.getPhoneNumberIndex(), employee.getPhoneNumber(), employee);
            addIndex(testEmployeeTable.getMiddleDigitOfPhoneNumberIndex(), employee.getMiddleDigitOfPhoneNumber(), employee);
            addIndex(testEmployeeTable.getLast4DigitOfPhoneNumberIndex(), employee.getLast4DigitOfPhoneNumber(), employee);
            addIndex(testEmployeeTable.getBirthIndex(), employee.getBirthDay(), employee);
            addIndex(testEmployeeTable.getYearOfBirthIndex(), employee.getYearOfBirth(), employee);
            addIndex(testEmployeeTable.getMonthOfBirthIndex(), employee.getMonthOfBirth(), employee);
            addIndex(testEmployeeTable.getDayOfBirthIndex(), employee.getDayOfBirth(), employee);
            addIndex(testEmployeeTable.getCareerLevelIndex(), CareerLevel.nullSaferToString(employee.getCareerLevel()), employee);
            addIndex(testEmployeeTable.getCertiIndex(), Certi.nullSaferToString(employee.getCerti()), employee);
        }
    }

    private void addIndex(Map<String, Set<Employee>> index, String indexCondition, Employee employeeToAdd) {
        index.computeIfAbsent(indexCondition, useless -> new HashSet<>()).add(employeeToAdd);
    }
}
