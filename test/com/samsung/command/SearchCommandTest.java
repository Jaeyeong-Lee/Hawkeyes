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

import java.util.*;

class SearchCommandTest {

    Command<Set<Employee>> searchCommand;
    Set<Employee> fakeEmployeeSet;
    Map<String, Set<Employee>> fakeDatabase;

    @BeforeEach
    void setUp() {
        searchCommand = new SearchCommand();

        //employeeTable 세팅
        fakeEmployeeSet = new HashSet<Employee>();
        fakeEmployeeSet.add(new Employee("15123099", "VXIHXOTH JHOP", CareerLevel.CL2, "010-3112-2609", "19771211", Certi.ADV));
        fakeEmployeeSet.add(new Employee("17112609", "FB NTAWR", CareerLevel.CL4, "010-5645-6122", "19861203", Certi.PRO));
        fakeEmployeeSet.add(new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-4581-2050", "20080718", Certi.ADV));

        fakeDatabase = new HashMap<String, Set<Employee>>();
        for (Employee e: fakeEmployeeSet) fakeDatabase.put(e.getEmployeeNumber(), new HashSet<Employee>(Arrays.asList(e)));

    }

    @AfterEach
    void tearDown() {
        searchCommand = null;
    }

    @Test
    void testExecuteWithEmployeeNumber(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("employeeNum", "15123099");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "");
        searchCommand.commandOption = testCommandOption;

        Set<Employee> actualReturn = searchCommand.execute();

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);        // 사번은 고유함
        Assertions.assertEquals(fakeDatabase.get(testSearchOption.getCondition()), actualReturn);       // 올바른 내용을 가지고 왔는지?
    }

    @Test
    void testExecuteWithNotExistData(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("employeeNum", "12345678");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "");
        searchCommand.commandOption = testCommandOption;

        Set<Employee> actualReturn = searchCommand.execute();

        // assertion
        Assertions.assertNull(actualReturn);        // 찾는 데이터가 없을 때의 return: null 로 할지, 빈 Set<Employee> 로 할지 결정 필요
    }

    @Test
    void testExecuteWithCode(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("phoneNum", "5645");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "m");
        searchCommand.commandOption = testCommandOption;

        Set<Employee> actualReturn = searchCommand.execute();

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);        // 사번은 고유함
        Assertions.assertEquals(fakeDatabase.get("17112609"), actualReturn);       // 올바른 내용을 가지고 왔는지?
    }
}