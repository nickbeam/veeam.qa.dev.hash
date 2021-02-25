package com.veeam.qa.dev.hash;

import com.veeam.qa.dev.hash.exception.StorageException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class MainFile {
    public static void main(String[] args) {
        File filePath = new File("./.gitignore");
        try {
            System.out.println(filePath.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File dir = new File("./src/main/java/com/veeam"); // /webapp
        getDirectoryFileNames(dir, "");
    }

    private static void getDirectoryFileNames(File dir, String space) {
        File[] files = dir.listFiles();
        if (files == null) {
            throw new StorageException(dir.getName() + " is not directory, or IO Error");
        }
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(space + file.getName());
            } else {
                System.out.println(space + "[" + file.getName() + "]");
                getDirectoryFileNames(file, space + "    ");
            }
        }
    }
}
