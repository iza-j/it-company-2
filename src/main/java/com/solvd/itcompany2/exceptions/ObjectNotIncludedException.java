package com.solvd.itcompany2.exceptions;

public class ObjectNotIncludedException extends Exception {

    public ObjectNotIncludedException(String errorMessage) {
        super(errorMessage + "\n");
    }
}