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
            Optional.ofNullable(employee.getEmployeeNumber())
                    .ifPresent(useless -> result.addAll(searchByEmployeeNumber(employee)));
            Optional.ofNullable(employee.getName())
                    .ifPresent(useless -> result.addAll(searchByName(employee)));
            Optional.ofNullable(employee.getFirstName())
                    .ifPresent(useless -> result.addAll(searchByFirstName(employee)));
            Optional.ofNullable(employee.getLastName())
                    .ifPresent(useless -> result.addAll(searchByLastName(employee)));
            Optional.ofNullable(employee.getPhoneNumber())
                    .ifPresent(useless -> result.addAll(searchByPhoneNumber(employee)));
            Optional.ofNullable(employee.getMiddleDigitOfPhoneNumber()).ifPresent(
                    useless -> result.addAll(searchByMiddleDigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getLast4DigitOfPhoneNumber())
                    .ifPresent(useless -> result.addAll(searchByLast4DigitOfPhoneNumber(employee)));
            Optional.ofNullable(employee.getBirthDay())
                    .ifPresent(useless -> result.addAll(searchByBirth(employee)));
            Optional.ofNullable(employee.getYearOfBirth())
                    .ifPresent(useless -> result.addAll(searchByYearOfBirth(employee)));
            Optional.ofNullable(employee.getMonthOfBirth())
                    .ifPresent(useless -> result.addAll(searchByMonthOfBirth(employee)));
            Optional.ofNullable(employee.getDayOfBirth())
                    .ifPresent(useless -> result.addAll(searchByDayOfBirth(employee)));
            Optional.ofNullable(employee.getCareerLevel())
                    .ifPresent(useless -> result.addAll(searchByCareerLevel(employee)));
            Optional.ofNullable(employee.getCerti())
                    .ifPresent(useless -> result.addAll(searchByCerti(employee)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Employee> searchByEmployeeNumber(Employee employee) {
        return employeeTable.getEmployeeNumberIndex().getOrDefault(employee.getEmployeeNumber(), new HashSet<>());
    }

    public Set<Employee> searchByName(Employee employee) {
        return employeeTable.getNameIndex().getOrDefault(employee.getName(), new HashSet<>());
    }

    public Set<Employee> searchByFirstName(Employee employee) {
        return employeeTable.getFirstNameIndex().getOrDefault(employee.getFirstName(), new HashSet<>());
    }

    public Set<Employee> searchByLastName(Employee employee) {
        return employeeTable.getLastNameIndex().getOrDefault(employee.getLastName(), new HashSet<>());
    }

    public Set<Employee> searchByPhoneNumber(Employee employee) {
        return employeeTable.getPhoneNumberIndex().getOrDefault(employee.getPhoneNumber(), new HashSet<>());
    }

    public Set<Employee> searchByMiddleDigitOfPhoneNumber(Employee employee) {
        return employeeTable.getMiddleDigitOfPhoneNumberIndex().getOrDefault(employee.getMiddleDigitOfPhoneNumber(), new HashSet<>());
    }

    public Set<Employee> searchByLast4DigitOfPhoneNumber(Employee employee) {
        return employeeTable.getLast4DigitOfPhoneNumberIndex().getOrDefault(employee.getLast4DigitOfPhoneNumber(), new HashSet<>());
    }

    public Set<Employee> searchByBirth(Employee employee) {
        return employeeTable.getBirthIndex().getOrDefault(employee.getBirthDay(), new HashSet<>());
    }

    public Set<Employee> searchByYearOfBirth(Employee employee) {
        return employeeTable.getYearOfBirthIndex().getOrDefault(employee.getYearOfBirth(), new HashSet<>());
    }

    public Set<Employee> searchByMonthOfBirth(Employee employee) {
        return employeeTable.getMonthOfBirthIndex().getOrDefault(employee.getMonthOfBirth(), new HashSet<>());
    }

    public Set<Employee> searchByDayOfBirth(Employee employee) {
        return employeeTable.getDayOfBirthIndex().getOrDefault(employee.getDayOfBirth(), new HashSet<>());
    }

    public Set<Employee> searchByCareerLevel(Employee employee) {
        return employeeTable.getCareerLevelIndex().getOrDefault(employee.getCareerLevel().toString(), new HashSet<>());
    }

    public Set<Employee> searchByCerti(Employee employee) {
        return employeeTable.getCertiIndex().getOrDefault(employee.getCerti().toString(), new HashSet<>());
    }

    @Override
    public Set<Employee> delete(Employee employee) {
        Set<Employee> employees = null;
        try {
            employees = search(employee);

            // value 삭제
            for (Employee employeeToDelete : employees) {
                employeeTable.getEmployeeNumberIndex().get(employeeToDelete.getEmployeeNumber())
                        .remove(employeeToDelete);
                employeeTable.getNameIndex().get(employeeToDelete.getName())
                        .remove(employeeToDelete);
                employeeTable.getFirstNameIndex().get(employeeToDelete.getFirstName())
                        .remove(employeeToDelete);
                employeeTable.getLastNameIndex().get(employeeToDelete.getLastName())
                        .remove(employeeToDelete);
                employeeTable.getPhoneNumberIndex().get(employeeToDelete.getPhoneNumber())
                        .remove(employeeToDelete);
                employeeTable.getMiddleDigitOfPhoneNumberIndex()
                        .get(employeeToDelete.getMiddleDigitOfPhoneNumber())
                        .remove(employeeToDelete);
                employeeTable.getLast4DigitOfPhoneNumberIndex()
                        .get(employeeToDelete.getLast4DigitOfPhoneNumber())
                        .remove(employeeToDelete);
                employeeTable.getBirthIndex().get(employeeToDelete.getBirthDay())
                        .remove(employeeToDelete);
                employeeTable.getYearOfBirthIndex().get(employeeToDelete.getYearOfBirth())
                        .remove(employeeToDelete);
                employeeTable.getMonthOfBirthIndex().get(employeeToDelete.getMonthOfBirth())
                        .remove(employeeToDelete);
                employeeTable.getDayOfBirthIndex().get(employeeToDelete.getDayOfBirth())
                        .remove(employeeToDelete);
                employeeTable.getCareerLevelIndex()
                        .get(employeeToDelete.getCareerLevel().toString()).remove(employeeToDelete);
                employeeTable.getCertiIndex().get(employeeToDelete.getCerti().toString())
                        .remove(employeeToDelete);
            }

            // value가 비어있는 key 삭제
            for (Employee employeeToDelete : employees) {
                if ((employeeTable.getEmployeeNumberIndex().get(employeeToDelete.getEmployeeNumber()) != null)
                        && employeeTable.getEmployeeNumberIndex().get(employeeToDelete.getEmployeeNumber()).isEmpty()) {
                    employeeTable.getEmployeeNumberIndex()
                            .remove(employeeToDelete.getEmployeeNumber());
                }
                if ((employeeTable.getNameIndex().get(employeeToDelete.getName()) != null) && employeeTable.getNameIndex().get(employeeToDelete.getName()).isEmpty()) {
                    employeeTable.getNameIndex().remove(employeeToDelete.getName());
                }
                if ((employeeTable.getFirstNameIndex().get(employeeToDelete.getFirstName()) != null) && employeeTable.getFirstNameIndex().get(employeeToDelete.getFirstName())
                        .isEmpty()) {
                    employeeTable.getFirstNameIndex().remove(employeeToDelete.getFirstName());
                }
                if ((employeeTable.getLastNameIndex().get(employeeToDelete.getLastName()) != null) && employeeTable.getLastNameIndex().get(employeeToDelete.getLastName())
                        .isEmpty()) {
                    employeeTable.getLastNameIndex().remove(employeeToDelete.getLastName());
                }
                if ((employeeTable.getPhoneNumberIndex().get(employeeToDelete.getPhoneNumber()) != null) && employeeTable.getPhoneNumberIndex().get(employeeToDelete.getPhoneNumber())
                        .isEmpty()) {
                    employeeTable.getPhoneNumberIndex().remove(employeeToDelete.getPhoneNumber());
                }
                if ((employeeTable.getMiddleDigitOfPhoneNumberIndex()
                        .get(employeeToDelete.getMiddleDigitOfPhoneNumber()) != null) && employeeTable.getMiddleDigitOfPhoneNumberIndex()
                        .get(employeeToDelete.getMiddleDigitOfPhoneNumber()).isEmpty()) {
                    employeeTable.getMiddleDigitOfPhoneNumberIndex()
                            .remove(employeeToDelete.getMiddleDigitOfPhoneNumber());
                }
                if ((employeeTable.getLast4DigitOfPhoneNumberIndex()
                        .get(employeeToDelete.getLast4DigitOfPhoneNumber()) != null) && employeeTable.getLast4DigitOfPhoneNumberIndex()
                        .get(employeeToDelete.getLast4DigitOfPhoneNumber()).isEmpty()) {
                    employeeTable.getLast4DigitOfPhoneNumberIndex()
                            .remove(employeeToDelete.getLast4DigitOfPhoneNumber());
                }
                if ((employeeTable.getBirthIndex().get(employeeToDelete.getBirthDay()) != null) && employeeTable.getBirthIndex().get(employeeToDelete.getBirthDay()).isEmpty()) {
                    employeeTable.getBirthIndex().remove(employeeToDelete.getBirthDay());
                }
                if ((employeeTable.getYearOfBirthIndex().get(employeeToDelete.getYearOfBirth()) != null) && employeeTable.getYearOfBirthIndex().get(employeeToDelete.getYearOfBirth())
                        .isEmpty()) {
                    employeeTable.getYearOfBirthIndex().remove(employeeToDelete.getYearOfBirth());
                }
                if ((employeeTable.getMonthOfBirthIndex().get(employeeToDelete.getMonthOfBirth()) != null) && employeeTable.getMonthOfBirthIndex().get(employeeToDelete.getMonthOfBirth())
                        .isEmpty()) {
                    employeeTable.getMonthOfBirthIndex().remove(employeeToDelete.getMonthOfBirth());
                }
                if ((employeeTable.getDayOfBirthIndex().get(employeeToDelete.getDayOfBirth()) != null) && employeeTable.getDayOfBirthIndex().get(employeeToDelete.getDayOfBirth())
                        .isEmpty()) {
                    employeeTable.getDayOfBirthIndex().remove(employeeToDelete.getDayOfBirth());
                }
                if ((employeeTable.getCareerLevelIndex()
                        .get(employeeToDelete.getCareerLevel().toString()) != null) && employeeTable.getCareerLevelIndex()
                        .get(employeeToDelete.getCareerLevel().toString()).isEmpty()) {
                    employeeTable.getCareerLevelIndex()
                            .remove(employeeToDelete.getCareerLevel().toString());
                }
                if ((employeeTable.getCertiIndex().get(employeeToDelete.getCerti().toString()) != null) && employeeTable.getCertiIndex().get(employeeToDelete.getCerti().toString())
                        .isEmpty()) {
                    employeeTable.getCertiIndex().remove(employeeToDelete.getCerti().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Set<Employee> modify(Employee asIsEmployee, Employee toBeEmployee) {
        Set<Employee> asIsEmployees = null;
        EmployeeManager employeeManager = new EmployeeManager();
        try {
            asIsEmployees = search(asIsEmployee);
            for (Employee asIs : asIsEmployees) {
                Employee toBe = employeeManager.overWrite(asIs, toBeEmployee);

                // employeeTable.getEmployeeNumberIndex() = HashMap<String, Set<Employee>>
                // employeeTable.getEmployeeNumberIndex().get() = Set<Employee>

                if (employeeTable.getEmployeeNumberIndex().containsKey(asIs.getEmployeeNumber())) {
                    Set<Employee> temp = employeeTable.getEmployeeNumberIndex()
                            .get(asIs.getEmployeeNumber());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getEmployeeNumber().equals(toBe.getEmployeeNumber())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getEmployeeNumberIndex()
                                .computeIfAbsent(toBe.getEmployeeNumber(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getEmployeeNumberIndex().remove(asIs.getEmployeeNumber());
                    }
                }

                if (employeeTable.getNameIndex().containsKey(asIs.getName())) {
                    Set<Employee> temp = employeeTable.getNameIndex().get(asIs.getName());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getName().equals(toBe.getName())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getNameIndex()
                                .computeIfAbsent(toBe.getName(), useless -> new HashSet<>())
                                .add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getNameIndex().remove(asIs.getName());
                    }
                }

                if (employeeTable.getFirstNameIndex().containsKey(asIs.getFirstName())) {
                    Set<Employee> temp = employeeTable.getFirstNameIndex().get(asIs.getFirstName());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getFirstName().equals(toBe.getFirstName())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getFirstNameIndex()
                                .computeIfAbsent(toBe.getFirstName(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getFirstNameIndex().remove(asIs.getFirstName());
                    }
                }

                if (employeeTable.getLastNameIndex().containsKey(asIs.getLastName())) {
                    Set<Employee> temp = employeeTable.getLastNameIndex().get(asIs.getLastName());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getLastName().equals(toBe.getLastName())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getLastNameIndex().computeIfAbsent(toBe.getLastName(),
                                useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getLastNameIndex().remove(asIs.getLastName());
                    }
                }

                if (employeeTable.getPhoneNumberIndex().containsKey(asIs.getPhoneNumber())) {
                    Set<Employee> temp = employeeTable.getPhoneNumberIndex()
                            .get(asIs.getPhoneNumber());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getPhoneNumber().equals(toBe.getPhoneNumber())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getPhoneNumberIndex()
                                .computeIfAbsent(toBe.getPhoneNumber(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getPhoneNumberIndex().remove(asIs.getPhoneNumber());
                    }
                }

                if (employeeTable.getMiddleDigitOfPhoneNumberIndex()
                        .containsKey(asIs.getMiddleDigitOfPhoneNumber())) {
                    Set<Employee> temp = employeeTable.getMiddleDigitOfPhoneNumberIndex()
                            .get(asIs.getMiddleDigitOfPhoneNumber());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getMiddleDigitOfPhoneNumber()
                            .equals(toBe.getMiddleDigitOfPhoneNumber())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getMiddleDigitOfPhoneNumberIndex()
                                .computeIfAbsent(toBe.getMiddleDigitOfPhoneNumber(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getMiddleDigitOfPhoneNumberIndex()
                                .remove(asIs.getMiddleDigitOfPhoneNumber());
                    }
                }

                if (employeeTable.getLast4DigitOfPhoneNumberIndex()
                        .containsKey(asIs.getLast4DigitOfPhoneNumber())) {
                    Set<Employee> temp = employeeTable.getLast4DigitOfPhoneNumberIndex()
                            .get(asIs.getLast4DigitOfPhoneNumber());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getLast4DigitOfPhoneNumber()
                            .equals(toBe.getLast4DigitOfPhoneNumber())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getLast4DigitOfPhoneNumberIndex()
                                .computeIfAbsent(toBe.getLast4DigitOfPhoneNumber(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getLast4DigitOfPhoneNumberIndex()
                                .remove(asIs.getLast4DigitOfPhoneNumber());
                    }
                }

                if (employeeTable.getBirthIndex().containsKey(asIs.getBirthDay())) {
                    Set<Employee> temp = employeeTable.getBirthIndex().get(asIs.getBirthDay());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getBirthDay().equals(toBe.getBirthDay())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getBirthIndex().computeIfAbsent(toBe.getBirthDay(),
                                useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getBirthIndex().remove(asIs.getBirthDay());
                    }
                }

                if (employeeTable.getYearOfBirthIndex().containsKey(asIs.getYearOfBirth())) {
                    Set<Employee> temp = employeeTable.getYearOfBirthIndex()
                            .get(asIs.getYearOfBirth());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getYearOfBirth().equals(toBe.getYearOfBirth())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getYearOfBirthIndex()
                                .computeIfAbsent(toBe.getYearOfBirth(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getYearOfBirthIndex().remove(asIs.getYearOfBirth());
                    }
                }

                if (employeeTable.getMonthOfBirthIndex().containsKey(asIs.getMonthOfBirth())) {
                    Set<Employee> temp = employeeTable.getMonthOfBirthIndex()
                            .get(asIs.getMonthOfBirth());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getMonthOfBirth().equals(toBe.getMonthOfBirth())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getMonthOfBirthIndex()
                                .computeIfAbsent(toBe.getMonthOfBirth(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getMonthOfBirthIndex().remove(asIs.getMonthOfBirth());
                    }
                }

                if (employeeTable.getDayOfBirthIndex().containsKey(asIs.getDayOfBirth())) {
                    Set<Employee> temp = employeeTable.getDayOfBirthIndex()
                            .get(asIs.getDayOfBirth());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getDayOfBirth().equals(toBe.getDayOfBirth())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getDayOfBirthIndex()
                                .computeIfAbsent(toBe.getDayOfBirth(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getDayOfBirthIndex().remove(asIs.getDayOfBirth());
                    }
                }

                if (employeeTable.getCareerLevelIndex()
                        .containsKey(asIs.getCareerLevel().toString())) {
                    Set<Employee> temp = employeeTable.getCareerLevelIndex()
                            .get(asIs.getCareerLevel().toString());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getCareerLevel().toString().equals(toBe.getCareerLevel().toString())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getCareerLevelIndex()
                                .computeIfAbsent(toBe.getCareerLevel().toString(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getCareerLevelIndex()
                                .remove(asIs.getCareerLevel().toString());
                    }
                }

                if (employeeTable.getCertiIndex().containsKey(asIs.getCerti().toString())) {
                    Set<Employee> temp = employeeTable.getCertiIndex()
                            .get(asIs.getCerti().toString());
                    if (temp.contains(asIs)) {
                        temp.remove(asIs);
                    }

                    if (asIs.getCerti().toString().equals(toBe.getCerti().toString())) {
                        temp.add(toBe); // key가 변하지 않을 경우
                    } else {
                        employeeTable.getCertiIndex()
                                .computeIfAbsent(toBe.getCerti().toString(),
                                        useless -> new HashSet<>()).add(toBe);// key가 변할 경우
                    }

                    if (temp.size() == 0) {
                        employeeTable.getCertiIndex().remove(asIs.getCerti().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asIsEmployees;
    }

    public void deleteAll() {
        employeeTable.initialize();
    }
}
