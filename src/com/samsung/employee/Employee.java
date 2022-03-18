package com.samsung.employee;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Employee {
    private String employeeNumber;
    private Date yearOfEntry; // 입사년도 19 또는 20을 prefix로 넣기
    private String name;
    private CareerLevel carrierLevel;
    private String phoneNumber;
    private String birthDay;
    private Certi softwareCertificatedLevel;

    public Employee(String employeeNumber, String name, String careerLevel, String phoneNumber, String birthDay, String softwareCertificatedLevel){
        this.employeeNumber = employeeNumber;
        this.yearOfEntry = getYearFromEmployeeNumber();

        this.name = name;
        this.carrierLevel = careerLevel == null? null : CareerLevel.valueOf(careerLevel);
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.softwareCertificatedLevel = softwareCertificatedLevel == null? null: Certi.valueOf(softwareCertificatedLevel);
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

    public String getEmployeeNumber(){
        return this.employeeNumber;
    }
}
