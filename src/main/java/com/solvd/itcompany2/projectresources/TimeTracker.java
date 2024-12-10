package com.solvd.itcompany2.projectresources;

import com.solvd.itcompany2.corporatestructure.Employee;

import java.util.Objects;

import static com.solvd.itcompany2.helpers.GlobalVariable.MULTIPLICAND;
import static com.solvd.itcompany2.helpers.GlobalVariable.MULTIPLIER;

public class TimeTracker {

    private Employee employee;
    private Project project;
    private Integer personHours;

    public TimeTracker(Employee employee, Project project, Integer personHours) {
        this.employee = employee;
        this.project = project;
        this.personHours = personHours;
    }

    @Override
    public int hashCode() {
        int code = MULTIPLICAND;
        code = code * MULTIPLIER + (employee != null ? employee.hashCode() : 0);
        code = code * MULTIPLIER + (project != null ? project.hashCode() : 0);
        return code;
    }

    @Override
    public boolean equals(Object compared) {
        if (compared == this) {
            return true;
        }
        if (compared == null) {
            return false;
        }
        if (this.getClass() != compared.getClass()) {
            return false;
        }
        return  Objects.equals(this.employee, ((TimeTracker) compared).getEmployee()) &&
                Objects.equals(this.project, ((TimeTracker) compared).getProject());
    }

    @Override
    public String toString() {
        return  new StringBuilder()
                .append("Time tracker of ")
                .append(this.employee == null ? "n/d" : this.employee.getName())
                .append(". Project #")
                .append(this.project == null ? "n/d" : this.project.getProjectNo())
                .append(" for ")
                .append(this.project == null ? "n/d" : this.project.getClient().getName())
                .toString();
    }

    public void logHours(Integer hours) {
        this.project.setPersonHours(this.project.getPersonHours() + hours);
        this.setPersonHours(this.getPersonHours() + hours);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getPersonHours() {
        return personHours;
    }

    public void setPersonHours(Integer personHours) {
        this.personHours = personHours;
    }
}