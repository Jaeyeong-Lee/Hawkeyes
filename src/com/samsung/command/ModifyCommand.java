package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.Set;

public class ModifyCommand extends Command<Set<Employee>> {

    public ModifyCommand() {
    }

    public ModifyCommand(CommandOption commandOption) {
        super(commandOption);
    }

    @Override
    public Set<Employee> execute() {
        return employeeDAO.modify(commandOption.convertSearchOptionToEmployee(),
                commandOption.convertModifyOptionToEmployee());
    }

    @Override
    public String toString() {
        return ConstCommand.modify;
    }
}
