package com.solvd.itcompany2.helpers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public enum Territory {

    ANTARCTICA("Antarctica/Troll"),
    CALIFORNIA("US/Pacific"),
    JAPAN("Japan"),
    POLAND("Poland"),
    PORTUGAL("Portugal"),
    SOUTH_KOREA("Asia/Seoul");

    private final String timeZone; // https://mkyong.com/java8/java-display-all-zoneid-and-its-utc-offset/

    Territory(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeDateHour() {
        return LocalDateTime
                .now(ZoneId.of(this.getTimeZone()))
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm"));
    }

    public String getTimeHourDate() {
        return LocalDateTime
                .now(ZoneId.of(this.getTimeZone()))
                .format(DateTimeFormatter.ofPattern("HH:mm, MMMM d"));
    }

    public String getTimeZone() {
        return timeZone;
    }
}