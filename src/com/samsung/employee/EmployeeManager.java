package com.samsung.employee;

import com.samsung.command.Command;
import com.samsung.command.CommandFactory;
import com.samsung.database.PersistentDAO;
import com.samsung.database.table.EmployeeTable;
import com.samsung.iomanager.FileIOManager;
import com.samsung.iomanager.IOManager;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private IOManager<String> fileIOManager;
    private List<Command> commandList;




    public void process(String inputFileName, String outputFileName) throws Exception {

        FileIOManager fileIOManager = new FileIOManager();

        List<String> inputLines = fileIOManager.readInput(inputFileName);
        List<String> outputLines = new ArrayList<>();
        commandList = new ArrayList<>();

        CommandFactory factory = new CommandFactory();

        for (String line : inputLines) {
            commandList.add(factory.getCommand(line.substring(0,3)));
        }

        for (Command command : commandList) {
            command.execute();
        }

    }


}
