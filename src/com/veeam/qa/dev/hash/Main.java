package com.veeam.qa.dev.hash;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        checkArgs(args);

        String arg0 = args[0];
        String arg1 = args[1];

//        File inputFile = new File(arg0);
//        File pathToDir = new File(arg1);


    }

    private static void checkArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments");
            System.exit(1);
        } else {
            Set<String> arguments = new HashSet<>(Arrays.asList(args));
            arguments.forEach(System.out::println);
        }
    }
}
