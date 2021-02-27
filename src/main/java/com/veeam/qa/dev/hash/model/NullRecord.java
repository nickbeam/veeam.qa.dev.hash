package com.veeam.qa.dev.hash.model;

import com.veeam.qa.dev.hash.Algorithm;

import java.io.File;

public class NullRecord extends Record {
    public NullRecord() {
        super();
    }

    @Override
    public File getFile() {
        return new File("");
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.MD5;
    }

    @Override
    public String getHash() {
        return "";
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
