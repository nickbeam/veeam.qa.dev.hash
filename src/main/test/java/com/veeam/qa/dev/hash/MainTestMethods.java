package com.veeam.qa.dev.hash;


import com.veeam.qa.dev.hash.model.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


class MainTestMethods {
    private static final String DIR = "c:\\tmp\\files\\";

    @Test
    void checkArgs() {
        //TODO implement test method
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
        Main m = new Main();
        Field field = m.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(m, DIR);
        Method method = Main.class.getDeclaredMethod("parseString", String.class);
        method.setAccessible(true);
        Record record = (Record) method.invoke(m, "file_01.bin md5 aaeab83fcc93cd3ab003fa8bfd8d8906");
        Assertions.assertEquals(new Record(new File(DIR + "file_01.bin"), Algorithm.MD5, "aaeab83fcc93cd3ab003fa8bfd8d8906"), record);
    }
}