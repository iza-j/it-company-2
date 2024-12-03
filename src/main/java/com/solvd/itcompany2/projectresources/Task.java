package com.solvd.itcompany2.projectresources;

import java.util.HashSet;

public class Task {

    private String description;
    private TaskOwner owner;
    private HashSet<Stakeholder> stakeholders;
    private String status;

    public Task(String description, TaskOwner owner, HashSet<Stakeholder> stakeholders, String status) {
        this.description = description;
        this.owner = owner;
        this.stakeholders = stakeholders;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskOwner getOwner() {
        return owner;
    }

    public void setOwner(TaskOwner owner) {
        this.owner = owner;
    }

    public HashSet<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(HashSet<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}