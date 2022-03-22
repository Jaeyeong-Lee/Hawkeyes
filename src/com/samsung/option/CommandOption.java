package com.samsung.option;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.constants.ConstEmployee;
import com.samsung.constants.ConstOption;
import com.samsung.employee.Employee;

public class CommandOption {

    private SearchOption searchOption;
    private SearchOption modifyOption;
    private String code;
    private boolean isPrint;

    public CommandOption(SearchOption searchOption, String code, boolean isPrint) {
        this.searchOption = searchOption;
        this.code = code;
        this.isPrint = isPrint;
    }

    public CommandOption(SearchOption searchOption, SearchOption modifyOption, String code,
        boolean isPrint) {
        this.searchOption = searchOption;
        this.modifyOption = modifyOption;
        this.code = code;
        this.isPrint = isPrint;
    }

    public SearchOption getSearchOption() {
        return searchOption;
    }

    public String getCode() {
        return code;
    }

    public SearchOption getModifyOption() {
        return modifyOption;
    }

    public boolean getIsPrint() {
        return isPrint;
    }

    public Employee convertSearchOptionToEmployee() {
        String code = getCode();

        if (code == null || code.isEmpty()) {
            return setEmployeeColumnAndValueWithoutCode(getSearchOption().getColumn(),
                getSearchOption().getCondition());
        } else {
            return setEmployeeColumnAndValueWithCode(getSearchOption().getColumn(),
                getSearchOption().getCondition(), code);
        }
    }

    public Employee convertModifyOptionToEmployee() {
        return setEmployeeColumnAndValueWithoutCode(getModifyOption().getColumn(),
            getModifyOption().getCondition());
    }

    private Employee setEmployeeColumnAndValueWithoutCode(String column, String value) {
        Employee retEmployee = new Employee();

        switch (column) {
            case ConstEmployee.employeeNum:
                retEmployee.setEmployeeNumber(value);
                break;
            case ConstEmployee.name:
                retEmployee.setName(value);
                break;
            case ConstEmployee.cl:
                retEmployee.setCareerLevel(CareerLevel.valueOf(value));
                break;
            case ConstEmployee.phoneNum:
                retEmployee.setPhoneNumber(value);
                break;
            case ConstEmployee.birthday:
                retEmployee.setBirthDay(value);
                break;
            case ConstEmployee.certi:
                retEmployee.setCerti(Certi.valueOf(value));
                break;
        }

        return retEmployee;
    }

    private Employee setEmployeeColumnAndValueWithCode(String column, String value, String code) {
        Employee retEmployee = new Employee();

        switch (code) {
            case ConstOption.firstName:
                retEmployee.setFirstName(value);
                break;
            case ConstOption.yearOfBirth:
                retEmployee.setYearOfBirth(value);
                break;
            case ConstOption.dayOfBirth:
                retEmployee.setDayOfBirth(value);
                break;
            case ConstOption.monthOfBirth:
                if (column.equals(ConstEmployee.birthday)) {
                    retEmployee.setMonthOfBirth(value);
                } else if (column.equals(ConstEmployee.phoneNum)) {
                    retEmployee.setMiddleDigitOfPhoneNumber(value);
                }
                break;
            case ConstOption.lastName:
                if (column.equals(ConstEmployee.name)) {
                    retEmployee.setLastName(value);
                } else if (column.equals(ConstEmployee.phoneNum)) {
                    retEmployee.setLast4DigitOfPhoneNumber(value);
                }
                break;
        }

        return retEmployee;
    }
}
