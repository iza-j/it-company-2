package com.solvd.itcompany2.corporatestructure;

import java.util.ArrayList;
import java.util.List;

public class ITCompany implements CorporateUnit {

    private String name;
    private Employee ceo;
    private List<Department> departments;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> companyEmployees = new ArrayList<>();

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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Employee getCeo() {
        return ceo;
    }

    public void setCeo(Employee ceo) {
        this.ceo = ceo;
    }
}