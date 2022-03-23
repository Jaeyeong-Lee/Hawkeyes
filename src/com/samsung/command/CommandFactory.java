package com.samsung.command;

import com.samsung.constants.ConstCommand;
import com.samsung.employee.Employee;
import java.util.Set;


public class CommandFactory {
    
    public Command getCommand(String line) {
        try {
            CommandAttr commandAttr = new CommandAttr();
            commandAttr.setAttrByLine(line);

            switch (commandAttr.getCommand()) {

                case ConstCommand.add:
                    return new AddCommand(line);
                case ConstCommand.search:
                    return new SearchCommand(commandAttr.getOption());
                case ConstCommand.modify:
                    return new ModifyCommand(commandAttr.getOption());
                case ConstCommand.delete:
                    return new DeleteCommand(commandAttr.getOption());

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new Command<Set<Employee>>() {
            @Override
            public Set<Employee> execute() {
                return null;
            }
        };
    }
}
