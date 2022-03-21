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
import java.util.List;
import java.util.Set;

class ModifyCommandTest {
    Command<Set<Employee>> modifyCommand;
    List<Employee> fakeEmployeeList;

    @BeforeEach
    void setUp() {
        modifyCommand = new ModifyCommand();

        //employeeTable 세팅
        fakeEmployeeList = new ArrayList<Employee>();
        fakeEmployeeList.add(new Employee("15123099", "VXIHXOTH JHOP", CareerLevel.CL2, "010-3112-2609", "19771211", Certi.ADV));
        fakeEmployeeList.add(new Employee("17112609", "FB NTAWR", CareerLevel.CL4, "010-5645-6122", "19861203", Certi.PRO));
        fakeEmployeeList.add(new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-4581-2050", "20080718", Certi.ADV));
    }

    @AfterEach
    void tearDown() {
        modifyCommand = null;
    }

    @Test
    void testExecuteWithNameChangeArgumentWithNotCode(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("name", "FB NTAWR");
        SearchOption testModifyOption = new SearchOption("name", "ABCD EFG");
        CommandOption testCommandOption = new CommandOption(testSearchOption, testModifyOption, "", false);
        modifyCommand.commandOption = testCommandOption;

        List<Employee> actualReturn = new ArrayList<>(modifyCommand.execute());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);        // 사번은 고유함
        Assertions.assertEquals(actualReturn.get(0).getName(), testModifyOption.getCondition());      // 내용 비교
    }

    @Test
    void testExecuteWithCertiChangeArgumentWithCode(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("phoneNum", "6122");
        SearchOption testModifyOption = new SearchOption("certi", "EX");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "l", false);
        modifyCommand.commandOption = testCommandOption;

        List<Employee> actualReturn = new ArrayList<>(modifyCommand.execute());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);
        Assertions.assertEquals(actualReturn.get(0).getCerti(), Certi.EX);
    }
}