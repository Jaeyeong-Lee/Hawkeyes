package com.samsung.command;

import com.samsung.database.table.EmployeeTable;
import com.samsung.employee.Employee;
import com.samsung.iomanager.FileIOManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    CommandFactory commandFactory;

    @BeforeEach
    void setup(){
        commandFactory = new CommandFactory();
    }

    @Test
    void 비정상입력_Null반환_여부() {

        System.out.println("Null / Empty String 입력 시, Null 반환 여부 확인");
        
        assertNull(commandFactory.getCommand(""));
        assertNull(commandFactory.getCommand(null));

    }

    @Test
    void 올바른_Class_반환_여부() {

        String inputSample = null;

        inputSample = "ADD, , , ,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV";
        assertEquals(AddCommand.class, commandFactory.getCommand(inputSample).getClass());

        inputSample = "SCH,-p,-d, ,birthday,04";
        assertEquals(SearchCommand.class, commandFactory.getCommand(inputSample).getClass());

        inputSample = "MOD,-p, , ,name,FB NTAWR,birthday,20050520";
        assertEquals(ModifyCommand.class, commandFactory.getCommand(inputSample).getClass());

        inputSample = "DEL, , , ,employeeNum,18115040";
        assertEquals(DeleteCommand.class, commandFactory.getCommand(inputSample).getClass());

    }


    // TODO: 올바른_Class_반환_여부() TC ParameterizedTest 변환 검토, Parameter와 기대 값을 동일하게 넣을 수 있는지 확인   
    @ParameterizedTest
    @ValueSource(strings ={
            "ADD, , , ,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV",
            "SCH,-p,-d, ,birthday,04",
            "MOD,-p, , ,name,FB NTAWR,birthday,20050520",
            "DEL, , , ,employeeNum,18115040",
            })
    void 올바른_Class_반환_여부_Param(String inputLine) {
        
        /*
        System.out.println(inputLine);
        assertEquals(AddCommand.class, commandFactory.getCommand(inputLine).getClass());
        commandFactory.getCommand(inputLine).getClass();
        */
    }


}