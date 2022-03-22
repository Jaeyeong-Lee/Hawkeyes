package com.samsung.command;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {

    Command<Set<Employee>> addCommand;
    Set<Employee> fakeEmployeeSet;

    @Mock
    private EmployeeTable testEmployeeTable;

    @BeforeEach
    void setup() {
        addCommand = new AddCommand("ADD, , , ,11125777,TKOQKIS HC,CL1,010-6734-2289,19991001,PRO");

        //employeeTable 세팅
        fakeEmployeeSet = new HashSet<Employee>();
        fakeEmployeeSet.add(
                new Employee("15123099", "VXIHXOTH JHOP", CareerLevel.CL2, "010-3112-2609",
                        "19771211",
                        Certi.ADV));
        fakeEmployeeSet.add(
                new Employee("17112609", "FB NTAWR", CareerLevel.CL4, "010-5645-6122", "19861203",
                        Certi.PRO));
        fakeEmployeeSet.add(
                new Employee("18115040", "TTETHU HBO", CareerLevel.CL3, "010-4581-2050", "20080718",
                        Certi.ADV));

        testEmployeeTable = EmployeeTable.getInstance();
    }

    @AfterEach
    void teardown() {
        addCommand.employeeDAO.deleteAll();
        addCommand = null;
        fakeEmployeeSet = null;
        testEmployeeTable = null;
    }

    @Test
    void returnNullTest() {

        System.out.println("AddCommand.execute()의 결과는 null 이다.");

        Set<Employee> addResult = addCommand.execute();

        assertNull(addResult);

    }

    @Test
    void lineAddCommandExecuteTest() {

        Assertions.assertTrue(testEmployeeTable.getEmployeeNumberIndex().size() == 0);

        addCommand.execute();

        Assertions.assertTrue(testEmployeeTable.getEmployeeNumberIndex().size() == 1);

    }


}