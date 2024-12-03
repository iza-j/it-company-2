package com.solvd.itcompany2.outsideentities;

import com.solvd.itcompany2.projectresources.Stakeholder;

public class Client implements Stakeholder {

    private String name;
    private String address;

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}