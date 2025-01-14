package com.solvd.itcompany2.helpers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.solvd.itcompany2.helpers.Connection.getTime;
import static com.solvd.itcompany2.helpers.Formatter.ansiColor;
import static com.solvd.itcompany2.helpers.Formatter.reset;

public class ThreadExtension extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(ThreadExtension.class);

    private final int threadNo;

    public ThreadExtension(int threadNo) {
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        LOGGER.info("Opening thread no." + threadNo);

        for (int i = 1; i <= 6; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                LOGGER.warn(e);
            }

            LOGGER.info(new StringBuilder()
                    .append(ansiColor(30 + i))
                    .append("Loop no. ")
                    .append(i)
                    .append(" in thread no. ")
                    .append(threadNo)
                    .append(".\t Local time now: ")
                    .append(getTime())
                    .append(ansiColor(reset)));
        }

        LOGGER.info("Closing thread no." + threadNo);
    }
}