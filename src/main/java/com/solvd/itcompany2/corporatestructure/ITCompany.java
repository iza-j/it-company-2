package com.solvd.itcompany2.corporatestructure;

import java.util.HashSet;

public class ITCompany implements CorporateUnit {

    private String name;
    private Employee ceo;
    private HashSet<Department> departments;

    @Override
    public HashSet<Employee> getAllEmployees() {
        HashSet<Employee> companyEmployees = new HashSet<>();

        if (this.getCeo() != null) { // if a ceo exists
            companyEmployees.add(this.getCeo());
        }

        if (this.getDepartments() != null) { // if any departments exist in the company
            for (Department department : this.getDepartments()) {
                companyEmployees.addAll(department.getAllEmployees());
            }
        }

        return companyEmployees;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(HashSet<Department> departments) {
        this.departments = departments;
    }

    public Employee getCeo() {
        return ceo;
    }

    public void setCeo(Employee ceo) {
        this.ceo = ceo;
    }
}