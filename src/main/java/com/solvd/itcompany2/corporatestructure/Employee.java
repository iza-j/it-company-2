package com.solvd.itcompany2.corporatestructure;

import com.solvd.itcompany2.exceptions.NegativeNumberException;
import com.solvd.itcompany2.exceptions.NumberEqualToZeroException;
import com.solvd.itcompany2.outsideentities.PayableEntity;
import com.solvd.itcompany2.projectresources.SpaceRequester;
import com.solvd.itcompany2.projectresources.Stakeholder;
import com.solvd.itcompany2.projectresources.Task;
import com.solvd.itcompany2.projectresources.TaskOwner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.solvd.itcompany2.helpers.Formatter.*;
import static com.solvd.itcompany2.helpers.GlobalVariable.*;

public final class Employee implements PayableEntity, TaskOwner, Stakeholder, SpaceRequester { // 'final' keyword prevents inheritance. i don't want to have employees of a different class than Employee

    private int id;
    private String name;
    private String timeZone; // https://mkyong.com/java8/java-display-all-zoneid-and-its-utc-offset/
    private String firstDay;
    private Double hourlyWage;

    public Employee(int id, String name, String timeZone, String firstDay, Double hourlyWage) {
        this.id = id;
        this.name = name;
        this.timeZone = timeZone;
        this.firstDay = firstDay;
        this.hourlyWage = hourlyWage;
    }

    @Override
    public int hashCode() { // a manual override
        int code = MULTIPLICAND; // using prime numbers bc of a reason that i don't understand (yet)
        code = code * MULTIPLIER + id; // int's default value can't be null, so no conditions in this line
        code = code * MULTIPLIER + (name != null ? name.hashCode() : 0); // ternary conditional operator (inline if)
        code = code * MULTIPLIER + (firstDay != null ? firstDay.hashCode() : 0); // (a ? b : c) == (if a then b otherwise c)
        return code; // id should be unique, but someone's id could be entered incorrectly. but then it's rather impossible to have id and name and firstDay identical to another employee
    }

    @Override
    public boolean equals(Object compared) { // type of the equals method is Object. if i tried to use another type, it would become overloading instead of overriding
        if (compared == this) { // check whether we're trying to compare the object to itself
            return true;
        }
        if (compared == null) { // if i used a null reference, i'd get a NullPointerException (not good)
            return false;
        }
        if (this.getClass() != compared.getClass()) { // if i tried to compare objects from 2 different classes i'd get a ClassCastException (also not good)
            return false;
        }
        return  this.id == ((Employee) compared).getID() &&
                Objects.equals(this.name, ((Employee) compared).getName()) &&
                Objects.equals(this.firstDay, ((Employee) compared).getFirstDay()); // returns value of && statement
    }

    @Override
    public String toString() {
        return  new StringBuilder()
                .append("Employee #")
                .append(id)
                .append(": ")
                .append(this.name == null ? "n/d" : this.name)
                .append(". Working since ")
                .append(this.firstDay == null ? "n/d" : this.firstDay)
                .toString();
    }

    public void printTimeZone() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("HH:mm, MMMM d");

        LOGGER.info(new StringBuilder()
                .append(this.name)
                .append("'s time zone is ")
                .append(ZoneId.of(this.timeZone))
                .append(". It's ")
                .append(LocalDateTime.now(ZoneId.of(this.timeZone)).format(form))
                .append(" in there."));
    }

    public void printWorkYears() {
        LocalDate start = LocalDate.parse(this.firstDay);
        Period period = Period.between(start, LocalDate.now());
        long months = period.toTotalMonths();

        LOGGER.info(new StringBuilder()
                .append(this.name)
                .append(" has been working with us since ")
                .append(start)
                .append(". That's ")

                .append(months > 12     ? period.getYears()     : "")
                .append(months > 12     ? " year(s) and "       : "")

                .append(months > 0      ? period.getMonths()    : "")
                .append(months > 0      ? " month(s)!"          : "")

                .append(months == 0     ? period.getDays()      : "")
                .append(months == 0     ? " day(s)!"            : ""));
    }

    @Override
    public void pay(double amount) throws NumberEqualToZeroException, NegativeNumberException {
        String exceptionMessage = new StringBuilder()
                .append("You can't pay anybody ")
                .append(amount)
                .append(" pln.")
                .toString();

        if (amount == 0) {
            throw new NumberEqualToZeroException(exceptionMessage);

        } else if (amount < 0) {
            throw new NegativeNumberException(exceptionMessage);

        } else {
            LOGGER.info(new StringBuilder()
                    .append(ansiColor(yellowFG, blackBG))
                    .append(" *ka-ching!* ")
                    .append(ansiColor(reset))
                    .append(" You pay ")
                    .append(this.name)
                    .append(" ")
                    .append(amount)
                    .append(" pln for their work."));
        }
    }

    @Override
    public void finishTask(Task task){
        task.setStatus("finished");

        LOGGER.info(new StringBuilder()
                .append(ansiColor(cyanFG))
                .append("Task:\n")
                .append(ansiColor(reset))
                .append(task.getDescription())
                .append("\n")
                .append(ansiColor(cyanFG))
                .append("Stakeholders:")
                .append(ansiColor(reset)));
        for (Stakeholder stakeholder : task.getStakeholders()) {
            LOGGER.info(stakeholder.getName());
        }
        LOGGER.info(new StringBuilder()
                .append(ansiColor(cyanFG))
                .append("Finished by:\n")
                .append(ansiColor(reset))
                .append(this.getName()));
    }

    public boolean checkAffiliation (CorporateUnit corporateUnit) {
        boolean affiliation = false;
        for (Employee employee : corporateUnit.getAllEmployees()) {
            if (this == employee) {
                affiliation = true;
                break;
            }
        }
        return affiliation;
    }

    @Override
    public void requestSpace() {
        LOGGER.info("Your request has been approved! You booked 1 desk for " + this.getName());
    }

    public String getTime() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm");
        return LocalDateTime.now(ZoneId.of(this.timeZone)).format(form);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    public Double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}