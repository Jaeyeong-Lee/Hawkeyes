package com.samsung.employee;

import com.samsung.command.Command;
import com.samsung.command.CommandFactory;
import com.samsung.constants.ConstCommand;
import com.samsung.constants.ConstEmployee;
import com.samsung.iomanager.FileIOManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeManager {

    List<String> inputLines = new ArrayList<>();
    List<String> outputLines = new ArrayList<>();
    List<Command> commandList = new ArrayList<>();

    public void process(String inputFileName, String outputFileName) {
        FileIOManager fileIOManager = new FileIOManager();

        inputLines = fileIOManager.readInput(inputFileName);

        makeCommandObjectList();

        makeOutputLines();

        fileIOManager.writeOutput(outputFileName, outputLines);
    }

    private void makeCommandObjectList() {
        CommandFactory factory = new CommandFactory();
        commandList = inputLines.stream().map(line -> factory.getCommand(line))
                .collect(Collectors.toList());
    }

    private void makeOutputLines() {
        commandList.stream().map(this::getOutputLineByCommand).forEach((command) -> outputLines.add(command.toString()));
    }

    private String getOutputLineByCommand(Command<Set<Employee>> command) {

        Set<Employee> employees = command.execute();

        if (employees == null || ConstCommand.add.equals(command.toString())) {
            return "";
        }

        if (employees.size() == 0) {
            return command + "," + ConstCommand.none;
        }

        if (!command.getCommandOption().getIsPrint()) {
            return command + "," + employees.size();
        }

        return employees.stream()
                .sorted(Comparator.comparing(Employee::getYearFromEmployeeNumber)
                        .thenComparing(Employee::getEmployeeNumber))
                .limit(5)
                .map(employee -> command + "," + employee.toString())
                .collect(Collectors.joining("\n"));

    }

    public Employee overWrite(Employee asIsEmployee, Employee toBeEmployee) {
        Employee employee = new Employee();

        employee.setEmployeeNumber(
                toBeEmployee.getEmployeeNumber() != null ? toBeEmployee.getEmployeeNumber()
                        : asIsEmployee.getEmployeeNumber());
        employee.setName(
                toBeEmployee.getName() != null ? toBeEmployee.getName() : asIsEmployee.getName());
        employee.setFirstName(toBeEmployee.getFirstName() != null ? toBeEmployee.getFirstName()
                : asIsEmployee.getFirstName());
        employee.setLastName(toBeEmployee.getLastName() != null ? toBeEmployee.getLastName()
                : asIsEmployee.getLastName());
        employee.setPhoneNumber(
                toBeEmployee.getPhoneNumber() != null ? toBeEmployee.getPhoneNumber()
                        : asIsEmployee.getPhoneNumber());
        employee.setMiddleDigitOfPhoneNumber(toBeEmployee.getMiddleDigitOfPhoneNumber() != null
                ? toBeEmployee.getMiddleDigitOfPhoneNumber()
                : asIsEmployee.getMiddleDigitOfPhoneNumber());
        employee.setLast4DigitOfPhoneNumber(toBeEmployee.getLast4DigitOfPhoneNumber() != null
                ? toBeEmployee.getLast4DigitOfPhoneNumber()
                : asIsEmployee.getLast4DigitOfPhoneNumber());
        employee.setBirthDay(toBeEmployee.getBirthDay() != null ? toBeEmployee.getBirthDay()
                : asIsEmployee.getBirthDay());
        employee.setYearOfBirth(
                toBeEmployee.getYearOfBirth() != null ? toBeEmployee.getYearOfBirth()
                        : asIsEmployee.getYearOfBirth());
        employee.setMonthOfBirth(
                toBeEmployee.getMonthOfBirth() != null ? toBeEmployee.getMonthOfBirth()
                        : asIsEmployee.getMonthOfBirth());
        employee.setDayOfBirth(toBeEmployee.getDayOfBirth() != null ? toBeEmployee.getDayOfBirth()
                : asIsEmployee.getDayOfBirth());
        employee.setCareerLevel(
                toBeEmployee.getCareerLevel() != null ? toBeEmployee.getCareerLevel()
                        : asIsEmployee.getCareerLevel());
        employee.setCerti(toBeEmployee.getCerti() != null ? toBeEmployee.getCerti()
                : asIsEmployee.getCerti());

        if (toBeEmployee.getName() != null) {
            employee.setFirstName(toBeEmployee.getName().split(ConstEmployee.whiteSpaceDelimiter)[0]);
            employee.setLastName(toBeEmployee.getName().split(ConstEmployee.whiteSpaceDelimiter)[1]);
        } else if (toBeEmployee.getFirstName() != null) {
            employee.setName(toBeEmployee.getFirstName() + ConstEmployee.whiteSpaceDelimiter + employee.getName().split(ConstEmployee.whiteSpaceDelimiter)[1]);
        } else if (toBeEmployee.getLastName() != null) {
            employee.setName(employee.getName().split(ConstEmployee.whiteSpaceDelimiter)[0] + ConstEmployee.whiteSpaceDelimiter + toBeEmployee.getLastName());
        } else if (toBeEmployee.getPhoneNumber() != null) {
            employee.setMiddleDigitOfPhoneNumber(toBeEmployee.getPhoneNumber().split(ConstEmployee.hyphenDelimiter)[1]);
            employee.setLast4DigitOfPhoneNumber(toBeEmployee.getPhoneNumber().split(ConstEmployee.hyphenDelimiter)[2]);
        } else if (toBeEmployee.getMiddleDigitOfPhoneNumber() != null) {
            employee.setPhoneNumber(employee.getPhoneNumber().split(ConstEmployee.hyphenDelimiter)[0]
                    + toBeEmployee.getMiddleDigitOfPhoneNumber() + employee.getPhoneNumber()
                    .split(ConstEmployee.hyphenDelimiter)[2]);
        } else if (toBeEmployee.getLast4DigitOfPhoneNumber() != null) {
            employee.setPhoneNumber(
                    employee.getPhoneNumber().split(ConstEmployee.hyphenDelimiter)[0] + employee.getPhoneNumber()
                            .split(ConstEmployee.hyphenDelimiter)[1] + toBeEmployee.getLast4DigitOfPhoneNumber());
        } else if (toBeEmployee.getBirthDay() != null) {
            employee.setYearOfBirth(toBeEmployee.getBirthDay().substring(0, 4));
            employee.setMonthOfBirth(toBeEmployee.getBirthDay().substring(4, 6));
            employee.setDayOfBirth(toBeEmployee.getBirthDay().substring(6, 8));
        } else if (toBeEmployee.getYearOfBirth() != null) {
            employee.setBirthDay(
                    toBeEmployee.getYearOfBirth() + employee.getBirthDay().substring(4, 8));
        } else if (toBeEmployee.getMonthOfBirth() != null) {
            employee.setBirthDay(
                    employee.getBirthDay().substring(0, 4) + toBeEmployee.getMonthOfBirth()
                            + employee.getBirthDay().substring(6, 8));
        } else if (toBeEmployee.getDayOfBirth() != null) {
            employee.setBirthDay(
                    employee.getBirthDay().substring(0, 6) + toBeEmployee.getDayOfBirth());
        }

        return employee;
    }

}
