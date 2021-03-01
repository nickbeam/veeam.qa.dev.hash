package com.veeam.qa.dev.hash;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class MainTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void hash() {
        Main.main(new String[]{"c:\\tmp\\import.txt", "c:\\tmp\\files"});
        Assertions.assertEquals(
                "file_01.bin FAIL\r\n" +
                "file_02.bin FAIL\r\n" +
                "file_03.bin NOT FOUND\r\n" +
                "файл_04.txt OK\r\n" +
                "файл_04.txt FAIL\r\n" +
                "файл_05.txt FAIL\r\n" +
                "файл_06.txt OK\r\n",
                outContent.toString());
    }
}