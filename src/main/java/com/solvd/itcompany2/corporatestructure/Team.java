package com.solvd.itcompany2.corporatestructure;

import com.solvd.itcompany2.exceptions.EmptyListException;
import com.solvd.itcompany2.exceptions.ObjectAlreadyIncludedException;
import com.solvd.itcompany2.exceptions.ObjectNotIncludedException;
import com.solvd.itcompany2.projectresources.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.solvd.itcompany2.helpers.Formatter.*;
import static com.solvd.itcompany2.helpers.GlobalVariable.MULTIPLICAND;
import static com.solvd.itcompany2.helpers.GlobalVariable.MULTIPLIER;

public class Team implements CorporateUnit, TaskOwner, Stakeholder, SpaceRequester {

    private static final Logger LOGGER = LogManager.getLogger(Team.class);

    protected String name;
    protected Employee leader;
    protected List<Employee> employees;

    public Team() {
    }

    public Team(String name, Employee leader, List<Employee> employees) {
        this.name = name;
        this.leader = leader;
        this.employees = employees;
    }

    @Override
    public int hashCode() {
        int code = MULTIPLICAND;
        code = code * MULTIPLIER + (name != null ? name.hashCode() : 0);
        code = code * MULTIPLIER + (leader != null ? leader.hashCode() : 0);
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
        return Objects.equals(this.name, ((Team) compared).getName()) &&
                Objects.equals(this.leader, ((Team) compared).getLeader());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Team ")
                .append(this.name == null ? "n/d" : this.name)
                .append(", led by ")
                .append(this.leader == null ? "n/d" : this.leader.getName())
                .toString();
    }

    public void printDescription() {
        if ((this.name != null) && (this.leader != null) && (this.employees != null)) {
            StringBuilder msg = new StringBuilder().append(this.leader.getName())
                    .append(" is in charge of ")
                    .append(this.getName())
                    .append(". ");

            Employee[] temp = this.getEmployees().toArray(new Employee[0]);
            for (int i = 0; i < temp.length; i++) {
                if (i != 0) {
                    msg.append(", ");
                }
                msg.append(temp[i].getName());
            }
            msg.append(" work alongside them.");

            LOGGER.info(msg);
        }
    }

    public void printEmployeesDescription() {
        if (this.getAllEmployees().isEmpty()) {
            throw new EmptyListException("You can't print employees' description. There are 0 people in this team.");
        } else {
            for (Employee employee : this.getAllEmployees()) {
                employee.printTimeZone();
                employee.printWorkYears();
                LOGGER.info("");
            }
        }
    }

    @Override
    public final List<Employee> getAllEmployees() { // final method can't be overridden by a subclass
        List<Employee> teamEmployees = new ArrayList<>();

        if (this.getLeader() != null) { // if a team leader exists
            teamEmployees.add(this.getLeader());
        }

        if (this.getEmployees() != null) { // if any employees exist
            teamEmployees.addAll(this.getEmployees());
        }

        return teamEmployees;
    }

    @Override
    public void finishTask(Task task) {
        task.setStatus(TaskStatus.COMPLETED);

        LOGGER.info(new StringBuilder()
                .append(ansiColor(cyanFG))
                .append("Task:\n")
                .append(ansiColor(reset))
                .append(task.getDescription())
                .append("\n")
                .append(ansiColor(cyanFG))
                .append("Stakeholders:")
                .append(ansiColor(reset)));
        task.getStakeholders().forEach(stakeholder -> LOGGER.info(stakeholder.getName()));
        LOGGER.info(new StringBuilder()
                .append(ansiColor(cyanFG))
                .append("Finished by:\n")
                .append(ansiColor(reset))
                .append(this.getName()));
    }

    @Override
    public void requestSpace() {
        LOGGER.info(new StringBuilder()
                .append("Your request has been approved! You booked ")
                .append(this.getAllEmployees().size())
                .append(" desk(s) for ")
                .append(this.getName()));
    }

    public void addEmployee(Employee employee) throws ObjectAlreadyIncludedException {
        if (employee.checkAffiliation(this)) {
            throw new ObjectAlreadyIncludedException(employee.getName() + " is already on the team, so you can't add them.");

        } else {
            List<Employee> newList = new ArrayList<>(this.getEmployees());
            newList.add(employee);
            this.setEmployees(newList);

            LOGGER.info(employee.getName() + " added successfully! "); // print success message
            this.printDescription();
        }
    }

    public void removeEmployee(Employee removedEmployee) throws ObjectNotIncludedException {
        if (!removedEmployee.checkAffiliation(this)) {
            throw new ObjectNotIncludedException(removedEmployee.getName() + " is not on the team, so you can't remove them.");

        } else {
            if (removedEmployee == this.getLeader()) {
                this.setLeader(null);

            } else {
                List<Employee> newList = new ArrayList<>();
                for (Employee oldEmployee : this.getEmployees()) {
                    if (oldEmployee != removedEmployee) {
                        newList.add(oldEmployee);
                    }
                }
                this.setEmployees(newList);
            }

            LOGGER.info(removedEmployee.getName() + " removed successfully! "); // print success message
            this.printDescription();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getLeader() {
        return leader;
    }

    public void setLeader(Employee leader) {
        this.leader = leader;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}