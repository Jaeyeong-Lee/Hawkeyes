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


class DeleteCommandTest {

    Command<Set<Employee>> deleteCommand;
    List<Employee> fakeEmployeeList;

    @Mock
    private EmployeeTable testEmployeeTable;

    @BeforeEach
    void setUp() {
        deleteCommand = new DeleteCommand();

        //employeeTable 세팅
        fakeEmployeeList = new ArrayList<Employee>();
        fakeEmployeeList.add(
                new Employee("15123099", "VXIHXOTH JHOP", CareerLevel.CL2, "010-3112-2609",
                        "19771211",
                        Certi.ADV));
        fakeEmployeeList.add(
                new Employee("17112609", "FB NTAWR", CareerLevel.CL4, "010-5645-6122", "19861203",
                        Certi.PRO));
        fakeEmployeeList.add(
                new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-1234-6122", "20080718",
                        Certi.ADV));

        testEmployeeTable = EmployeeTable.getInstance();
    }

    @AfterEach
    void tearDown() {
        deleteCommand.employeeDAO.deleteAll();
        deleteCommand = null;
        fakeEmployeeList = null;
        testEmployeeTable = null;
    }

    @Test
    void testExecuteWithEmployeeNumber() {
        // test data 준비
        SearchOption testSearchOption = new SearchOption("employeeNum", "15123099");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "", false);
        deleteCommand.commandOption = testCommandOption;

        // mock setup
        new MockingUtility().mockAllIndexingTables(fakeEmployeeList, testEmployeeTable);
        Whitebox.setInternalState(deleteCommand.employeeDAO, "employeeTable", testEmployeeTable);

        List<Employee> actualReturn = new ArrayList<>(deleteCommand.execute());

        // return 값에 대한 확인(지울 대상이 올바르게 return 되었는지)
        Assertions.assertTrue(actualReturn.size() == 1);        // delete 대상이 된 row 를 받음.
        Assertions.assertEquals(testSearchOption.getCondition(),
                actualReturn.get(0).getEmployeeNumber());

        // 내부 state 에 대한 확인(내부에서 올바르게 지워졌는지)
        EmployeeTable internalTable = (EmployeeTable) Whitebox.getInternalState(
                deleteCommand.employeeDAO, "employeeTable");
        Assertions.assertTrue(internalTable.getEmployeeNumberIndex().size() == 2);
        Assertions.assertNull(
                internalTable.getEmployeeNumberIndex().get(testSearchOption.getCondition()));
    }

    @Test
    void testExecuteWithCode() {
        // test data 준비
        SearchOption testSearchOption = new SearchOption("phoneNum", "6122");
        CommandOption testCommandOption = new CommandOption(testSearchOption, "l", false);
        deleteCommand.commandOption = testCommandOption;

        // mock setup
        new MockingUtility().mockAllIndexingTables(fakeEmployeeList, testEmployeeTable);
        Whitebox.setInternalState(deleteCommand.employeeDAO, "employeeTable", testEmployeeTable);

        List<Employee> actualReturn = new ArrayList<>(deleteCommand.execute());

        // return 값에 대한 확인(지울 대상이 올바르게 return 되었는지)
        Assertions.assertTrue(actualReturn.size() == 2);        // delete 대상이 된 row 를 받음.

        Assertions.assertAll(
                () -> Assertions.assertEquals(testSearchOption.getCondition(),
                        actualReturn.get(0).getLast4DigitOfPhoneNumber()),
                () -> Assertions.assertEquals(testSearchOption.getCondition(),
                        actualReturn.get(1).getLast4DigitOfPhoneNumber())
        );

        // 내부 state 에 대한 확인(내부에서 올바르게 지워졌는지)
        EmployeeTable internalTable = (EmployeeTable) Whitebox.getInternalState(
                deleteCommand.employeeDAO, "employeeTable");
        Assertions.assertTrue(internalTable.getLast4DigitOfPhoneNumberIndex().size() == 1);
        Assertions.assertNull(
                internalTable.getEmployeeNumberIndex().get(testSearchOption.getCondition()));
    }

}