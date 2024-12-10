package com.solvd.itcompany2.helpers;

import com.solvd.itcompany2.Main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class GlobalVariable {

    public static final int MULTIPLICAND = 17; // static variable: only 1 copy is created in memory
    public static final int MULTIPLIER = 31; // final variable: can't be changed once it's been initialized

    public static final Logger LOGGER = LogManager.getLogger(Main.class);
}