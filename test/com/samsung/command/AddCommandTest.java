package com.samsung.command;

import com.samsung.employee.Employee;
import org.junit.jupiter.api.*;


import static javax.print.attribute.standard.MediaSizeName.A;
import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {

    Command<Employee> addCommand;
    CommandFactory<Employee> factory;

    @BeforeEach
    void setup(){
        addCommand = new AddCommand();
    }

    @AfterEach
    void teardown(){
        addCommand = null;
    }

    @Test
    void 반환_테스트() {

        System.out.println("AddCommand.execute()의 결과는 null이 아니고 Employee Class이다.");

        Employee addResult = addCommand.execute();

        assertNotNull(addResult);
        assertEquals(Employee.class, addResult.getClass());

    }

    @Test
    void 삽입_테스트() {

        // TODO: Mock방법 객체 수준으로 TDD되도록 개선 해보기
        // TODO: -p Option으로도 TC개발 필요할지 팀원들과 논의 해보기

        System.out.println("샘플 데이터 ADD 후 Search결과의 Return이 동일한지 확인한다.");

        addCommand.execute();

        // TODO: factory.getCommand() 사용하지 않고, AddCommand()관련 TC만 수행할 수 있도록 변경 필요 
        assertEquals(factory.getCommand("SCH, , , ,certi,PRO").execute(), addCommand.execute());
        assertEquals(factory.getCommand("SCH, , , ,employeeNum,01122329").execute(), addCommand.execute());

    }



}