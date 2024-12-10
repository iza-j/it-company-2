package com.solvd.itcompany2.outsideentities;

import com.solvd.itcompany2.techstack.Tool;

import java.util.Set;

import static com.solvd.itcompany2.helpers.GlobalVariable.LOGGER;

public class SoftwareVendor extends Provider {

    private Set<Tool> tools;

    public SoftwareVendor(String name, String phoneNumber, Set<Tool> tools) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.tools = tools;
    }

    @Override
    public void phoneCall() {
        LOGGER.info("Calling...\n.\n.\n.\n" + this.name + "'s customer service is temporarily unavailable. Please message us on BoopLoop.\n");
    }

    public Set<Tool> getTools() {
        return tools;
    }

    public void setTools(Set<Tool> tools) {
        this.tools = tools;
    }
}