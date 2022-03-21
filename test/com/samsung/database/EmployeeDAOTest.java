package com.samsung.database;

import com.samsung.constants.CareerLevel;
import com.samsung.constants.Certi;
import com.samsung.employee.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    private static EmployeeDAO employeeDAO;

    @BeforeEach
    public void beforeEach() {
        employeeDAO = new EmployeeDAO();
        employeeDAO.add(new Employee("18050301", "KYUMOK KIM", CareerLevel.CL2, "010-9777-6055", "19980906", Certi.PRO));
        employeeDAO.add(new Employee("12343254", "YOUNHO LEE", CareerLevel.CL3, "010-1234-6055", "20210410", Certi.ADV));
        employeeDAO.add(new Employee("23423434", "JAEYOUNG KIM", CareerLevel.CL2, "010-4543-5687", "20210805", Certi.ADV));
        employeeDAO.add(new Employee("20384845", "WONKYUNG YUN", CareerLevel.CL1, "010-4543-4561", "19980906", Certi.EX));
        employeeDAO.add(new Employee("56765867", "YOUNGSEUNG JUNG", CareerLevel.CL2, "010-1234-1123", "19800401", Certi.PRO));
        employeeDAO.add(new Employee("67874836", "HYUNGDON JUNG", CareerLevel.CL2, "010-2869-0987", "19811005", Certi.PRO));
        employeeDAO.add(new Employee("78009889", "JAESUCK YU", CareerLevel.CL4, "010-8724-2364", "19920401", Certi.PRO));
        employeeDAO.add(new Employee("34563645", "MYUNGSU PARK", CareerLevel.CL3, "010-8233-5833", "20100815", Certi.PRO));
        employeeDAO.add(new Employee("54687253", "HONGCHUL NO", CareerLevel.CL2, "010-5790-2364", "20080727", Certi.ADV));
        employeeDAO.add(new Employee("86907892", "HA HA", CareerLevel.CL2, "010-0078-5257", "19980906", Certi.ADV));
    }

    @AfterEach
    public void afterEach(){
        employeeDAO.deleteAll();
    }

    public void printAll(String head, Set<Employee> employees) {
        System.out.println(head);
        for (Employee temp : employees) {
            System.out.println(temp.toString());
        }
        System.out.println("");
    }

    @Test
    public void searchByEmployeeNumber() {
        Employee condition = new Employee();
        condition.setEmployeeNumber("23423434");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getEmployeeNumber(), employeeResult.getEmployeeNumber());
        }
    }

    @Test
    public void searchByName() {
        Employee condition = new Employee();
        condition.setName("HYUNGDON JUNG");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getName(), employeeResult.getName());
        }
    }

    @Test
    public void searchByFirstName() {
        Employee condition = new Employee();
        condition.setFirstName("HYUNGDON");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getFirstName(), employeeResult.getFirstName());
        }
    }

    @Test
    public void searchByLastName() {
        Employee condition = new Employee();
        condition.setLastName("LEE");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getLastName(), employeeResult.getLastName());
        }
    }

    @Test
    public void searchByPhoneNumber() {
        Employee condition = new Employee();
        condition.setPhoneNumber("010-2869-0987");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getPhoneNumber(), employeeResult.getPhoneNumber());
        }
    }

    @Test
    public void searchByMiddleDigitOfPhoneNumber() {
        Employee condition = new Employee();
        condition.setMiddleDigitOfPhoneNumber("4543");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getMiddleDigitOfPhoneNumber(), employeeResult.getMiddleDigitOfPhoneNumber());
        }
    }

    @Test
    public void searchByLast4DigitOfPhoneNumber() {
        Employee condition = new Employee();
        condition.setLast4DigitOfPhoneNumber("6055");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getLast4DigitOfPhoneNumber(), employeeResult.getLast4DigitOfPhoneNumber());
        }
    }

    @Test
    public void searchByBirth() {
        Employee condition = new Employee();
        condition.setBirthDay("19800401");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getBirthDay(), employeeResult.getBirthDay());
        }
    }

    @Test
    public void searchByYearOfBirth() {
        Employee condition = new Employee();
        condition.setYearOfBirth("1980");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getYearOfBirth(), employeeResult.getYearOfBirth());
        }
    }

    @Test
    public void searchByMonthOfBirth() {
        Employee condition = new Employee();
        condition.setMonthOfBirth("04");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getMonthOfBirth(), employeeResult.getMonthOfBirth());
        }
    }

    @Test
    public void searchByDayOfBirth() {
        Employee condition = new Employee();
        condition.setDayOfBirth("27");

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getDayOfBirth(), employeeResult.getDayOfBirth());
        }
    }

    @Test
    public void searchByCareerLevel() {
        Employee condition = new Employee();
        condition.setCareerLevel(CareerLevel.CL2);

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getCareerLevel(), employeeResult.getCareerLevel());
        }
    }

    @Test
    public void searchByCerti() {
        Employee condition = new Employee();
        condition.setCerti(Certi.PRO);

        for (Employee employeeResult : employeeDAO.search(condition)) {
            System.out.println(employeeResult.toString());
            assertEquals(condition.getCerti(), employeeResult.getCerti());
        }
    }

    @Test
    void addTest() {
        Employee addCondition = new Employee("34534436", "BOB TRUNK", CareerLevel.CL3, "010-5676-2364", "19900906", Certi.PRO);
        Set<Employee> queryResult;

        queryResult = employeeDAO.search(addCondition);
        int sizeBeforeAdd = queryResult.size();
        printAll("before add", queryResult);

        employeeDAO.add(addCondition);
        queryResult = employeeDAO.search(addCondition);
        int sizeAfterAdd = queryResult.size();
        printAll("after add", queryResult);

        assertEquals(sizeBeforeAdd, sizeAfterAdd - 1);
        assertTrue(queryResult.contains(addCondition));
    }

    @Test
    void deleteTest() {
        Employee deleteCondition = new Employee();
        deleteCondition.setEmployeeNumber("23423434");

        Set<Employee> queryResult;
        queryResult = employeeDAO.search(deleteCondition);
        printAll("before delete", queryResult);

        queryResult = employeeDAO.delete(deleteCondition);
        printAll("deleted row", queryResult);

        assertFalse(queryResult.contains(deleteCondition));
    }

    @Test
    void modifyEmployeeNumberSearchByEmployeNumber() {
        Employee searchCondition = new Employee();
        searchCondition.setEmployeeNumber("23423434");

        Employee modifyCondition = new Employee();
        modifyCondition.setEmployeeNumber("00003434");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getEmployeeNumber(), "23423434");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyEmployeeNumberSearchByName() {
        Employee searchCondition = new Employee();
        searchCondition.setName("WONKYUNG YUN");

        Employee modifyCondition = new Employee();
        modifyCondition.setEmployeeNumber("00003434");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getName(), "WONKYUNG YUN");
            assertEquals(employee.getEmployeeNumber(), "20384845");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getEmployeeNumber(), "00003434");
        }
    }

    @Test
    void modifyName() {
        Employee searchCondition = new Employee();
        searchCondition.setName("WONKYUNG YUN");

        Employee modifyCondition = new Employee();
        modifyCondition.setName("MODIFIED NAME");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getName(), "WONKYUNG YUN");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyFirstName() {
        Employee searchCondition = new Employee();
        searchCondition.setFirstName("JAESUCK");

        Employee modifyCondition = new Employee();
        modifyCondition.setFirstName("MODIFIED");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getFirstName(), "JAESUCK");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyLastName() {
        Employee searchCondition = new Employee();
        searchCondition.setLastName("LEE");

        Employee modifyCondition = new Employee();
        modifyCondition.setLastName("MODIFIED");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getLastName(), "LEE");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyPhoneNumber() {
        Employee searchCondition = new Employee();
        searchCondition.setPhoneNumber("010-4543-5687");

        Employee modifyCondition = new Employee();
        modifyCondition.setPhoneNumber("000-0000-0000");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getPhoneNumber(), "010-4543-5687");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyMiddleDigitOfPhoneNumber() {
        Employee searchCondition = new Employee();
        searchCondition.setMiddleDigitOfPhoneNumber("4543");

        Employee modifyCondition = new Employee();
        modifyCondition.setMiddleDigitOfPhoneNumber("0000");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getMiddleDigitOfPhoneNumber(), "4543");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyLast4DigitOfPhoneNumber() {
        Employee searchCondition = new Employee();
        searchCondition.setLast4DigitOfPhoneNumber("5687");

        Employee modifyCondition = new Employee();
        modifyCondition.setLast4DigitOfPhoneNumber("0000");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getLast4DigitOfPhoneNumber(), "5687");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyBirthDay() {
        Employee searchCondition = new Employee();
        searchCondition.setBirthDay("19980906");

        Employee modifyCondition = new Employee();
        modifyCondition.setBirthDay("00000000");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getBirthDay(), "19980906");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyYearOfBirth() {
        Employee searchCondition = new Employee();
        searchCondition.setYearOfBirth("1980");

        Employee modifyCondition = new Employee();
        modifyCondition.setYearOfBirth("0000");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getYearOfBirth(), "1980");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyMonthOfBirth() {
        Employee searchCondition = new Employee();
        searchCondition.setMonthOfBirth("09");

        Employee modifyCondition = new Employee();
        modifyCondition.setMonthOfBirth("00");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getMonthOfBirth(), "09");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyDayOfBirth() {
        Employee searchCondition = new Employee();
        searchCondition.setDayOfBirth("01");

        Employee modifyCondition = new Employee();
        modifyCondition.setDayOfBirth("00");

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getDayOfBirth(), "01");
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyCareerLevel() {
        Employee searchCondition = new Employee();
        searchCondition.setCareerLevel(CareerLevel.CL3);

        Employee modifyCondition = new Employee();
        modifyCondition.setName("DUMMY DUM");
        modifyCondition.setCareerLevel(CareerLevel.CL2);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getCareerLevel(), CareerLevel.CL3);
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }

    @Test
    void modifyCerti() {
        Employee searchCondition = new Employee();
        searchCondition.setCerti(Certi.PRO);

        Employee modifyCondition = new Employee();
        modifyCondition.setCerti(Certi.ADV);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            assertEquals(employee.getCerti(), Certi.PRO);
        }

        employeeDAO.modify(searchCondition, modifyCondition);

        for (Employee employee : employeeDAO.search(searchCondition)) {
            System.out.println(employee.toString());
            fail();
        }
    }
}