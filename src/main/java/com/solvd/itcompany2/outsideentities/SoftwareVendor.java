package com.solvd.itcompany2.outsideentities;

import com.solvd.itcompany2.techstack.Tool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Set;

public class SoftwareVendor extends Provider {

    private static final Logger log = LogManager.getLogger(SoftwareVendor.class);

    private Set<Tool> tools;

    public SoftwareVendor(String name, String phoneNumber, Set<Tool> tools) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.tools = tools;
    }

    @Override
    public void phoneCall() {
        log.info("Calling...\n.\n.\n.\n" + this.name + "'s customer service is temporarily unavailable. Please message us on BoopLoop.\n");
    }

    public Set<Tool> getTools() {
        return tools;
    }

    public void setTools(Set<Tool> tools) {
        this.tools = tools;
    }
}