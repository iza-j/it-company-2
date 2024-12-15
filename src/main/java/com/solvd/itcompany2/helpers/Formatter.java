package com.solvd.itcompany2.helpers;

public abstract class Formatter {

    public static final String reset =      "0";
    public static final String bold =       "1";

    public static final String blackFG =    "30";
    public static final String yellowFG =   "33";
    public static final String cyanFG =     "36";

    public static final String blackBG =    "40";
    public static final String purpleBG =   "45";

    public static String ansiColor(String code) { // static method: you don't have to instantiate any objects in order to use it
        return new StringBuilder()
                .append("\u001B[")
                .append(code)
                .append("m")
                .toString();
    }

    public static String ansiColor(int code) { // static method: you don't have to instantiate any objects in order to use it
        return new StringBuilder()
                .append("\u001B[")
                .append(code)
                .append("m")
                .toString();
    }

    public static String ansiColor(String code, String code2) {
        return new StringBuilder()
                .append("\u001B[")
                .append(code)
                .append(";")
                .append(code2)
                .append("m")
                .toString();
    }

    public static String ansiColor(String code, String code2, String code3) {
        return new StringBuilder()
                .append("\u001B[")
                .append(code)
                .append(";")
                .append(code2)
                .append(";")
                .append(code3)
                .append("m")
                .toString();
    }

    public static String formatHeader(String text) {
        return new StringBuilder()
                .append("\n")
                .append(ansiColor(blackFG, purpleBG, bold))
                .append(" --- ")
                .append(text)
                .append(" --- ")
                .append(ansiColor(reset))
                .append("\n")
                .toString();
    }
}