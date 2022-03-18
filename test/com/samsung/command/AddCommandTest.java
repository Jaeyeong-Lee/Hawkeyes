package com.samsung.command;

import com.samsung.employee.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    Command command;

    @Test
    void 반환_테스트() {

        System.out.println("AddCommand.execute()의 결과는 Employee 객체다.");

        command = new AddCommand();

        command.execute();

        assertEquals(Employee.class, command.execute().getClass());

        Assertions.assertNotNull(actualReturn);

    }



}