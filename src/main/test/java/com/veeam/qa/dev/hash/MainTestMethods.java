package com.veeam.qa.dev.hash;


import com.veeam.qa.dev.hash.exception.ArgumentsException;
import com.veeam.qa.dev.hash.model.NullRecord;
import com.veeam.qa.dev.hash.model.Record;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


class MainTestMethods {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    private static final Main MAIN = new Main();
    private static final String DIR = "c:\\tmp\\files\\";
    private static final String FILE = "c:\\tmp\\import.txt";

    @BeforeAll
    static void setupVars() throws Exception {
        Field field = MAIN.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(MAIN, DIR);
    }

    @BeforeEach
    void setUpStreams() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void checkArgs() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("checkArgs", String[].class);
        method.setAccessible(true);
        File file = (File) method.invoke(MAIN, (Object) new String[]{FILE, DIR});
        Assertions.assertEquals(new File(FILE), file);
    }

    @Test
    void checkArgsMoreThanTwo() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("checkArgs", String[].class);
        method.setAccessible(true);
        Assertions.assertThrows(ArgumentsException.class, () -> {
            try {
                method.invoke(MAIN, (Object) new String[]{FILE, DIR, "c:\\windows\\temp"});
            } catch (InvocationTargetException e) {
                Assertions.assertEquals("Wrong arguments", e.getTargetException().getMessage());
                throw e.getTargetException();
            }
        });
    }

    @Test
    void checkArgsInputFileNotAFile() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("checkArgs", String[].class);
        method.setAccessible(true);
        Assertions.assertThrows(ArgumentsException.class, () -> {
            try {
                method.invoke(MAIN, (Object) new String[]{"c:\\windows\\temp", DIR});
            } catch (InvocationTargetException e) {
                Assertions.assertEquals("Input file not found", e.getTargetException().getMessage());
                throw e.getTargetException();
            }
        });
    }

    @Test
    void strToFile() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("strToFile", String.class);
        method.setAccessible(true);
        File file = (File) method.invoke(MAIN, "some.file");
        Assertions.assertEquals(new File(DIR + "some.file"), file);
    }

    @Test
    void isHashMatch() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("isHashMatch", Record.class);
        method.setAccessible(true);
        Assertions.assertTrue(
                (boolean) method.invoke(MAIN,
                        new Record(new File(DIR + "файл_06.txt"),
                                Algorithm.SHA256, "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"))
        );
    }

    @Test
    void isHashMatchWithWrongAlgorithm() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("isHashMatch", Record.class);
        method.setAccessible(true);
        Assertions.assertFalse(
                (boolean) method.invoke(MAIN,
                        new Record(new File(DIR + "файл_06.txt"),
                                Algorithm.MD5, "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"))
        );
    }

    @Test
    void isHashMatchWithWrongHash() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("isHashMatch", Record.class);
        method.setAccessible(true);
        Assertions.assertFalse(
                (boolean) method.invoke(MAIN,
                        new Record(new File(DIR + "файл_06.txt"),
                                Algorithm.SHA256, "03b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"))
        );
    }

    @Test
    void printResultOK() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("printResult", Record.class);
        method.setAccessible(true);
        method.invoke(MAIN, new Record(new File(DIR + "файл_06.txt"),
                Algorithm.SHA256, "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"));
        Assertions.assertEquals(
                "файл_06.txt OK\r\n",
                outContent.toString());
    }

    @Test
    void printResultFAIL() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("printResult", Record.class);
        method.setAccessible(true);
        method.invoke(MAIN, new Record(new File(DIR + "файл_06.txt"),
                Algorithm.MD5, "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"));
        Assertions.assertEquals(
                "файл_06.txt FAIL\r\n",
                outContent.toString());
    }

    @Test
    void parseString() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("parseString", String.class);
        method.setAccessible(true);
        Record record = (Record) method.invoke(MAIN, "file_01.bin md5 aaeab83fcc93cd3ab003fa8bfd8d8906");
        Assertions.assertEquals(new Record(new File(DIR + "file_01.bin"), Algorithm.MD5, "aaeab83fcc93cd3ab003fa8bfd8d8906"), record);
    }

    @Test
    void parseStringReturnNullRecord() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("parseString", String.class);
        method.setAccessible(true);
        Record record = (Record) method.invoke(MAIN, "file_01.bi_n md5 aaeab83fcc93cd3ab003fa8bfd8d8906");
        Assertions.assertEquals(new NullRecord().isNull(), record.isNull());
    }

    @Test
    void readInputFile() throws Exception {
        Method method = MAIN.getClass().getDeclaredMethod("readInputFile", File.class);
        method.setAccessible(true);
        method.invoke(MAIN, new File(FILE));
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