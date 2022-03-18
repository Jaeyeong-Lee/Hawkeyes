package com.samsung.database;

import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.*;

public class EmployeeDAO extends PersistentDAO<Employee> {
    EmployeeTable employeeTable = EmployeeTable.getInstance();

    @Override
    public int add(Employee employee) {
        try {
            Set<Employee> searchByEmployeeNumber = employeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), t -> new HashSet<>());
            searchByEmployeeNumber.add(employee);

            Set<Employee> searchByFullName = employeeTable.getNameIndex().computeIfAbsent(employee.getName(), t -> new HashSet<>());
            searchByFullName.add(employee);

            Set<Employee> searchByFirstName = employeeTable.getFirstNameIndex().computeIfAbsent(employee.getFirstName(), t -> new HashSet<>());
            searchByFirstName.add(employee);

            Set<Employee> searchByLastName = employeeTable.getLastNameIndex().computeIfAbsent(employee.getLastName(), t -> new HashSet<>());
            searchByLastName.add(employee);

            Set<Employee> searchByFullPhoneNumber = employeeTable.getPhoneNumberIndex().computeIfAbsent(employee.getPhoneNumber(), t -> new HashSet<>());
            searchByFullPhoneNumber.add(employee);

            Set<Employee> searchByMiddleDigitOfPhoneNumber = employeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), t -> new HashSet<>());
            searchByMiddleDigitOfPhoneNumber.add(employee);

            Set<Employee> searchByLast4DigitOfPhoneNumber = employeeTable.getLast4DigitOfPhoneNumberIndex().computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), t -> new HashSet<>());
            searchByLast4DigitOfPhoneNumber.add(employee);

            Set<Employee> searchByFullBirth = employeeTable.getBirthIndex().computeIfAbsent(employee.getBirthDay(), t -> new HashSet<>());
            searchByFullBirth.add(employee);

            Set<Employee> searchByYearBirth = employeeTable.getYearOfBirthIndex().computeIfAbsent(employee.getYearOfBirth(), t -> new HashSet<>());
            searchByYearBirth.add(employee);

            Set<Employee> searchByMonthBirth = employeeTable.getMonthOfBirthIndex().computeIfAbsent(employee.getMonthOfBirth(), t -> new HashSet<>());
            searchByMonthBirth.add(employee);

            Set<Employee> searchByDayBirth = employeeTable.getDayOfBirthIndex().computeIfAbsent(employee.getDayOfBirth(), t -> new HashSet<>());
            searchByDayBirth.add(employee);

            Set<Employee> searchByCareer = employeeTable.getCareerLevelIndex().computeIfAbsent(employee.getCareerLevel().toString(), t -> new HashSet<>());
            searchByCareer.add(employee);

            Set<Employee> searchByCerti = employeeTable.getCertiIndex().computeIfAbsent(employee.getCerti().toString(), t -> new HashSet<>());
            searchByCerti.add(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Set<Employee> search(Employee employee) {
        Set<Employee> result = new HashSet<>();

        if (employee.getEmployeeNumber() != null) {
            result.addAll(searchByEmployeeNumber(employee));
        }
        if (employee.getName() != null) {
            result.addAll(searchByName(employee));
        }
        if (employee.getFirstName() != null) {
            result.addAll(searchByFirstName(employee));
        }
        if (employee.getLastName() != null) {
            result.addAll(searchByLastName(employee));
        }
        if (employee.getPhoneNumber() != null) {
            result.addAll(searchByPhoneNumber(employee));
        }
        if (employee.getMiddleDigitOfPhoneNumber() != null) {
            result.addAll(searchByMiddleDigitOfPhoneNumber(employee));
        }
        if (employee.getLast4DigitOfPhoneNumber() != null) {
            result.addAll(searchByLast4DigitOfPhoneNumber(employee));
        }
        if (employee.getBirthDay() != null) {
            result.addAll(searchByBirth(employee));
        }
        if (employee.getYearOfBirth() != null) {
            result.addAll(searchByYearOfBirth(employee));
        }
        if (employee.getMonthOfBirth() != null) {
            result.addAll(searchByMonthOfBirth(employee));
        }
        if (employee.getDayOfBirth() != null) {
            result.addAll(searchByDayOfBirth(employee));
        }
        if (employee.getCareerLevel() != null) {
            result.addAll(searchByCareerLevel(employee));
        }
        if (employee.getCerti() != null) {
            result.addAll(searchByCerti(employee));
        }

        return result;
    }

    public Set<Employee> searchByEmployeeNumber(Employee employee) {
        return employeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), t -> new HashSet<>());
    }

    public Set<Employee> searchByName(Employee employee) {
        return employeeTable.getNameIndex().computeIfAbsent(employee.getName(), t -> new HashSet<>());
    }

    public Set<Employee> searchByFirstName(Employee employee) {
        return employeeTable.getFirstNameIndex().computeIfAbsent(employee.getFirstName(), t -> new HashSet<>());
    }

    public Set<Employee> searchByLastName(Employee employee) {
        return employeeTable.getLastNameIndex().computeIfAbsent(employee.getLastName(), t -> new HashSet<>());
    }

    public Set<Employee> searchByPhoneNumber(Employee employee) {
        return employeeTable.getPhoneNumberIndex().computeIfAbsent(employee.getPhoneNumber(), t -> new HashSet<>());
    }

    public Set<Employee> searchByMiddleDigitOfPhoneNumber(Employee employee) {
        return employeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), t -> new HashSet<>());
    }

    public Set<Employee> searchByLast4DigitOfPhoneNumber(Employee employee) {
        return employeeTable.getLast4DigitOfPhoneNumberIndex().computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), t -> new HashSet<>());
    }

    public Set<Employee> searchByBirth(Employee employee) {
        return employeeTable.getBirthIndex().computeIfAbsent(employee.getBirthDay(), t -> new HashSet<>());
    }

    public Set<Employee> searchByYearOfBirth(Employee employee) {
        return employeeTable.getYearOfBirthIndex().computeIfAbsent(employee.getYearOfBirth(), t -> new HashSet<>());
    }

    public Set<Employee> searchByMonthOfBirth(Employee employee) {
        return employeeTable.getMonthOfBirthIndex().computeIfAbsent(employee.getMonthOfBirth(), t -> new HashSet<>());
    }

    public Set<Employee> searchByDayOfBirth(Employee employee) {
        return employeeTable.getDayOfBirthIndex().computeIfAbsent(employee.getDayOfBirth(), t -> new HashSet<>());
    }

    public Set<Employee> searchByCareerLevel(Employee employee) {
        return employeeTable.getCareerLevelIndex().computeIfAbsent(employee.getCareerLevel().toString(), t -> new HashSet<>());
    }

    public Set<Employee> searchByCerti(Employee employee) {
        return employeeTable.getCertiIndex().computeIfAbsent(employee.getCerti().toString(), t -> new HashSet<>());
    }

    @Override
    public Set<Employee> delete(Employee employee) {
        //TODO
        // delete되기 전 collection을 return 하도록 수정해야 한다.
        Set<Employee> employees = search(employee);
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
        return employees;
    }

    @Override
    public Set<Employee> modify(Employee asIsEmployee, Employee toBeEmployee) {
        return null;
    }
}
