package com.samsung.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    CommandFactory commandFactory;

    @BeforeEach
    void setup(){
        commandFactory = new CommandFactory();
    }

    @Test
    void 비정상입력_예외_여부() {

        System.out.println("Null / Empty String 입력 시, 예외 확인");

        assertThrows(Exception.class, ()->commandFactory.getCommand(""));
        assertThrows(Exception.class, ()->commandFactory.getCommand(null));

    }

    @Test
    void 올바른_Class_반환_여부() throws Exception {

        assertEquals(AddCommand.class, commandFactory.getCommand("ADD").getClass());

        assertEquals(SearchCommand.class, commandFactory.getCommand("SCH").getClass());

        assertEquals(ModifyCommand.class, commandFactory.getCommand("MOD").getClass());

        assertEquals(DeleteCommand.class, commandFactory.getCommand("DEL").getClass());

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