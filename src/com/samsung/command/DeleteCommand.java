package com.samsung.command;

import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;
public class DeleteCommand<E> extends Command<Set<Employee>> {

    public DeleteCommand() {};

    public DeleteCommand(CommandOption commandOption) {
        super(commandOption);
    }

    @Override
    public Set<Employee> execute() {
        // 1. CommandOption -> Employee 객체로 convert
        Employee deleteCondition = convertCommandOptionToEmployeeParam();

        // 2. EmployeeDAO.delete() 수행
        Set<Employee> returnEmp = employeeDAO.delete(deleteCondition);

        return returnEmp;
    }
}
