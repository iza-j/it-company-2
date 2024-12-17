package com.solvd.itcompany2.techstack;

import com.solvd.itcompany2.corporatestructure.Employee;

import java.util.List;

public class Tool {

    private String name;
    private List<Employee> employees;

    public Tool(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public boolean checkAccess(Employee employee) {
        return this.getEmployees().stream().anyMatch(employeeWithAccess -> employee.equals(employeeWithAccess));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}