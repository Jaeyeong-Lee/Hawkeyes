package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.EmployeeDAO;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

public abstract class Command<T> {

    protected CommandOption commandOption;

    public Command(CommandOption commandOption) {
        this.commandOption = commandOption;
        employeeDAO = new EmployeeDAO();

    }

    public abstract T execute();

    EmployeeDAO employeeDAO;

    public Command(){
        employeeDAO = new EmployeeDAO();
    }


}
