package com.veeam.qa.dev.hash.model;

import com.veeam.qa.dev.hash.Algorithm;

import java.io.File;
import java.util.Objects;

public class Record {
    private File file;
    private Algorithm algorithm;
    private String hash;

    public Record() {

    }

    public Record(File file, Algorithm algorithm, String hash) {
        Objects.requireNonNull(file);
        Objects.requireNonNull(algorithm);
        this.file = file;
        this.algorithm = algorithm;
        this.hash = hash;
    }

    public File getFile() {
        return file;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public String getHash() {
        return hash;
    }

    public boolean isNull() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return file.equals(record.file) && algorithm == record.algorithm && hash.equals(record.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, algorithm, hash);
    }

    @Override
    public String toString() {
        return "Record{" +
                "file=" + file +
                ", algorithm=" + algorithm +
                ", hash='" + hash + '\'' +
                '}';
    }
}
