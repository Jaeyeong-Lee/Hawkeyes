package com.samsung.database.table;

import com.samsung.employee.Employee;

import java.util.Map;
import java.util.Set;

public class EmployeeTable {
    private static EmployeeTable table = new EmployeeTable();

    public static EmployeeTable getInstance() {
        return EmployeeTable.table;
    }

    private Map<String, Set<Employee>> employeeNumberIndex;
    private Map<String, Set<Employee>> fullNameIndex;
    private Map<String, Set<Employee>> firstNameIndex;
    private Map<String, Set<Employee>> lastNameIndex;
    private Map<String, Set<Employee>> fullPhoneNumberIndex;
    private Map<String, Set<Employee>> middleDigitOfPhoneNumberIndex;
    private Map<String, Set<Employee>> last4DigitOfPhoneNumberIndex;
    private Map<String, Set<Employee>> fullBirthIndex;
    private Map<String, Set<Employee>> yearOfBirthIndex;
    private Map<String, Set<Employee>> monthOfBirthIndex;
    private Map<String, Set<Employee>> dayOfBirthIndex;
    private Map<String, Set<Employee>> careerIndex;
    private Map<String, Set<Employee>> certiIndex;

    private EmployeeTable() {
    }

    public Map<String, Set<Employee>> getEmployeeNumberIndex() {
        return employeeNumberIndex;
    }

    public void setEmployeeNumberIndex(Map<String, Set<Employee>> employeeNumberIndex) {
        this.employeeNumberIndex = employeeNumberIndex;
    }

    public Map<String, Set<Employee>> getFullNameIndex() {
        return fullNameIndex;
    }

    public void setFullNameIndex(Map<String, Set<Employee>> fullNameIndex) {
        this.fullNameIndex = fullNameIndex;
    }

    public Map<String, Set<Employee>> getFirstNameIndex() {
        return firstNameIndex;
    }

    public void setFirstNameIndex(Map<String, Set<Employee>> firstNameIndex) {
        this.firstNameIndex = firstNameIndex;
    }

    public Map<String, Set<Employee>> getLastNameIndex() {
        return lastNameIndex;
    }

    public void setLastNameIndex(Map<String, Set<Employee>> lastNameIndex) {
        this.lastNameIndex = lastNameIndex;
    }

    public Map<String, Set<Employee>> getFullPhoneNumberIndex() {
        return fullPhoneNumberIndex;
    }

    public void setFullPhoneNumberIndex(Map<String, Set<Employee>> fullPhoneNumberIndex) {
        this.fullPhoneNumberIndex = fullPhoneNumberIndex;
    }

    public Map<String, Set<Employee>> getMiddleDigitOfPhoneNumberIndex() {
        return middleDigitOfPhoneNumberIndex;
    }

    public void setMiddleDigitOfPhoneNumberIndex(Map<String, Set<Employee>> middleDigitOfPhoneNumberIndex) {
        this.middleDigitOfPhoneNumberIndex = middleDigitOfPhoneNumberIndex;
    }

    public Map<String, Set<Employee>> getLast4DigitOfPhoneNumberIndex() {
        return last4DigitOfPhoneNumberIndex;
    }

    public void setLast4DigitOfPhoneNumberIndex(Map<String, Set<Employee>> last4DigitOfPhoneNumberIndex) {
        this.last4DigitOfPhoneNumberIndex = last4DigitOfPhoneNumberIndex;
    }

    public Map<String, Set<Employee>> getFullBirthIndex() {
        return fullBirthIndex;
    }

    public void setFullBirthIndex(Map<String, Set<Employee>> fullBirthIndex) {
        this.fullBirthIndex = fullBirthIndex;
    }

    public Map<String, Set<Employee>> getYearOfBirthIndex() {
        return yearOfBirthIndex;
    }

    public void setYearOfBirthIndex(Map<String, Set<Employee>> yearOfBirthIndex) {
        this.yearOfBirthIndex = yearOfBirthIndex;
    }

    public Map<String, Set<Employee>> getMonthOfBirthIndex() {
        return monthOfBirthIndex;
    }

    public void setMonthOfBirthIndex(Map<String, Set<Employee>> monthOfBirthIndex) {
        this.monthOfBirthIndex = monthOfBirthIndex;
    }

    public Map<String, Set<Employee>> getDayOfBirthIndex() {
        return dayOfBirthIndex;
    }

    public void setDayOfBirthIndex(Map<String, Set<Employee>> dayOfBirthIndex) {
        this.dayOfBirthIndex = dayOfBirthIndex;
    }

    public Map<String, Set<Employee>> getCareerIndex() {
        return careerIndex;
    }

    public void setCareerIndex(Map<String, Set<Employee>> careerIndex) {
        this.careerIndex = careerIndex;
    }

    public Map<String, Set<Employee>> getCertiIndex() {
        return certiIndex;
    }

    public void setCertiIndex(Map<String, Set<Employee>> certiIndex) {
        this.certiIndex = certiIndex;
    }
}
