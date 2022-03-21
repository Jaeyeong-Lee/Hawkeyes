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
                    outputLines.add(command.getCommandString() + "," + ((employees.size()==0)? "NONE" : employees.size()));
                } else {

                    List<Employee> a = employees.stream()
                            .sorted((o1, o2) -> {
                                if (o1.getYearFromEmployeeNumber() == o2.getYearFromEmployeeNumber()) {
                                    return o1.getEmployeeNumber().compareTo(o2.getEmployeeNumber());
                                }
                                return o1.getYearFromEmployeeNumber().compareTo(o2.getYearFromEmployeeNumber());
                            })
                            .limit(5)
                            .collect(Collectors.toList());

                    for (Employee employee : a) {
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
