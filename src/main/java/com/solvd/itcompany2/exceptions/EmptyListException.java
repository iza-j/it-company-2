package com.solvd.itcompany2.exceptions;

public class EmptyListException extends NullPointerException { // https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html

    public EmptyListException(String errorMessage) {
        super(errorMessage + "\n");
    }
}