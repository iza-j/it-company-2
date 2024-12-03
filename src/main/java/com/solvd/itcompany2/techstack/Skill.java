package com.solvd.itcompany2.techstack;

import com.solvd.itcompany2.corporatestructure.Employee;

import java.util.HashSet;

public class Skill {

    private String name;
    private HashSet<Employee> employees;

    public Skill(String name, HashSet<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public void printAccess(Tool tool) {
        System.out.print(new StringBuilder()
                .append("Employees skilled at ")
                .append(this.name)
                .append(", but without access to ")
                .append(tool.getName())
                .append(":\n"));

        for (Employee employee : this.getEmployees()) {
            boolean access = false;
            for (Employee toolUser : tool.getEmployees()) {
                if (employee.equals(toolUser)) {
                    access = true;
                    break;
                }
            }
            if (!access) {
                System.out.println(employee.getName());
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(HashSet<Employee> employees) {
        this.employees = employees;
    }
}