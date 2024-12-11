package com.solvd.itcompany2.techstack;

import com.solvd.itcompany2.corporatestructure.Employee;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Set;

public class Skill {

    private static final Logger log = LogManager.getLogger(Skill.class);

    private String name;
    private Set<Employee> employees;

    public Skill(String name, Set<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public void printAccess(Tool tool) {
        log.info(new StringBuilder()
                .append("Employees skilled at ")
                .append(this.name)
                .append(", but without access to ")
                .append(tool.getName())
                .append(":"));

        for (Employee employee : this.getEmployees()) {
            boolean access = false;
            for (Employee toolUser : tool.getEmployees()) {
                if (employee.equals(toolUser)) {
                    access = true;
                    break;
                }
            }
            if (!access) {
                log.info(employee.getName());
            }
        }
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