package com.solvd.itcompany2.corporatestructure;

import java.util.*;

public class Department implements CorporateUnit {

    private String name;
    private Employee headOfDpt;
    private HashSet<Team> teams;

    public Department() {
    }

    public Department(String name, Employee headOfDpt, HashSet<Team> teams) {
        this.name = name;
        this.headOfDpt = headOfDpt;
        this.teams = teams;
    }

    @Override
    public HashSet<Employee> getAllEmployees() {
        HashSet<Employee> departmentEmployees = new HashSet<>();

        if (this.getHeadOfDpt() != null) { // if a head of department exists
            departmentEmployees.add(this.getHeadOfDpt());
        }

        if (this.getTeams() != null) { // if any teams exist in the department
            for (Team team : this.getTeams()) {
                departmentEmployees.addAll(team.getAllEmployees());
            }
        }

        return departmentEmployees;
    }

    public HashSet<Team> getTeams() {
        return teams;
    }

    public void setTeams(HashSet<Team> teams) {
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