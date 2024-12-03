package com.solvd.itcompany2.exceptions;

import java.io.IOException;

public class MysteriousObject implements AutoCloseable{

    @Override
    public void close() throws IOException {
        System.out.println("Closing the mysterious object...\n.\n.\n.\nIt's closed!\n");
    }
}