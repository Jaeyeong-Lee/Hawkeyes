package com.samsung.option;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.employee.Employee;

public class CommandOption {
    private SearchOption searchOption;
    private SearchOption modifyOption;
    private String code;
    private boolean isPrint;

    public CommandOption(SearchOption searchOption, String code, boolean isPrint){
        this.searchOption = searchOption;
        this.code = code;
        this.isPrint = isPrint;
    }

    public CommandOption(SearchOption searchOption, SearchOption modifyOption, String code, boolean isPrint){
        this.searchOption = searchOption;
        this.modifyOption = modifyOption;
        this.code = code;
        this.isPrint = isPrint;
    }

    public SearchOption getSearchOption() {
        return searchOption;
    }

    private void setSearchOption(SearchOption searchOption) {
        this.searchOption = searchOption;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public SearchOption getModifyOption() {
        return modifyOption;
    }

    private void setModifyOption(SearchOption modifyOption) {
        this.modifyOption = modifyOption;
    }

    public boolean getIsPrint() {
        return isPrint;
    }

    private void setIsPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }

    public Employee convertSearchOptionToEmployee(){
        //TODO. convertSearchOptionToEmployee() 와 convertModifyOptionToEmployee() 의 중복부 refactoring

        Employee retEmployee = new Employee();

        String code = getCode();
        String column = getSearchOption().getColumn();
        String value = getSearchOption().getCondition();

        // 옵션이 없는 경우,
        if (code == null || code.isEmpty()){
            switch(column){
                case "employeeNum":
                    retEmployee.setEmployeeNumber(value);
                    break;
                case "name":
                    retEmployee.setName(value);
                    break;
                case "cl":
                    retEmployee.setCareerLevel(CareerLevel.valueOf(value));
                    break;
                case "phoneNum":
                    retEmployee.setPhoneNumber(value);
                    break;
                case "birthday":
                    retEmployee.setBirthDay(value);
                    break;
                case "certi":
                    retEmployee.setCerti(Certi.valueOf(value));
                    break;
            }
        }
        else{
            switch(code){
                case "f":       // 성명의 이름으로 검색
                    retEmployee.setFirstName(value);
                    break;
                case "y":       // 생년월일의 연도로 검색
                    retEmployee.setYearOfBirth(value);
                    break;
                case "d":       // 생년월일의 일로 검색
                    retEmployee.setDayOfBirth(value);
                    break;
                case "m":       // 생년월일의 월로 검색 or 전화번호 중간 자리로 검색
                    if (column.equals("birthday"))
                        retEmployee.setMonthOfBirth(value);
                    else if (column.equals("phoneNum"))
                        retEmployee.setMiddleDigitOfPhoneNumber(value);
                    break;
                case "l":
                    if (column.equals("name"))
                        retEmployee.setLastName(value);
                    else if (column.equals("phoneNum"))
                        retEmployee.setLast4DigitOfPhoneNumber(value);
                    break;
            }
        }

        return retEmployee;
    }

    public Employee convertModifyOptionToEmployee(){
        Employee retEmployee = new Employee();

        String column = getModifyOption().getColumn();
        String value = getModifyOption().getCondition();

        switch(column){
            case "employeeNum":
                retEmployee.setEmployeeNumber(value);
                break;
            case "name":
                retEmployee.setName(value);
                break;
            case "cl":
                retEmployee.setCareerLevel(CareerLevel.valueOf(value));
                break;
            case "phoneNum":
                retEmployee.setPhoneNumber(value);
                break;
            case "birthday":
                retEmployee.setBirthDay(value);
                break;
            case "certi":
                retEmployee.setCerti(Certi.valueOf(value));
                break;
        }

        return retEmployee;
    }
}
