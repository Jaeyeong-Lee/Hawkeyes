package com.samsung.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void isValidFileTest() {

        assertFalse(Main.isValidFile(null));
        assertFalse(Main.isValidFile(""));

        assertFalse(Main.isValidFile("file.shh"));

    }

}