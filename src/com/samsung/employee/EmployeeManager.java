package com.samsung.employee;

import com.samsung.command.Command;
import com.samsung.command.CommandFactory;
import com.samsung.iomanager.FileIOManager;
import com.samsung.iomanager.IOManager;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private IOManager<String> fileIOManager;
    private List<Command> commandList;

    public Employee overWrite(Employee asIsEmployee, Employee toBeEmployee) {
        Employee employee = new Employee();

        employee.setEmployeeNumber(toBeEmployee.getEmployeeNumber() != null ? toBeEmployee.getEmployeeNumber() : asIsEmployee.getEmployeeNumber());
        employee.setName(toBeEmployee.getName() != null ? toBeEmployee.getName() : asIsEmployee.getName());
        employee.setFirstName(toBeEmployee.getFirstName() != null ? toBeEmployee.getFirstName() : asIsEmployee.getFirstName());
        employee.setLastName(toBeEmployee.getLastName() != null ? toBeEmployee.getLastName() : asIsEmployee.getLastName());
        employee.setPhoneNumber(toBeEmployee.getPhoneNumber() != null ? toBeEmployee.getPhoneNumber() : asIsEmployee.getPhoneNumber());
        employee.setMiddleDigitOfPhoneNumber(toBeEmployee.getMiddleDigitOfPhoneNumber() != null ? toBeEmployee.getMiddleDigitOfPhoneNumber() : asIsEmployee.getMiddleDigitOfPhoneNumber());
        employee.setLast4DigitOfPhoneNumber(toBeEmployee.getLast4DigitOfPhoneNumber() != null ? toBeEmployee.getLast4DigitOfPhoneNumber() : asIsEmployee.getLast4DigitOfPhoneNumber());
        employee.setBirthDay(toBeEmployee.getBirthDay() != null ? toBeEmployee.getBirthDay() : asIsEmployee.getBirthDay());
        employee.setYearOfBirth(toBeEmployee.getYearOfBirth() != null ? toBeEmployee.getYearOfBirth() : asIsEmployee.getYearOfBirth());
        employee.setMonthOfBirth(toBeEmployee.getMonthOfBirth() != null ? toBeEmployee.getMonthOfBirth() : asIsEmployee.getMonthOfBirth());
        employee.setDayOfBirth(toBeEmployee.getDayOfBirth() != null ? toBeEmployee.getDayOfBirth() : asIsEmployee.getDayOfBirth());
        employee.setCareerLevel(toBeEmployee.getCareerLevel() != null ? toBeEmployee.getCareerLevel() : asIsEmployee.getCareerLevel());
        employee.setCerti(toBeEmployee.getCerti() != null ? toBeEmployee.getCerti() : asIsEmployee.getCerti());

        return employee;
    }

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
