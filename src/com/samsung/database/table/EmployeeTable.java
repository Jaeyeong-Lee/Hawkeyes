package com.samsung.database.table;

import com.samsung.employee.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EmployeeTable {
    private static EmployeeTable table = new EmployeeTable();

    public static EmployeeTable getInstance() {
        return EmployeeTable.table;
    }

    private Map<String, Set<Employee>> employeeNumberIndex = new HashMap<>();
    private Map<String, Set<Employee>> nameIndex = new HashMap<>();
    private Map<String, Set<Employee>> firstNameIndex = new HashMap<>();
    private Map<String, Set<Employee>> lastNameIndex = new HashMap<>();
    private Map<String, Set<Employee>> phoneNumberIndex = new HashMap<>();
    private Map<String, Set<Employee>> middleDigitOfPhoneNumberIndex = new HashMap<>();
    private Map<String, Set<Employee>> last4DigitOfPhoneNumberIndex = new HashMap<>();
    private Map<String, Set<Employee>> birthIndex = new HashMap<>();
    private Map<String, Set<Employee>> yearOfBirthIndex = new HashMap<>();
    private Map<String, Set<Employee>> monthOfBirthIndex = new HashMap<>();
    private Map<String, Set<Employee>> dayOfBirthIndex = new HashMap<>();
    private Map<String, Set<Employee>> careerLevelIndex = new HashMap<>();
    private Map<String, Set<Employee>> certiIndex = new HashMap<>();

    private EmployeeTable() {
    }

    public Map<String, Set<Employee>> getEmployeeNumberIndex() {
        return employeeNumberIndex;
    }

    public Map<String, Set<Employee>> getNameIndex() {
        return nameIndex;
    }

    public Map<String, Set<Employee>> getFirstNameIndex() {
        return firstNameIndex;
    }

    public Map<String, Set<Employee>> getLastNameIndex() {
        return lastNameIndex;
    }

    public Map<String, Set<Employee>> getPhoneNumberIndex() {
        return phoneNumberIndex;
    }

    public Map<String, Set<Employee>> getMiddleDigitOfPhoneNumberIndex() {
        return middleDigitOfPhoneNumberIndex;
    }

    public Map<String, Set<Employee>> getLast4DigitOfPhoneNumberIndex() {
        return last4DigitOfPhoneNumberIndex;
    }

    public Map<String, Set<Employee>> getBirthIndex() {
        return birthIndex;
    }

    public Map<String, Set<Employee>> getYearOfBirthIndex() {
        return yearOfBirthIndex;
    }

    public Map<String, Set<Employee>> getMonthOfBirthIndex() {
        return monthOfBirthIndex;
    }

    public Map<String, Set<Employee>> getDayOfBirthIndex() {
        return dayOfBirthIndex;
    }

    public Map<String, Set<Employee>> getCareerLevelIndex() {
        return careerLevelIndex;
    }

    public Map<String, Set<Employee>> getCertiIndex() {
        return certiIndex;
    }

    public void initialize(){
        employeeNumberIndex = new HashMap<>();
        nameIndex = new HashMap<>();
        firstNameIndex = new HashMap<>();
        lastNameIndex = new HashMap<>();
        phoneNumberIndex = new HashMap<>();
        middleDigitOfPhoneNumberIndex = new HashMap<>();
        last4DigitOfPhoneNumberIndex = new HashMap<>();
        birthIndex = new HashMap<>();
        yearOfBirthIndex = new HashMap<>();
        monthOfBirthIndex = new HashMap<>();
        dayOfBirthIndex = new HashMap<>();
        careerLevelIndex = new HashMap<>();
        certiIndex = new HashMap<>();
    }
}
