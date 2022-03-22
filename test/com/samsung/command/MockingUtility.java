package com.samsung.command;

import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockingUtility {

    public void mockAllIndexingTables(List<Employee> fakeEmployeeList,
            EmployeeTable testEmployeeTable) {
        for (Employee employee : fakeEmployeeList) {
            Set<Employee> employeeNumberIndexingTable = testEmployeeTable.getEmployeeNumberIndex()
                    .computeIfAbsent(employee.getEmployeeNumber(), t -> new HashSet<>());
            employeeNumberIndexingTable.add(employee);

            Set<Employee> employeeNameIndexingTable = testEmployeeTable.getNameIndex()
                    .computeIfAbsent(employee.getName(), t -> new HashSet<>());
            employeeNameIndexingTable.add(employee);

            Set<Employee> employeeFirstNameIndexingTable = testEmployeeTable.getFirstNameIndex()
                    .computeIfAbsent(employee.getFirstName(), t -> new HashSet<>());
            employeeFirstNameIndexingTable.add(employee);

            Set<Employee> employeeLastNameIndexingTable = testEmployeeTable.getLastNameIndex()
                    .computeIfAbsent(employee.getLastName(), t -> new HashSet<>());
            employeeLastNameIndexingTable.add(employee);

            Set<Employee> employeePhoneNumberIndexingTable = testEmployeeTable.getPhoneNumberIndex()
                    .computeIfAbsent(employee.getPhoneNumber(), t -> new HashSet<>());
            employeePhoneNumberIndexingTable.add(employee);

            Set<Employee> employeeMiddleDigitOfPhoneNumberIndexingTable = testEmployeeTable.getMiddleDigitOfPhoneNumberIndex()
                    .computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), t -> new HashSet<>());
            employeeMiddleDigitOfPhoneNumberIndexingTable.add(employee);

            Set<Employee> employeeLastDigitOfPhoneNumberIndexingTable = testEmployeeTable.getLast4DigitOfPhoneNumberIndex()
                    .computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), t -> new HashSet<>());
            employeeLastDigitOfPhoneNumberIndexingTable.add(employee);

            Set<Employee> employeeBirthIndexingTable = testEmployeeTable.getBirthIndex()
                    .computeIfAbsent(employee.getBirthDay(), t -> new HashSet<>());
            employeeBirthIndexingTable.add(employee);

            Set<Employee> employeeYearOfBirthIndexingTable = testEmployeeTable.getYearOfBirthIndex()
                    .computeIfAbsent(employee.getYearOfBirth(), t -> new HashSet<>());
            employeeYearOfBirthIndexingTable.add(employee);

            Set<Employee> employeeMonthOfBirthIndexingTable = testEmployeeTable.getMonthOfBirthIndex()
                    .computeIfAbsent(employee.getMonthOfBirth(), t -> new HashSet<>());
            employeeMonthOfBirthIndexingTable.add(employee);

            Set<Employee> employeeDayOfBirthIndexingTable = testEmployeeTable.getDayOfBirthIndex()
                    .computeIfAbsent(employee.getDayOfBirth(), t -> new HashSet<>());
            employeeDayOfBirthIndexingTable.add(employee);

            Set<Employee> employeeCareerLevelIndexingTable = testEmployeeTable.getCareerLevelIndex()
                    .computeIfAbsent(employee.getCareerLevel().toString(), t -> new HashSet<>());
            employeeCareerLevelIndexingTable.add(employee);

            Set<Employee> employeeCertiIndexingTable = testEmployeeTable.getCertiIndex()
                    .computeIfAbsent(employee.getCerti().toString(), t -> new HashSet<>());
            employeeCertiIndexingTable.add(employee);
        }
    }
}
