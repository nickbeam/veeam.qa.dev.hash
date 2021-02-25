package com.veeam.qa.dev.hash;

import com.veeam.qa.dev.hash.exception.ArgumentsException;

import java.io.File;

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
