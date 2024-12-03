package com.solvd.itcompany2.exceptions;

public class NegativeNumberException extends Exception {

    public NegativeNumberException(String errorMessage) {
        super(errorMessage + "\n");
    }
}