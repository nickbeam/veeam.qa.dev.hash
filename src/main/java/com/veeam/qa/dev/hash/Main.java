package com.veeam.qa.dev.hash;

import com.veeam.qa.dev.hash.exception.ArgumentsException;
import com.veeam.qa.dev.hash.model.Record;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static File inputFile;
    private static File dir;

    public static void main(String[] args) {
        checkArgs(args);
        readInputFile(inputFile);
    }

    private static void checkArgs(String[] args) {
        if (args.length == 0 || args.length > 2) {
            throw new ArgumentsException("Wrong arguments");
        }
        inputFile = new File(args[0]);
        if (!inputFile.isFile()) {
            throw new ArgumentsException("Input file not found");
        }
        if (args.length == 1) {
            dir = new File("./");
        } else {
            dir = new File(args[1]);
        }
    }

    private static void readInputFile(File file) {
        //TODO implement read input file
        Path path = Paths.get(inputFile.getAbsolutePath());
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Record parseString(String str) {
        //TODO implement parse string
        return null;
    }

    private static boolean isHashMatch(File file, Algorithm alg, String hash) {
        //TODO implement check hash file
        return false;
    }

    private static boolean isFileExist(String str) {
        //TODO implement check file exist
        return false;
    }
}
