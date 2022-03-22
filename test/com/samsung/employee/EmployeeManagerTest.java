package com.samsung.employee;

import com.samsung.iomanager.FileIOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeManagerTest {

    FileIOManager fileIOManager;

    @BeforeEach
    void setUp(){
        fileIOManager = new FileIOManager();
        fileIOManager.readInput("input.txt");
    }

    @AfterEach
    void tearDown(){
        fileIOManager = null;
    }

    // @Test
    // void

}