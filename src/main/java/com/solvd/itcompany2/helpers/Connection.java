package com.solvd.itcompany2.helpers;

import java.time.LocalTime;

public abstract class Connection {

    public static String getTime() {
        return LocalTime.now().toString();
    }
}