package com.solvd.itcompany2.techstack;

import com.solvd.itcompany2.corporatestructure.Employee;

import java.util.Set;

public class Tool {

    private String name;
    private Set<Employee> employees;

    public Tool(String name, Set<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public boolean checkAccess(Employee employee) {
        boolean access = false;
        for (Employee employeeWithAccess : this.employees) {
            if (employee.equals(employeeWithAccess)) {
                access = true;
                break;
            }
        }
        return access;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}