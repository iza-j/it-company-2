package com.solvd.itcompany2.outsideentities;

import com.solvd.itcompany2.techstack.Tool;

import java.util.HashSet;

public class SoftwareVendor extends Provider {

    private HashSet<Tool> tools;

    public SoftwareVendor(String name, String phoneNumber, HashSet<Tool> tools) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.tools = tools;
    }

    @Override
    public void phoneCall() {
        System.out.println("Calling...\n.\n.\n.\n" + this.name + "'s customer service is temporarily unavailable. Please message us on BoopLoop.\n");
    }

    public HashSet<Tool> getTools() {
        return tools;
    }

    public void setTools(HashSet<Tool> tools) {
        this.tools = tools;
    }
}