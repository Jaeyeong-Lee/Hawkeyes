package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

import java.util.List;
import java.util.Set;

public class SearchCommand<E> extends Command<Set<Employee>>{

    public SearchCommand() {};

    public SearchCommand(CommandOption option) {
        super(option);
    }

    @Override
    public Set<Employee> execute() {

        // 1. CommandOption -> Employee 객체로 convert
        Employee searchCondition = new Employee();
        String code = commandOption.getCode();
        String column = commandOption.getSearchOption().getColumn();
        String value = commandOption.getSearchOption().getCondition();

        // 옵션이 없는 경우,
        if (code == null || code.isEmpty()){
            switch(column){
                case "employeeNum":
                    searchCondition.setEmployeeNumber(value);
                    break;
                case "name":
                    searchCondition.setName(value);
                    break;
                case "cl":
                    searchCondition.setCareerLevel(CareerLevel.valueOf(value));
                    break;
                case "phoneNum":
                    searchCondition.setPhoneNumber(value);
                    break;
                case "birthday":
                    searchCondition.setBirthDay(value);
                    break;
                case "certi":
                    searchCondition.setCerti(Certi.valueOf(value));
                    break;
            }
        }
        else{
            switch(code){
                case "f":       // 성명의 이름으로 검색
                    searchCondition.setFirstName(value);
                    break;
                case "y":       // 생년월일의 연도로 검색
                    searchCondition.setYearOfBirth(value);
                    break;
                case "d":       // 생년월일의 일로 검색
                    searchCondition.setDayOfBirth(value);
                    break;
                case "m":       // 생년월일의 월로 검색 or 전화번호 중간 자리로 검색
                    if (column.equals("birthday"))
                        searchCondition.setMonthOfBirth(value);
                    else if (column.equals("phoneNum"))
                        searchCondition.setMiddleDigitOfPhoneNumber(value);
                    break;
                case "l":
                    break;
            }
        }

        // 2. EmployeeDAO.search() 수행
        Set<Employee> returnEmp = employeeDAO.search(searchCondition);

        return returnEmp;
    }
}
