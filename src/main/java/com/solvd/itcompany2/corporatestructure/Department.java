package com.solvd.itcompany2.corporatestructure;

import java.util.HashSet;
import java.util.Set;

public class Department implements CorporateUnit {

    private String name;
    private Employee headOfDpt;
    private Set<Team> teams;

    public Department() {
    }

    public Department(String name, Employee headOfDpt, Set<Team> teams) {
        this.name = name;
        this.headOfDpt = headOfDpt;
        this.teams = teams;
    }

    @Override
    public Set<Employee> getAllEmployees() {
        Set<Employee> departmentEmployees = new HashSet<>();

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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
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