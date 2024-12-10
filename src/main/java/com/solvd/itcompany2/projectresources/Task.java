package com.solvd.itcompany2.projectresources;

import java.util.Set;

public class Task {

    private String description;
    private TaskOwner owner;
    private Set<Stakeholder> stakeholders;
    private String status;

    public Task(String description, TaskOwner owner, Set<Stakeholder> stakeholders, String status) {
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

    public Set<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(Set<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}