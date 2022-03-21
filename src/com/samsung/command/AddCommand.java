package com.samsung.command;

import com.samsung.database.PersistentDAO;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class AddCommand<E> extends Command<Set<Employee>>{

    public AddCommand() {};

    public AddCommand(String line, CommandOption commandOption){
        super(line, commandOption);
    }

    @Override
    public Set<Employee> execute() {

        System.out.println("excuted");
        return null;
    }

}
