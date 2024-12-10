package com.solvd.itcompany2.corporatestructure;

import java.util.HashSet;
import java.util.Set;

public class ITCompany implements CorporateUnit {

    private String name;
    private Employee ceo;
    private Set<Department> departments;

    @Override
    public Set<Employee> getAllEmployees() {
        Set<Employee> companyEmployees = new HashSet<>();

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

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Employee getCeo() {
        return ceo;
    }

    public void setCeo(Employee ceo) {
        this.ceo = ceo;
    }
}