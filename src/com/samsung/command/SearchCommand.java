package com.samsung.command;

import com.samsung.database.PersistentDAO;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.List;
import java.util.Set;

public class SearchCommand<E> extends Command<Set<Employee>>{

    public SearchCommand() {};

    public SearchCommand(CommandOption option) {
        super(option);
    }

    @Override
    public Set<Employee> execute() {

        // 1. CommandOption -> Employee 객체로 convert
        Employee searchCondition = commandOption.convertSearchOptionToEmployee();

        // 2. EmployeeDAO.search() 수행
        Set<Employee> returnEmp = employeeDAO.search(searchCondition);

        return returnEmp;
    }

    @Override
    public String toString() {
        return "SCH";
    }
}
