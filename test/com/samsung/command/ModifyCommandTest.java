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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        fakeEmployeeList.add(new Employee("15123099", "VXIHXOTH JHOP", CareerLevel.CL2, "010-3112-2609", "19771211", Certi.ADV));
        fakeEmployeeList.add(new Employee("17112609", "FB NTAWR", CareerLevel.CL4, "010-5645-6122", "19861203", Certi.PRO));
        fakeEmployeeList.add(new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-4581-6122", "20080718", Certi.ADV));

        testEmployeeTable = EmployeeTable.getInstance();
    }

    @AfterEach
    void tearDown() {
        modifyCommand = null;
        fakeEmployeeList = null;
        testEmployeeTable = null;
    }

    @Test
    void testExecuteWithNameChangeArgumentWithNotCode(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("name", "FB NTAWR");
        SearchOption testModifyOption = new SearchOption("name", "ABCD EFG");
        CommandOption testCommandOption = new CommandOption(testSearchOption, testModifyOption, "", false);
        modifyCommand.commandOption = testCommandOption;

        // mock setup
        mockAllIndexingTables();
        Whitebox.setInternalState(modifyCommand.employeeDAO, "employeeTable", testEmployeeTable);

        List<Employee> actualReturn = new ArrayList<>(modifyCommand.execute());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 1);        // 변경된 값은 1개
        Assertions.assertEquals(actualReturn.get(0).getName(), testSearchOption.getCondition());      // 내용 비교(변경전 record)

        // 내부 state 에 대한 확인(내부에서 올바르게 변경 되었는지)
        EmployeeTable internalTable = (EmployeeTable)Whitebox.getInternalState(modifyCommand.employeeDAO, "employeeTable");
        Assertions.assertTrue(internalTable.getNameIndex().size() == fakeEmployeeList.size());
        Assertions.assertTrue(internalTable.getNameIndex().get(testModifyOption.getCondition()).size() == 1);
    }

    @Test
    void testExecuteWithCertiChangeArgumentWithCode(){
        // test data 준비
        SearchOption testSearchOption = new SearchOption("phoneNum", "6122");
        SearchOption testModifyOption = new SearchOption("certi", "EX");
        CommandOption testCommandOption = new CommandOption(testSearchOption, testModifyOption, "l", false);
        modifyCommand.commandOption = testCommandOption;

        // mock setup
        mockAllIndexingTables();
        Whitebox.setInternalState(modifyCommand.employeeDAO, "employeeTable", testEmployeeTable);

        List<Employee> actualReturn = new ArrayList<>(modifyCommand.execute());

        // assertion
        Assertions.assertTrue(actualReturn.size() == 2);        // 변경된 값은 2개
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(actualReturn.get(0).getCerti(), Certi.EX),
                () -> Assertions.assertNotEquals(actualReturn.get(1).getCerti(), Certi.EX)
        );      // 변경되기 전 값은 둘다 EX 가 아님

        // 내부 state 에 대한 확인(내부에서 올바르게 변경 되었는지)
        EmployeeTable internalTable = (EmployeeTable)Whitebox.getInternalState(modifyCommand.employeeDAO, "employeeTable");
        Assertions.assertTrue(internalTable.getNameIndex().size() == fakeEmployeeList.size());
        Assertions.assertTrue(internalTable.getCertiIndex().get(testModifyOption.getCondition()).size() == 2);
    }

    void mockAllIndexingTables(){
        for(Employee employee: fakeEmployeeList) {
            Set<Employee> employeeNumberIndexingTable = testEmployeeTable.getEmployeeNumberIndex().computeIfAbsent(employee.getEmployeeNumber(), t -> new HashSet<>());
            employeeNumberIndexingTable.add(employee);

            Set<Employee> employeeNameIndexingTable = testEmployeeTable.getNameIndex().computeIfAbsent(employee.getName(), t -> new HashSet<>());
            employeeNameIndexingTable.add(employee);

            Set<Employee> employeeFirstNameIndexingTable = testEmployeeTable.getFirstNameIndex().computeIfAbsent(employee.getFirstName(), t -> new HashSet<>());
            employeeFirstNameIndexingTable.add(employee);

            Set<Employee> employeeLastNameIndexingTable = testEmployeeTable.getLastNameIndex().computeIfAbsent(employee.getLastName(), t -> new HashSet<>());
            employeeLastNameIndexingTable.add(employee);

            Set<Employee> employeePhoneNumberIndexingTable = testEmployeeTable.getPhoneNumberIndex().computeIfAbsent(employee.getPhoneNumber(), t -> new HashSet<>());
            employeePhoneNumberIndexingTable.add(employee);

            Set<Employee> employeeMiddleDigitOfPhoneNumberIndexingTable = testEmployeeTable.getMiddleDigitOfPhoneNumberIndex().computeIfAbsent(employee.getMiddleDigitOfPhoneNumber(), t -> new HashSet<>());
            employeeMiddleDigitOfPhoneNumberIndexingTable.add(employee);

            Set<Employee> employeeLastDigitOfPhoneNumberIndexingTable = testEmployeeTable.getLast4DigitOfPhoneNumberIndex().computeIfAbsent(employee.getLast4DigitOfPhoneNumber(), t -> new HashSet<>());
            employeeLastDigitOfPhoneNumberIndexingTable.add(employee);

            Set<Employee> employeeBirthIndexingTable = testEmployeeTable.getBirthIndex().computeIfAbsent(employee.getBirthDay(), t -> new HashSet<>());
            employeeBirthIndexingTable.add(employee);

            Set<Employee> employeeYearOfBirthIndexingTable = testEmployeeTable.getYearOfBirthIndex().computeIfAbsent(employee.getYearOfBirth(), t -> new HashSet<>());
            employeeYearOfBirthIndexingTable.add(employee);

            Set<Employee> employeeMonthOfBirthIndexingTable = testEmployeeTable.getMonthOfBirthIndex().computeIfAbsent(employee.getMonthOfBirth(), t -> new HashSet<>());
            employeeMonthOfBirthIndexingTable.add(employee);

            Set<Employee> employeeDayOfBirthIndexingTable = testEmployeeTable.getDayOfBirthIndex().computeIfAbsent(employee.getDayOfBirth(), t -> new HashSet<>());
            employeeDayOfBirthIndexingTable.add(employee);

            Set<Employee> employeeCareerLevelIndexingTable = testEmployeeTable.getCareerLevelIndex().computeIfAbsent(employee.getCareerLevel().toString(), t -> new HashSet<>());
            employeeCareerLevelIndexingTable.add(employee);

            Set<Employee> employeeCertiIndexingTable = testEmployeeTable.getCertiIndex().computeIfAbsent(employee.getCerti().toString(), t -> new HashSet<>());
            employeeCertiIndexingTable.add(employee);
        }
    }
}