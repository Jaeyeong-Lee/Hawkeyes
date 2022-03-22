package com.samsung.util;

import com.samsung.employee.Employee;

import java.util.Set;

public class Utility {
    public static boolean isSetNotNullAndEmpty(Set<Employee> employees){
        if (employees!=null && employees.isEmpty()) return true;
        return false;
    }
}
