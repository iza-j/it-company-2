package com.solvd.itcompany2.corporatestructure;

import java.util.HashSet;

public class TaskForce extends Team {

    public TaskForce(String name, Employee leader, HashSet<Employee> employees) {
        this.name = name;
        this.leader = leader;
        this.employees = employees;
    }
}