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

class ModifyCommandTest {

    Command<Set<Employee>> modifyCommand;
    List<Employee> fakeEmployeeList;

    @Mock
    private EmployeeTable testEmployeeTable;

    @BeforeEach
    void setUp() {
        modifyCommand = new ModifyCommand();

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
                new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-4581-6122", "20080718",
                        Certi.ADV));

        testEmployeeTable = EmployeeTable.getInstance();
    }

    @AfterEach
    void tearDown() {
        modifyCommand.employeeDAO.deleteAll();
        modifyCommand = null;
        fakeEmployeeList = null;
        testEmployeeTable = null;
    }

    @Test
    void testExecuteWithNameChangeArgumentWithNotCode() {
        // test data 준비
        SearchOption testSearchOption = new SearchOption("name", "FB NTAWR");
        SearchOption testModifyOption = new SearchOption("name", "ABCD EFG");
        CommandOption testCommandOption = new CommandOption(testSearchOption, testModifyOption, "",
                false);
        modifyCommand.commandOption = testCommandOption;

        // mock setup
        new MockingUtility().mockAllIndexingTables(fakeEmployeeList, testEmployeeTable);
        Whitebox.setInternalState(modifyCommand.employeeDAO, "employeeTable", testEmployeeTable);

        List<Employee> actualReturn = new ArrayList<>(modifyCommand.execute());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);        // 변경된 값은 1개
        Assertions.assertEquals(actualReturn.get(0).getName(),
                testSearchOption.getCondition());      // 내용 비교(변경전 record)

        // 내부 state 에 대한 확인(내부에서 올바르게 변경 되었는지)
        EmployeeTable internalTable = (EmployeeTable) Whitebox.getInternalState(
                modifyCommand.employeeDAO, "employeeTable");
        Assertions.assertTrue(internalTable.getNameIndex().size() == fakeEmployeeList.size());
        Assertions.assertTrue(
                internalTable.getNameIndex().get(testModifyOption.getCondition()).size() == 1);
    }

    @Test
    void testExecuteWithCertiChangeArgumentWithCode() {
        // test data 준비
        SearchOption testSearchOption = new SearchOption("phoneNum", "6122");
        SearchOption testModifyOption = new SearchOption("certi", "EX");
        CommandOption testCommandOption = new CommandOption(testSearchOption, testModifyOption, "l",
                false);
        modifyCommand.commandOption = testCommandOption;

        // mock setup
        new MockingUtility().mockAllIndexingTables(fakeEmployeeList, testEmployeeTable);
        Whitebox.setInternalState(modifyCommand.employeeDAO, "employeeTable", testEmployeeTable);

        List<Employee> actualReturn = new ArrayList<>(modifyCommand.execute());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 2);        // 변경된 값은 2개
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(actualReturn.get(0).getCerti(), Certi.EX),
                () -> Assertions.assertNotEquals(actualReturn.get(1).getCerti(), Certi.EX)
        );      // 변경되기 전 값은 둘다 EX 가 아님

        // 내부 state 에 대한 확인(내부에서 올바르게 변경 되었는지)
        EmployeeTable internalTable = (EmployeeTable) Whitebox.getInternalState(
                modifyCommand.employeeDAO, "employeeTable");
        Assertions.assertTrue(internalTable.getNameIndex().size() == fakeEmployeeList.size());
        Assertions.assertTrue(
                internalTable.getCertiIndex().get(testModifyOption.getCondition()).size() == 2);
    }

}