package com.solvd.itcompany2.exceptions;

public class NumberEqualToZeroException extends Exception {

    public NumberEqualToZeroException(String errorMessage) {
        super(errorMessage + "\n");
    }
}