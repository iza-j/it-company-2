package com.solvd.itcompany2.corporatestructure;

import java.util.Set;

public class TaskForce extends Team {

    public TaskForce(String name, Employee leader, Set<Employee> employees) {
        this.name = name;
        this.leader = leader;
        this.employees = employees;
    }
}