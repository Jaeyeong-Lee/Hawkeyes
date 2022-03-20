package com.samsung.employee;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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

    public Employee(String employeeNumber, String name, CareerLevel careerLevel, String phoneNumber, String birthDay, Certi certi) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        if (name!=null){
            this.firstName = name.split(" ")[0];
            this.lastName = name.split(" ")[1];
        }
        this.careerLevel = careerLevel;
        this.phoneNumber = phoneNumber;
        if (phoneNumber!=null){
            this.middleDigitOfPhoneNumber = phoneNumber.split("-")[1];
            this.last4DigitOfPhoneNumber = phoneNumber.split("-")[2];
        }
        //TODO prefix 구현해야함
        this.birthDay = birthDay;
        if (birthDay!=null){
            this.yearOfBirth = birthDay.substring(0,4);
            this.monthOfBirth = birthDay.substring(4,6);
            this.dayOfBirth = birthDay.substring(6,8);
        }
        this.certi = certi;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
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

    private Date getYearFromEmployeeNumber() {
        Date ret;

        int yearFromEmployeeNumber = Integer.parseInt(employeeNumber.substring(0,2));
        if (yearFromEmployeeNumber >= 69 && yearFromEmployeeNumber <= 99)
            yearFromEmployeeNumber += 1900;
        else if (yearFromEmployeeNumber >= 0 && yearFromEmployeeNumber <= 21)
            yearFromEmployeeNumber += 2000;

        ret = new GregorianCalendar(yearFromEmployeeNumber, Calendar.JANUARY, 1).getTime();

        return ret;
    }
}
