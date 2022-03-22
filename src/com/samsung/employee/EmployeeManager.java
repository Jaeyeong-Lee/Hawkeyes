package com.samsung.employee;

import com.samsung.command.Command;
import com.samsung.command.CommandFactory;
import com.samsung.iomanager.FileIOManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeManager {

    public void process(String inputFileName, String outputFileName) {
        FileIOManager fileIOManager = new FileIOManager();

        List<String> inputLines = fileIOManager.readInput(inputFileName);

        List<Command> commandList = getCommandObjectList(inputLines);

        List<String> outputLines = getOutputLines(commandList);

        fileIOManager.writeOutput(outputFileName, outputLines);
    }

    private List<Command> getCommandObjectList(List<String> inputLines) {

        // TODO : factory 매번 생성보다 한 번 생성해 두는 게 좋을 것 같아 코드 남겨 둠 (주석 추후 삭제)
        // 또는 다른 디자인패턴을 활용하여 가능할 수 있지 않을까 검
        CommandFactory factory = new CommandFactory();

        return inputLines.stream().map(line -> factory.getCommand(line)).collect(Collectors.toList());

        // TODO: 아래 처럼 활용하려면 getCommand Method가 static이어야 함
        // return inputLines.stream().map(CommandFactory::getCommand).collect(Collectors.toList());
    }

    private List<String> getOutputLines(List<Command> commandList) {
        List<String> outputLines = new ArrayList<>();
        commandList.stream().map(this::getOutputLineByCommand).forEach((command) -> outputLines.add(command.toString()));
        return outputLines;
    }

    private String getOutputLineByCommand(Command<Set<Employee>> command) {

        Set<Employee> employees = command.execute();

        if (employees == null) {
            return "";
        }

        if (employees.size() == 0) {
            return command + "," + "NONE";
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
            employee.setFirstName(toBeEmployee.getName().split(" ")[0]);
            employee.setLastName(toBeEmployee.getName().split(" ")[1]);
        } else if (toBeEmployee.getFirstName() != null) {
            employee.setName(toBeEmployee.getFirstName() + " " + employee.getName().split(" ")[1]);
        } else if (toBeEmployee.getLastName() != null) {
            employee.setName(employee.getName().split(" ")[0] + " " + toBeEmployee.getLastName());
        } else if (toBeEmployee.getPhoneNumber() != null) {
            employee.setMiddleDigitOfPhoneNumber(toBeEmployee.getPhoneNumber().split("-")[1]);
            employee.setLast4DigitOfPhoneNumber(toBeEmployee.getPhoneNumber().split("-")[2]);
        } else if (toBeEmployee.getMiddleDigitOfPhoneNumber() != null) {
            employee.setPhoneNumber(employee.getPhoneNumber().split("-")[0]
                    + toBeEmployee.getMiddleDigitOfPhoneNumber() + employee.getPhoneNumber()
                    .split("-")[2]);
        } else if (toBeEmployee.getLast4DigitOfPhoneNumber() != null) {
            employee.setPhoneNumber(
                    employee.getPhoneNumber().split("-")[0] + employee.getPhoneNumber()
                            .split("-")[1] + toBeEmployee.getLast4DigitOfPhoneNumber());
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
