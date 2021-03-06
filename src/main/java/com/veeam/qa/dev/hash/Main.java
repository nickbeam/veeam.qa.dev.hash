package com.veeam.qa.dev.hash;

import com.veeam.qa.dev.hash.exception.ArgumentsException;
import com.veeam.qa.dev.hash.model.NullRecord;
import com.veeam.qa.dev.hash.model.Record;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class Main {
    private static String dir;

    public static void main(String[] args) {
        readInputFile(checkArgs(args));
    }

    private static File checkArgs(String[] args) {
        if (args.length == 0 || args.length > 2) {
            throw new ArgumentsException("Wrong arguments");
        }
        File inputFile = new File(args[0]);
        if (!inputFile.isFile()) {
            throw new ArgumentsException("Input file not found");
        }
        if (args.length == 1) {
            dir = inputFile.getParent() + File.separator;
        } else {
            dir = args[1] + File.separator;
        }
        return inputFile;
    }

    private static void readInputFile(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String line;
            while ((line = reader.readLine()) != null) {
                Record record = parseString(line);
                if (!record.isNull()) {
                    printResult(record);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Record parseString(String str) {
        String[] arr = str.trim().replaceAll(" +", " ").split(" ");
        if (arr.length == 3) {
            File file = strToFile(arr[0]);
            if (file.isFile() && file.exists()) {
                return new Record(file, Algorithm.valueOf(arr[1].toUpperCase()), arr[2]);
            }
        }
        return new NullRecord();
    }

    private static File strToFile(String str) {
        File file = new File(dir + str);
        if (!file.exists() || file.isDirectory()) {
            System.out.println(str + " NOT FOUND");
        }
        return file;
    }

    private static boolean isHashMatch(Record record) {
        String hex = "";
        try {
            MessageDigest md = MessageDigest.getInstance(record.getAlgorithm().getName());
            md.update(Files.readAllBytes(record.getFile().toPath()));
            hex = DatatypeConverter.printHexBinary(md.digest());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return record.getHash().equalsIgnoreCase(hex);
    }

    private static void printResult(Record record) {
        System.out.println(record.getFile().getName() + " " + (isHashMatch(record) ? "OK" : "FAIL"));
    }
}
