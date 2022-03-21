package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


class DeleteCommandTest {
    Command<Set<Employee>> deleteCommand;
    Set<Employee> fakeEmployeeSet;

    @BeforeEach
    void setUp() {
        deleteCommand = new DeleteCommand();

        //employeeTable 세팅
        fakeEmployeeSet = new HashSet<Employee>();
        fakeEmployeeSet.add(new Employee("15123099", "VXIHXOTH JHOP", CareerLevel.CL2, "010-3112-2609", "19771211", Certi.ADV));
        fakeEmployeeSet.add(new Employee("17112609", "FB NTAWR", CareerLevel.CL4, "010-5645-6122", "19861203", Certi.PRO));
        fakeEmployeeSet.add(new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-4581-2050", "20080718", Certi.ADV));
    }

    @AfterEach
    void tearDown() {
        deleteCommand = null;
    }

    @Test
    void testExecuteWithEmployeeNumber(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("employeeNum", "15123099");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "", false);
        deleteCommand.commandOption = testCommandOption;

        List<Employee> actualReturn = new ArrayList<>(deleteCommand.execute());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);        // delete 대상이 된 row 를 받음.
        Assertions.assertEquals(testSearchOption.getCondition(), actualReturn.get(0).getEmployeeNumber());
        // TODO. 실제로 지워졌는지 db 내 확인
    }

}