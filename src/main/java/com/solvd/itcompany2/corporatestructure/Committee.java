package com.solvd.itcompany2.corporatestructure;

import java.util.Set;

import static com.solvd.itcompany2.helpers.GlobalVariable.LOGGER;

public class Committee extends Team {

    public Committee(String name, Employee leader, Set<Employee> employees) {
        this.name = name;
        this.leader = leader;
        this.employees = employees;
    }

    @Override
    public void printDescription() {
        if ((this.name != null) && (this.leader != null) && (this.employees != null)) {
            StringBuilder msg = new StringBuilder();

            msg     .append(this.leader.getName())
                    .append(" is the head of ")
                    .append(this.getName())
                    .append(" committee. Other members include: ");

            Employee[] temp = this.getEmployees().toArray(new Employee[0]);
            for (int i = 0; i < temp.length; i++) {
                if (i != 0) {
                    msg.append(", ");
                }
                msg.append(temp[i].getName());
            }
            msg.append(".");

            LOGGER.info(msg);
        }
    }
}