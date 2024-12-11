package com.solvd.itcompany2.outsideentities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class InternetServiceProvider extends Provider {

    private static final Logger log = LogManager.getLogger(InternetServiceProvider.class);

    private String country;

    public InternetServiceProvider(String name, String phoneNumber, String country) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.country = country;
    }

    @Override
    public void phoneCall() {
        log.info("Calling...\n.\n.\n.\nPlease call later. " + this.name + "'s customer service team waits to help you on second mondays of every third month, from 1pm til 2pm.\n");
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}