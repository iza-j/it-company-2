package com.solvd.itcompany2.helpers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.solvd.itcompany2.helpers.Formatter.*;

public class ThreadRunnable implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(ThreadRunnable.class);

    private final int threadNo;

    public ThreadRunnable(int threadNo) {
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 15; i++) {
            LOGGER.info(new StringBuilder()
                    .append(ansiColor(blackFG, whiteBG))
                    .append(" Active threads: ")
                    .append(Thread.activeCount())
                    .append(" ")
                    .append(ansiColor(reset)));

            try {
                Thread.sleep(460);
            } catch (Exception e) {
                LOGGER.warn(e);
            }
        }
    }
}