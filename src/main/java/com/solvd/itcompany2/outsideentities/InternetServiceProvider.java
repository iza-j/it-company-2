package com.solvd.itcompany2.outsideentities;

import static com.solvd.itcompany2.helpers.GlobalVariable.LOGGER;

public class InternetServiceProvider extends Provider {

    private String country;

    public InternetServiceProvider(String name, String phoneNumber, String country) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.country = country;
    }

    @Override
    public void phoneCall() {
        LOGGER.info("Calling...\n.\n.\n.\nPlease call later. " + this.name + "'s customer service team waits to help you on second mondays of every third month, from 1pm til 2pm.\n");
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}