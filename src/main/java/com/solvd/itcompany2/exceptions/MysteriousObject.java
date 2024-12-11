package com.solvd.itcompany2.exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class MysteriousObject implements AutoCloseable {

    private static final Logger log = LogManager.getLogger(MysteriousObject.class);

    @Override
    public void close() throws IOException {
        log.info("Closing the mysterious object...\n.\n.\n.\nIt's closed!\n");
    }
}