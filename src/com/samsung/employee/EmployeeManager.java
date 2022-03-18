package com.samsung.employee;

import com.samsung.command.Command;
import com.samsung.database.PersistentDAO;
import com.samsung.iomanager.IOManager;

import java.util.List;

public class EmployeeManager {
    private IOManager<String> fileIOManager;
    private List<Command> commandList;
}
