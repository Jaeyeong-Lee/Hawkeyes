package com.samsung.database;

import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;
import com.samsung.employee.EmployeeManager;

import java.util.*;

public class EmployeeDAO extends PersistentDAO<Employee> {
    private EmployeeTable employeeTable = EmployeeTable.getInstance();

    @Override
    public int add(Employee employee) {
        try {
            employeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getNameIndex().computeIfAbsent(employee.getName(), useless -> new HashSet<>()).add(employee);
            employeeTable.getFirstNameIndex().computeIfAbsent(employee.getFirstName(), useless -> new HashSet<>()).add(employee);
            employeeTable.getLastNameIndex().computeIfAbsent(employee.getLastName(), useless -> new HashSet<>()).add(employee);
            employeeTable.getPhoneNumberIndex().computeIfAbsent(employee.getPhoneNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getLast4DigitOfPhoneNumberIndex().computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), useless -> new HashSet<>()).add(employee);
            employeeTable.getBirthIndex().computeIfAbsent(employee.getBirthDay(), useless -> new HashSet<>()).add(employee);
            employeeTable.getYearOfBirthIndex().computeIfAbsent(employee.getYearOfBirth(), useless -> new HashSet<>()).add(employee);
            employeeTable.getMonthOfBirthIndex().computeIfAbsent(employee.getMonthOfBirth(), useless -> new HashSet<>()).add(employee);
            employeeTable.getDayOfBirthIndex().computeIfAbsent(employee.getDayOfBirth(), useless -> new HashSet<>()).add(employee);
            employeeTable.getCareerLevelIndex().computeIfAbsent(employee.getCareerLevel().toString(), useless -> new HashSet<>()).add(employee);
            employeeTable.getCertiIndex().computeIfAbsent(employee.getCerti().toString(), useless -> new HashSet<>()).add(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Set<Employee> search(Employee employee) {
        Set<Employee> result = new HashSet<>();
        try {
            Optional.ofNullable(employee.getEmployeeNumber()).ifPresent(useless -> result.addAll(searchByEmployeeNumber(employee)));
            Optional.ofNullable(employee.getName()).ifPresent(useless -> result.addAll(searchByName(employee)));
            Optional.ofNullable(employee.getFirstName()).ifPresent(useless -> result.addAll(searchByFirstName(employee)));
            Optional.ofNullable(employee.getLastName()).ifPresent(useless -> result.addAll(searchByLastName(employee)));
            Optional.ofNullable(employee.getPhoneNumber()).ifPresent(useless -> result.addAll(searchByPhoneNumber(employee)));
            Optional.ofNullable(employee.getMiddleDigitOfPhoneNumber()).ifPresent(useless -> result.addAll(searchByMiddleDigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getLast4DigitOfPhoneNumber()).ifPresent(useless -> result.addAll(searchByLast4DigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getBirthDay()).ifPresent(useless -> result.addAll(searchByBirth(employee)));
            Optional.ofNullable(employee.getYearOfBirth()).ifPresent(useless -> result.addAll(searchByYearOfBirth(employee)));
            Optional.ofNullable(employee.getMonthOfBirth()).ifPresent(useless -> result.addAll(searchByMonthOfBirth(employee)));
            Optional.ofNullable(employee.getDayOfBirth()).ifPresent(useless -> result.addAll(searchByDayOfBirth(employee)));
            Optional.ofNullable(employee.getCareerLevel()).ifPresent(useless -> result.addAll(searchByCareerLevel(employee)));
            Optional.ofNullable(employee.getCerti())
                    .ifPresent(useless -> result
                            .addAll(searchByCerti(employee)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Employee> searchByEmployeeNumber(Employee employee) {
        return employeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByName(Employee employee) {
        return employeeTable.getNameIndex().computeIfAbsent(employee.getName(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByFirstName(Employee employee) {
        return employeeTable.getFirstNameIndex().computeIfAbsent(employee.getFirstName(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByLastName(Employee employee) {
        return employeeTable.getLastNameIndex().computeIfAbsent(employee.getLastName(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByPhoneNumber(Employee employee) {
        return employeeTable.getPhoneNumberIndex().computeIfAbsent(employee.getPhoneNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByMiddleDigitOfPhoneNumber(Employee employee) {
        return employeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByLast4DigitOfPhoneNumber(Employee employee) {
        return employeeTable.getLast4DigitOfPhoneNumberIndex().computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByBirth(Employee employee) {
        return employeeTable.getBirthIndex().computeIfAbsent(employee.getBirthDay(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByYearOfBirth(Employee employee) {
        return employeeTable.getYearOfBirthIndex().computeIfAbsent(employee.getYearOfBirth(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByMonthOfBirth(Employee employee) {
        return employeeTable.getMonthOfBirthIndex().computeIfAbsent(employee.getMonthOfBirth(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByDayOfBirth(Employee employee) {
        return employeeTable.getDayOfBirthIndex().computeIfAbsent(employee.getDayOfBirth(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByCareerLevel(Employee employee) {
        return employeeTable.getCareerLevelIndex().computeIfAbsent(employee.getCareerLevel().toString(), useless -> new HashSet<>());
    }

    public Set<Employee> searchByCerti(Employee employee) {
        return employeeTable.getCertiIndex().computeIfAbsent(employee.getCerti().toString(), useless -> new HashSet<>());
    }

    @Override
    public Set<Employee> delete(Employee employee) {
        Set<Employee> employees = null;
        try {
            employees = search(employee);
            for (Employee employeeToDelete : employees) {
                employeeTable.getEmployeeNumberIndex().remove(employeeToDelete.getEmployeeNumber());
                employeeTable.getNameIndex().remove(employeeToDelete.getName());
                employeeTable.getFirstNameIndex().remove(employeeToDelete.getFirstName());
                employeeTable.getLastNameIndex().remove(employeeToDelete.getLastName());
                employeeTable.getPhoneNumberIndex().remove(employeeToDelete.getPhoneNumber());
                employeeTable.getMiddleDigitOfPhoneNumberIndex().remove(employeeToDelete.getMiddleDigitOfPhoneNumber());
                employeeTable.getLast4DigitOfPhoneNumberIndex().remove(employeeToDelete.getLast4DigitOfPhoneNumber());
                employeeTable.getBirthIndex().remove(employeeToDelete.getBirthDay());
                employeeTable.getYearOfBirthIndex().remove(employeeToDelete.getYearOfBirth());
                employeeTable.getMonthOfBirthIndex().remove(employeeToDelete.getMonthOfBirth());
                employeeTable.getDayOfBirthIndex().remove(employeeToDelete.getDayOfBirth());
                employeeTable.getCareerLevelIndex().remove(employeeToDelete.getCareerLevel().toString());
                employeeTable.getCertiIndex().remove(employeeToDelete.getCerti().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Set<Employee> modify(Employee asIsEmployee, Employee toBeEmployee) {
        Set<Employee> asIsEmployees = null;
        try {
            asIsEmployees = search(asIsEmployee);
            modifyValueInTable(asIsEmployee, toBeEmployee, asIsEmployees);
            modifyKeyInTable(toBeEmployee, asIsEmployees);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asIsEmployees;
    }

    private void modifyKeyInTable(Employee toBeEmployee, Set<Employee> asIsEmployees) {
        EmployeeManager employeeManager = new EmployeeManager();

        for (Employee asIs : asIsEmployees) {
            Employee toBe = employeeManager.overWrite(asIs, toBeEmployee);
            if (toBe.getEmployeeNumber()!=null){
                Set<Employee> index = employeeTable.getEmployeeNumberIndex().get(asIs.getEmployeeNumber());
                employeeTable.getEmployeeNumberIndex().remove(asIs.getEmployeeNumber());
                employeeTable.getEmployeeNumberIndex().put(toBe.getEmployeeNumber(), index);
            }

            if (toBe.getName()!=null){
                Set<Employee> index = employeeTable.getNameIndex().get(asIs.getName());
                employeeTable.getNameIndex().remove(asIs.getName());
                employeeTable.getNameIndex().put(toBe.getName(), index);
            }

            if (toBe.getFirstName()!=null){
                Set<Employee> index = employeeTable.getFirstNameIndex().get(asIs.getFirstName());
                employeeTable.getFirstNameIndex().remove(asIs.getFirstName());
                employeeTable.getFirstNameIndex().put(toBe.getFirstName(), index);
            }

            if (toBe.getLastName()!=null){
                Set<Employee> index = employeeTable.getLastNameIndex().get(asIs.getLastName());
                employeeTable.getLastNameIndex().remove(asIs.getLastName());
                employeeTable.getLastNameIndex().put(toBe.getLastName(), index);
            }

            if (toBe.getPhoneNumber()!=null){
                Set<Employee> index = employeeTable.getPhoneNumberIndex().get(asIs.getPhoneNumber());
                employeeTable.getPhoneNumberIndex().remove(asIs.getPhoneNumber());
                employeeTable.getPhoneNumberIndex().put(toBe.getPhoneNumber(), index);
            }

            if (toBe.getMiddleDigitOfPhoneNumber()!=null){
                Set<Employee> index = employeeTable.getMiddleDigitOfPhoneNumberIndex().get(asIs.getMiddleDigitOfPhoneNumber());
                employeeTable.getMiddleDigitOfPhoneNumberIndex().remove(asIs.getMiddleDigitOfPhoneNumber());
                employeeTable.getMiddleDigitOfPhoneNumberIndex().put(toBe.getMiddleDigitOfPhoneNumber(), index);
            }

            if (toBe.getLast4DigitOfPhoneNumber()!=null){
                Set<Employee> index = employeeTable.getLast4DigitOfPhoneNumberIndex().get(asIs.getLast4DigitOfPhoneNumber());
                employeeTable.getLast4DigitOfPhoneNumberIndex().remove(asIs.getLast4DigitOfPhoneNumber());
                employeeTable.getLast4DigitOfPhoneNumberIndex().put(toBe.getLast4DigitOfPhoneNumber(), index);
            }

            if (toBe.getBirthDay()!=null){
                Set<Employee> index = employeeTable.getBirthIndex().get(asIs.getBirthDay());
                employeeTable.getBirthIndex().remove(asIs.getBirthDay());
                employeeTable.getBirthIndex().put(toBe.getBirthDay(), index);
            }

            if (toBe.getYearOfBirth()!=null){
                Set<Employee> index = employeeTable.getYearOfBirthIndex().get(asIs.getYearOfBirth());
                employeeTable.getYearOfBirthIndex().remove(asIs.getYearOfBirth());
                employeeTable.getYearOfBirthIndex().put(toBe.getYearOfBirth(), index);
            }

            if (toBe.getMonthOfBirth()!=null){
                Set<Employee> index = employeeTable.getMonthOfBirthIndex().get(asIs.getMonthOfBirth());
                employeeTable.getMonthOfBirthIndex().remove(asIs.getMonthOfBirth());
                employeeTable.getMonthOfBirthIndex().put(toBe.getMonthOfBirth(), index);
            }

            if (toBe.getDayOfBirth()!=null){
                Set<Employee> index = employeeTable.getDayOfBirthIndex().get(asIs.getDayOfBirth());
                employeeTable.getDayOfBirthIndex().remove(asIs.getDayOfBirth());
                employeeTable.getDayOfBirthIndex().put(toBe.getDayOfBirth(), index);
            }

            if (toBe.getCareerLevel()!=null){
                Set<Employee> index = employeeTable.getCareerLevelIndex().get(asIs.getCareerLevel().toString());
                employeeTable.getCareerLevelIndex().remove(asIs.getCareerLevel().toString());
                employeeTable.getCareerLevelIndex().put(toBe.getCareerLevel().toString(), index);
            }

            if (toBe.getCerti()!=null){
                Set<Employee> index = employeeTable.getCertiIndex().get(asIs.getCerti().toString());
                employeeTable.getCertiIndex().remove(asIs.getCerti().toString());
                employeeTable.getCertiIndex().put(toBe.getCerti().toString(), index);
            }
        }
    }

    private void modifyValueInTable(Employee asIsEmployee, Employee toBeEmployee, Set<Employee> asIsEmployees) {
        EmployeeManager employeeManager = new EmployeeManager();

        for (Employee asIs : asIsEmployees) {
            Employee toBe = employeeManager.overWrite(asIs, toBeEmployee);

            Set<Employee> valuesToChange = searchByEmployeeNumber(asIsEmployee);
            valuesToChange.remove(asIs);
            valuesToChange.add(toBe);

            Set<Employee> index;

            index = employeeTable.getNameIndex().get(asIs.getName());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getFirstNameIndex().get(asIs.getFirstName());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getLastNameIndex().get(asIs.getLastName());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getPhoneNumberIndex().get(asIs.getPhoneNumber());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getMiddleDigitOfPhoneNumberIndex().get(asIs.getMiddleDigitOfPhoneNumber());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getLast4DigitOfPhoneNumberIndex().get(asIs.getLast4DigitOfPhoneNumber());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getBirthIndex().get(asIs.getBirthDay());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getYearOfBirthIndex().get(asIs.getYearOfBirth());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getMonthOfBirthIndex().get(asIs.getMonthOfBirth());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getDayOfBirthIndex().get(asIs.getDayOfBirth());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getCareerLevelIndex().get(asIs.getCareerLevel().toString());
            index.remove(asIs);
            index.add(toBe);

            index = employeeTable.getCertiIndex().get(asIs.getCerti().toString());
            index.remove(asIs);
            index.add(toBe);
        }
    }

    public void deleteAll(){
        employeeTable.initialize();
    }
}
