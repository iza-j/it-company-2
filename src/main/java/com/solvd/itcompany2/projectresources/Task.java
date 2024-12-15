package com.solvd.itcompany2.projectresources;

import java.util.List;

public class Task {

    private String description;
    private TaskOwner owner;
    private List<Stakeholder> stakeholders;
    private TaskStatus status;

    public Task(String description, TaskOwner owner, List<Stakeholder> stakeholders, TaskStatus status) {
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

    public List<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(List<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}