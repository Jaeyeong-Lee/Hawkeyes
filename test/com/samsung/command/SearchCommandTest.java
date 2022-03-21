package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;
import com.samsung.option.CommandOption;
import com.samsung.option.SearchOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.*;
import java.util.stream.Collectors;

class SearchCommandTest {

    Command<Set<Employee>> searchCommand;
    Set<Employee> fakeEmployeeSet;

    @Mock
    private EmployeeTable testEmployeeTable;

    @BeforeEach
    void setUp() {
        searchCommand = new SearchCommand();

        //employeeTable 세팅
        fakeEmployeeSet = new HashSet<Employee>();
        fakeEmployeeSet.add(new Employee("15123099", "VXIHXOTH JHOP", CareerLevel.CL2, "010-3112-2609", "19771211", Certi.ADV));
        fakeEmployeeSet.add(new Employee("17112609", "FB NTAWR", CareerLevel.CL4, "010-5645-6122", "19861203", Certi.PRO));
        fakeEmployeeSet.add(new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-5645-2050", "20080718", Certi.ADV));

        testEmployeeTable = EmployeeTable.getInstance();
    }

    @AfterEach
    void tearDown() {
        searchCommand = null;
    }

    @Test
    void testExecuteWithEmployeeNumber(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("employeeNum", "15123099");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "", false);
        searchCommand.commandOption = testCommandOption;

        // mock setup
        for(Employee employee: fakeEmployeeSet) {
            Set<Employee> searchByEmployeeNumber = testEmployeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), t -> new HashSet<>());
            searchByEmployeeNumber.add(employee);
        }
        Whitebox.setInternalState(searchCommand.employeeDAO, "employeeTable", testEmployeeTable);

        Set<Employee> actualReturn = searchCommand.execute();
        Set<Employee> expectedReturn = fakeEmployeeSet.stream().filter(e-> e.getEmployeeNumber().equals(testSearchOption.getCondition())).collect(Collectors.toSet());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);        // 사번은 고유함
        Assertions.assertEquals(actualReturn, expectedReturn);          // return 값의 내용 비교
    }

    @Test
    void testExecuteWithNotExistData(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("employeeNum", "12345678");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "", false);
        searchCommand.commandOption = testCommandOption;

        Set<Employee> actualReturn = searchCommand.execute();

        // assertion
        Assertions.assertTrue(actualReturn.size() == 0);
    }

    @Test
    void testExecuteWithCode(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("phoneNum", "5645");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "m", false);
        searchCommand.commandOption = testCommandOption;

        // mock setup
        for(Employee employee: fakeEmployeeSet) {
            Set<Employee> searchByMiddleDigitOfPhoneNumber = testEmployeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), t -> new HashSet<>());
            searchByMiddleDigitOfPhoneNumber.add(employee);
        }
        Whitebox.setInternalState(searchCommand.employeeDAO, "employeeTable", testEmployeeTable);

        Set<Employee> actualReturn = searchCommand.execute();
        Set<Employee> expectedReturn = fakeEmployeeSet.stream().filter(e-> e.getMiddleDigitOfPhoneNumber().equals(testSearchOption.getCondition())).collect(Collectors.toSet());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 2);
        Assertions.assertIterableEquals(actualReturn, expectedReturn);     // 올바른 내용을 가지고 왔는지?
    }
}