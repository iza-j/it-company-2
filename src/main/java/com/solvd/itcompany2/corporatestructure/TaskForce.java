package com.solvd.itcompany2.corporatestructure;

import java.util.List;

public class TaskForce extends Team {

    public TaskForce(String name, Employee leader, List<Employee> employees) {
        this.name = name;
        this.leader = leader;
        this.employees = employees;
    }
}