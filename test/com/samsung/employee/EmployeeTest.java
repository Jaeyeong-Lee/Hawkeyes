package com.samsung.employee;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void consistencyValidation() {
        Employee employee = new Employee("18050301", "KYUMOK KIM", CareerLevel.CL2, "010-9777-6055", "19980906", Certi.PRO);

        employee.consistencyValidation();

        assertEquals("KYUMOK", employee.getFirstName());
        assertEquals("KIM", employee.getLastName());
        assertEquals("9777", employee.getMiddleDigitOfPhoneNumber());
        assertEquals("6055", employee.getLast4DigitOfPhoneNumber());
        assertEquals("1998", employee.getYearOfBirth());
        assertEquals("09",employee.getMonthOfBirth());
        assertEquals("06",employee.getDayOfBirth());
    }
}