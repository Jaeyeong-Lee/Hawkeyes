package com.samsung.command;

import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class ModifyCommand<E> extends Command<Set<Employee>> {

    public ModifyCommand() {};

    public ModifyCommand(CommandOption commandOption) {
        super(commandOption);
    }

    @Override
    public Set<Employee> execute() {
        // 1. CommandOption -> Employee 객체로 convert
        Employee asIsEmployee = commandOption.convertSearchOptionToEmployee();
        Employee toBeEmployee = commandOption.convertModifyOptionToEmployee();

        // 2. EmployeeDAO.delete() 수행
        Set<Employee> returnEmp = employeeDAO.modify(asIsEmployee, toBeEmployee);

        return returnEmp;
    }

    @Override
    public String toString() {
        return "MOD";
    }
}
