package com.solvd.itcompany2.corporatestructure;

import java.util.ArrayList;
import java.util.List;

public class Department implements CorporateUnit {

    private String name;
    private Employee headOfDpt;
    private List<Team> teams;

    public Department() {
    }

    public Department(String name, Employee headOfDpt, List<Team> teams) {
        this.name = name;
        this.headOfDpt = headOfDpt;
        this.teams = teams;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> departmentEmployees = new ArrayList<>();

        if (this.getHeadOfDpt() != null) { // if a head of department exists
            departmentEmployees.add(this.getHeadOfDpt());
        }

        if (this.getTeams() != null) { // if any teams exist in the department
            this.getTeams().forEach(team -> departmentEmployees.addAll(team.getAllEmployees()));
        }

        return departmentEmployees;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Employee getHeadOfDpt() {
        return headOfDpt;
    }

    public void setHeadOfDpt(Employee headOfDpt) {
        this.headOfDpt = headOfDpt;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}