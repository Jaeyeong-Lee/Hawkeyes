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
        return null;
    }
}
