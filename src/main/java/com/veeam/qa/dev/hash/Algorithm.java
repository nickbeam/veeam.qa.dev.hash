package com.veeam.qa.dev.hash;

public enum Algorithm {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256");

    private final String name;

    Algorithm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
