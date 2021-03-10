package com.veeam.qa.dev.hash;


import com.veeam.qa.dev.hash.model.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


class MainTestMethods {
    private static final Main MAIN = new Main();
    private static final String DIR = "c:\\tmp\\files\\";
    private static final String FILE = "c:\\tmp\\import.txt";

    @BeforeAll
    static void setupVars() throws Exception {
        Field field = MAIN.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(MAIN, DIR);
    }

    @Test
    void checkArgs() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("checkArgs", String[].class);
        method.setAccessible(true);
        File file = (File) method.invoke(MAIN, (Object) new String[]{FILE, DIR});
        Assertions.assertEquals(new File(FILE), file);
    }

    @Test
    void readInputFile() {
        //TODO implement test method
    }

    @Test
    void strToFile() {
        //TODO implement test method
    }

    @Test
    void isHashMatch() {
        //TODO implement test method
    }

    @Test
    void printResult() {
        //TODO implement test method
    }

    @Test
    void parseString() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("parseString", String.class);
        method.setAccessible(true);
        Record record = (Record) method.invoke(MAIN, "file_01.bin md5 aaeab83fcc93cd3ab003fa8bfd8d8906");
        Assertions.assertEquals(new Record(new File(DIR + "file_01.bin"), Algorithm.MD5, "aaeab83fcc93cd3ab003fa8bfd8d8906"), record);
    }
}