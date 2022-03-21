package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.PersistentDAO;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class AddCommand<E> extends Command<Set<Employee>>{

    private String commandLine;

    public AddCommand() {};

    public AddCommand(String line){
        commandLine = line;
    }

    @Override
    public Set<Employee> execute() {

        employeeDAO.add(new Employee(commandLine.split(",")));

        return null;
    }

}
