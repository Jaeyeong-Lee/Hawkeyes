package com.samsung.employee;

import com.samsung.command.Command;
import com.samsung.command.CommandFactory;
import com.samsung.iomanager.FileIOManager;
import com.samsung.iomanager.IOManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            employee.setPhoneNumber(employee.getPhoneNumber().split("-")[0] + toBeEmployee.getMiddleDigitOfPhoneNumber() + employee.getPhoneNumber().split("-")[2]);
        } else if (toBeEmployee.getLast4DigitOfPhoneNumber() != null) {
            employee.setPhoneNumber(employee.getPhoneNumber().split("-")[0] + employee.getPhoneNumber().split("-")[1] + toBeEmployee.getLast4DigitOfPhoneNumber());
        } else if (toBeEmployee.getBirthDay() != null) {
            employee.setYearOfBirth(toBeEmployee.getBirthDay().substring(0, 4));
            employee.setMonthOfBirth(toBeEmployee.getBirthDay().substring(4, 6));
            employee.setDayOfBirth(toBeEmployee.getBirthDay().substring(6, 8));
        } else if (toBeEmployee.getYearOfBirth() != null) {
            employee.setBirthDay(toBeEmployee.getYearOfBirth() + employee.getBirthDay().substring(4, 8));
        } else if (toBeEmployee.getMonthOfBirth() != null) {
            employee.setBirthDay(employee.getBirthDay().substring(0, 4) + toBeEmployee.getMonthOfBirth() + employee.getBirthDay().substring(6, 8));
        } else if (toBeEmployee.getDayOfBirth() != null) {
            employee.setBirthDay(employee.getBirthDay().substring(0, 6) + toBeEmployee.getDayOfBirth());
        }

        return employee;
    }

    public void process(String inputFileName, String outputFileName) {
        FileIOManager fileIOManager = new FileIOManager();

        List<String> inputLines = fileIOManager.readInput(inputFileName);
        List<String> outputLines = new ArrayList<>();
        commandList = new ArrayList<>();

        CommandFactory factory = new CommandFactory();

        for (String line : inputLines) {
            commandList.add(factory.getCommand(line));
        }

        for (Command<Set<Employee>> command : commandList) {
            Set<Employee> employees = command.execute();

            if (employees != null) {
                if (!command.getCommandOption().getIsPrint()) {
                    outputLines.add(command.getCommandString() + "," + ((employees.size() == 0) ? "NONE" : employees.size()));
                } else {

                    employees.stream()
                            .sorted(Comparator.comparing(Employee::getEmployeeNumber))
                            .limit(5)
                            .collect(Collectors.toList());

                    for (Employee employee : employees) {
                        outputLines.add(command.getCommandString() + "," + employee.toStringForPrint());
                    }

                }

            }

        }

        outputLines.forEach(System.out::println); // TODO 디버그용으로 추후 삭제 필요
        fileIOManager.writeOutput(outputFileName, outputLines);
    }

    // TODO: 아래 코드는 TC없이 Refactoring 시도하다 구현하지 못한 코드 입니다. 추후 Refactoring시 참고 위해 남겨둡니다.
    private ArrayList<String> getOutputLine(Command<Set<Employee>> command, Set<Employee> employees) {

        // TODO: 특수한 사례
        if (employees == null) {
            return new ArrayList<>();
        }

        if (!command.getCommandOption().getIsPrint()) {
            // return new ArrayList<>().add(command.getCommandString() + "," + ((employees.size()==0)? "NONE" : employees.size()));
        }

        // return getPrintLines(command.getCommandString(), employees);
        return null;
    }

    private String getPrintLines(String commandString, Set<Employee> employees) {

        employees.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumber))
                .limit(5)
                .collect(Collectors.toList());

        StringBuilder printLines = new StringBuilder();

        for (Employee employee : employees) {
            printLines.append(commandString + "," + employee.toStringForPrint() + "\n");
        }
        return printLines.toString();
    }
}
