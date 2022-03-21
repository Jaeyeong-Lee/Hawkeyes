package com.samsung.employee;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;

import java.util.*;

public class Employee {
    private String employeeNumber;
    private String name;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String middleDigitOfPhoneNumber;
    private String last4DigitOfPhoneNumber;
    private String birthDay;
    private String yearOfBirth;
    private String monthOfBirth;
    private String dayOfBirth;
    private CareerLevel careerLevel;
    private Certi certi;

    public Employee() {
    }

    public Employee(String[] addCommandToken) {
        this(addCommandToken[4], addCommandToken[5], CareerLevel.valueOf(addCommandToken[6]), addCommandToken[7], addCommandToken[8], Certi.valueOf(addCommandToken[9]));
    }

    public Employee(String employeeNumber, String name, CareerLevel careerLevel, String phoneNumber, String birthDay, Certi certi) {
        //TODO prefix 구현해야함
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.careerLevel = careerLevel;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.certi = certi;
        consistencyValidation();
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getYearOfEmployeeNumber() {
        return employeeNumber.substring(0,2);
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMiddleDigitOfPhoneNumber() {
        return middleDigitOfPhoneNumber;
    }

    public String getLast4DigitOfPhoneNumber() {
        return last4DigitOfPhoneNumber;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public CareerLevel getCareerLevel() {
        return careerLevel;
    }

    public Certi getCerti() {
        return certi;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMiddleDigitOfPhoneNumber(String middleDigitOfPhoneNumber) {
        this.middleDigitOfPhoneNumber = middleDigitOfPhoneNumber;
    }

    public void setLast4DigitOfPhoneNumber(String last4DigitOfPhoneNumber) {
        this.last4DigitOfPhoneNumber = last4DigitOfPhoneNumber;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setCareerLevel(CareerLevel careerLevel) {
        this.careerLevel = careerLevel;
    }

    public void setCerti(Certi certi) {
        this.certi = certi;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", middleDigitOfPhoneNumber='" + middleDigitOfPhoneNumber + '\'' +
                ", last4DigitOfPhoneNumber='" + last4DigitOfPhoneNumber + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", monthOfBirth='" + monthOfBirth + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", carrierLevel=" + careerLevel +
                ", certi=" + certi +
                '}';
    }

    public String toStringForPrint() {
        return new StringBuilder().append(employeeNumber).append(",")
                .append(name).append(",")
                .append(careerLevel).append(",")
                .append(phoneNumber).append(",")
                .append(birthDay).append(",")
                .append(certi)
                .toString();
    }

    public Date getYearFromEmployeeNumber() {
        Date ret;

        int yearFromEmployeeNumber = Integer.parseInt(employeeNumber.substring(0, 2));
        if (yearFromEmployeeNumber >= 69 && yearFromEmployeeNumber <= 99)
            yearFromEmployeeNumber += 1900;
        else if (yearFromEmployeeNumber >= 0 && yearFromEmployeeNumber <= 21)
            yearFromEmployeeNumber += 2000;

        ret = new GregorianCalendar(yearFromEmployeeNumber, Calendar.JANUARY, 1).getTime();

        return ret;
    }

    public Employee consistencyValidation(){
        if (this.name!=null){
            this.firstName = this.name.split(" ")[0];
            this.lastName = this.name.split(" ")[1];
        }
        if (this.phoneNumber!=null){
            this.middleDigitOfPhoneNumber = this.phoneNumber.split("-")[1];
            this.last4DigitOfPhoneNumber = this.phoneNumber.split("-")[2];
        }
        if (birthDay!=null){
            this.yearOfBirth = this.birthDay.substring(0,4);
            this.monthOfBirth = this.birthDay.substring(4,6);
            this.dayOfBirth = this.birthDay.substring(6,8);
        }
        return this;
    }
}
