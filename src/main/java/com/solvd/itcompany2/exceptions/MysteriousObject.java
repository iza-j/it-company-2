package com.solvd.itcompany2.exceptions;

import java.io.IOException;

import static com.solvd.itcompany2.helpers.GlobalVariable.LOGGER;

public class MysteriousObject implements AutoCloseable{

    @Override
    public void close() throws IOException {
        LOGGER.info("Closing the mysterious object...\n.\n.\n.\nIt's closed!\n");
    }
}