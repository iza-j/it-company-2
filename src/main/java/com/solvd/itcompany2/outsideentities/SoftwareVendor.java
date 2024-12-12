package com.solvd.itcompany2.outsideentities;

import com.solvd.itcompany2.techstack.Tool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class SoftwareVendor extends Provider {

    private static final Logger LOGGER = LogManager.getLogger(SoftwareVendor.class);

    private List<Tool> tools;

    public SoftwareVendor(String name, String phoneNumber, List<Tool> tools) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.tools = tools;
    }

    @Override
    public void phoneCall() {
        LOGGER.info("Calling...\n.\n.\n.\n" + this.name + "'s customer service is temporarily unavailable. Please message us on BoopLoop.\n");
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }
}