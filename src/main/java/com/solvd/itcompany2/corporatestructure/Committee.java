package com.solvd.itcompany2.corporatestructure;

import java.util.HashSet;

public class Committee extends Team {

    public Committee(String name, Employee leader, HashSet<Employee> employees) {
        this.name = name;
        this.leader = leader;
        this.employees = employees;
    }

    @Override
    public void printDescription() {
        if ((this.name != null) && (this.leader != null) && (this.employees != null)) {
            System.out.print(new StringBuilder()
                    .append(this.leader.getName())
                    .append(" is the head of ")
                    .append(this.getName())
                    .append(" committee. Other members include "));

            Employee[] temp = this.getEmployees().toArray(new Employee[0]);
            for (int i = 0; i < temp.length; i++) {
                if (i != 0) {
                    System.out.print(", ");
                }
                System.out.print(temp[i].getName());
            }
            System.out.print(".\n");
        }
    }
}