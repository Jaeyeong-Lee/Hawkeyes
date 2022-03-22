package com.samsung.command;

import com.samsung.database.table.EmployeeTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    CommandFactory commandFactory;

    @BeforeEach
    void setup() {
        commandFactory = new CommandFactory();
    }

    @Test
    void 비정상입력_예외_여부() {
        System.out.println(
                "Null / Empty String 입력 시, Command 형식의 클래스를 리턴하며 내부 execute() 수행시 null 이 리턴되어야 함");

        assertAll(
                () -> assertTrue(commandFactory.getCommand("") instanceof Command),
                () -> assertTrue(commandFactory.getCommand(null) instanceof Command)
        );

        assertAll(
                () -> assertNull(commandFactory.getCommand("").execute()),
                () -> assertNull(commandFactory.getCommand(null).execute())
        );
    }

    @Test
    void 올바른_Class_반환_여부() throws Exception {

        assertEquals(AddCommand.class, commandFactory.getCommand("ADD").getClass());

        assertEquals(SearchCommand.class, commandFactory.getCommand("SCH").getClass());

        assertEquals(ModifyCommand.class, commandFactory.getCommand("MOD").getClass());

        assertEquals(DeleteCommand.class, commandFactory.getCommand("DEL").getClass());

    }


    @Test
    void testGetCommandWhenAddParamGiven() {
        String testInput = "ADD, , , ,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV";

        Command actualReturn = commandFactory.getCommand(testInput);
        assertTrue(actualReturn instanceof AddCommand);
    }

    @Test
    void testGetCommandWhenSchParamGiven(){
        String testInput = "SCH,-p,-d, ,birthday,04";

        Command actualReturn = commandFactory.getCommand(testInput);
        assertTrue(actualReturn instanceof SearchCommand);
        assertTrue(actualReturn.getCommandOption().getIsPrint());
        assertTrue(actualReturn.getCommandOption().getCode().equals("d"));
        assertTrue(actualReturn.getCommandOption().getSearchOption().getColumn().equals("birthday"));
        assertTrue(actualReturn.getCommandOption().getSearchOption().getCondition().equals("04"));
    }

    @Test
    void testGetCommandWhenModParamGiven(){
        String testInput = "MOD,-p, , ,name,FB NTAWR,birthday,20050520";

        Command actualReturn = commandFactory.getCommand(testInput);
        assertTrue(actualReturn instanceof ModifyCommand);
        assertTrue(actualReturn.getCommandOption().getIsPrint());
        assertTrue(actualReturn.getCommandOption().getSearchOption().getColumn().equals("name"));
        assertTrue(actualReturn.getCommandOption().getSearchOption().getCondition().equals("FB NTAWR"));
        assertTrue(actualReturn.getCommandOption().getModifyOption().getColumn().equals("birthday"));
        assertTrue(actualReturn.getCommandOption().getModifyOption().getCondition().equals("20050520"));
    }

    @Test
    void testGetCommandWhenDelParamGiven(){
        String testInput = "DEL, , , ,employeeNum,18115040";

        Command actualReturn = commandFactory.getCommand(testInput);
        assertTrue(actualReturn instanceof DeleteCommand);
        assertFalse(actualReturn.getCommandOption().getIsPrint());
        assertTrue(actualReturn.getCommandOption().getSearchOption().getColumn().equals("employeeNum"));
        assertTrue(actualReturn.getCommandOption().getSearchOption().getCondition().equals("18115040"));
    }
}