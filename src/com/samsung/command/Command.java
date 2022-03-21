package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.EmployeeDAO;
import com.samsung.database.PersistentDAO;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;

public abstract class Command<T> {

    protected CommandOption commandOption;

    public Command() {
        employeeDAO = new EmployeeDAO();
    }

    public Command(CommandOption commandOption) {
        this.commandOption = commandOption;
        employeeDAO = new EmployeeDAO();
    }

    public abstract T execute();

    protected EmployeeDAO employeeDAO;
    protected String commandString;

    public Employee convertCommandOptionToEmployeeParam(){
        Employee retEmployee = new Employee();

        String code = this.commandOption.getCode();
        String column = this.commandOption.getSearchOption().getColumn();
        String value = this.commandOption.getSearchOption().getCondition();

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

    @Override
    public String toString() {
        return "COM";
    }
  
    public CommandOption getCommandOption() {
        return this.commandOption;
    }

    public String getCommandString() {
        return this.commandString;

    }
}
