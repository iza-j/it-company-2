package com.solvd.itcompany2.techstack;

import com.solvd.itcompany2.corporatestructure.Employee;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class Skill {

    private static final Logger LOGGER = LogManager.getLogger(Skill.class);

    private String name;
    private List<Employee> employees;

    public Skill(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public void printAccess(Tool tool) {
        LOGGER.info(new StringBuilder()
                .append("Employees skilled at ")
                .append(this.name)
                .append(", but without access to ")
                .append(tool.getName())
                .append(":"));

        for (Employee skilledEmployee : this.getEmployees()) {
            boolean access = tool.getEmployees().stream().anyMatch(toolUser -> skilledEmployee.equals(toolUser));
            if (!access) {
                LOGGER.info(skilledEmployee.getName());
            }
        }
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