package com.solvd.itcompany2;

import com.solvd.itcompany2.corporatestructure.*;
import com.solvd.itcompany2.exceptions.*;
import com.solvd.itcompany2.outsideentities.*;
import com.solvd.itcompany2.projectresources.*;
import com.solvd.itcompany2.techstack.*;
import static com.solvd.itcompany2.helpers.Formatter.*;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Homework #2
        System.out.println("\n" + formatHeader("Homework #2"));

        // create employees
        Employee employee1 = new Employee(1, "Karol Zakrętka", "Antarctica/Troll", "2002-01-09", 714.0);
        Employee employee2 = new Employee(2, "Zofia Cętka", "US/Pacific", "2013-12-29", 301.7);
        Employee employee3 = new Employee(3, "Michał Dętka", "Japan", "2017-07-07", 162.0);
        Employee employee4 = new Employee(4, "Izabella Jętka", "Poland", "2024-10-28", 92.93);
        Employee employee5 = new Employee(5, "Jakub Piętka", "Portugal", "2024-04-29", 87.21);
        Employee employee6 = new Employee(6, "Agata Smętka", "Poland", "2024-04-27", 107.03);
        Employee employee7 = new Employee(7, "Maciej Kocimiętka", "Asia/Seoul", "2022-11-07", 111.76);

        // create teams
        Team testAutomation = new Team("Test Automation", employee3, new HashSet<> (Arrays.asList(employee4, employee5)));
        Team qualityAssurance = new Team();

        // print some of employees' data
        employee1.printTimeZone();
        System.out.println();
        try {
            testAutomation.printEmployeesDescription();
        } catch (EmptyListException e) {
            System.out.println(e);
        }
        employee2.printWorkYears();
        System.out.println();

        //create departments
        Department qaAndTesting = new Department("Quality Assurance and Testing", employee2, new HashSet<> (Arrays.asList(testAutomation, qualityAssurance)));
        Department productAndDesign = new Department();

        // create a company
        ITCompany resolvd = new ITCompany();
        resolvd.setName("Resolvd");
        resolvd.setCeo(employee1);
        resolvd.setDepartments(new HashSet<> (Arrays.asList(qaAndTesting, productAndDesign)));

        // create a client
        Client google = new Client("Google", "Generała Józefa Bema 2, 50-265 Wrocław, Poland");

        // create a project
        Project boopLoop = new Project(google, 42, 0, new BigDecimal(2137.9));

        // create time trackers
        TimeTracker izaBoopLoop = new TimeTracker(employee4, boopLoop, 0);
        TimeTracker jakubBoopLoop = new TimeTracker(employee5, boopLoop, 0);

        // track hours spent on a project
        izaBoopLoop.logHours(39);
        izaBoopLoop.logHours(99);
        jakubBoopLoop.logHours(15);
        System.out.println(boopLoop.getPersonHours());
        System.out.println(izaBoopLoop.getPersonHours());
        System.out.println(jakubBoopLoop.getPersonHours() + "\n");

        // count project earnings
        System.out.println(boopLoop.getEarnings() + "\n");

        // create a tool
        HashSet<Employee> intellijEmployees = new HashSet<> (Arrays.asList(employee3, employee1));
        Tool intellij = new Tool("IntelliJ IDEA Ultimate (Enterprise Edition)", intellijEmployees);

        // check an employee's access to a tool
        System.out.println(intellij.checkAccess(employee3));
        System.out.println(intellij.checkAccess(employee1) + "\n");

        // create a skill
        HashSet<Employee> programmingJavaEmployees = new HashSet<> (Arrays.asList(employee1, employee3, employee4, employee5));
        Skill programmingJava = new Skill("Programming in Java", programmingJavaEmployees);

        // check skilled employees' access to a tool
        programmingJava.printAccess(intellij);
        System.out.println();

        // Homework #3
        System.out.println("\n" + formatHeader("Homework #3"));

        // print employees' hash codes
        ArrayList<Employee> employeeList = new ArrayList<> (Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6));
        for (Employee employee : employeeList) {
            System.out.println(employee.hashCode());
        }
        System.out.println();

        // print toString's output for all employees
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
        }
        System.out.println();

        // compare all employees to employee6
        for (Employee employee : employeeList) {
            System.out.println(employee.equals(employee6));
        }
        System.out.println();

        // you can find comments about overriding in the Employee class

        // create service providers and call them
        Provider inea = new InternetServiceProvider("Inea", "112", "Poland");
        HashSet<Tool> jetBrainsTools = new HashSet<> (Arrays.asList(intellij));
        Provider jetBrains = new SoftwareVendor("JetBrains", "987123654", jetBrainsTools);
        inea.phoneCall();
        jetBrains.phoneCall();

        // create a committe & a taskforce, then print their descriptions
        Team dei = new Committee("Diversity, Equity and Inclusion", employee2, new HashSet<> (Arrays.asList(employee3, employee4)));
        Team christmasParty = new TaskForce("Christmas Party Organizers", employee4, new HashSet<> (Arrays.asList(employee2, employee5)));
        dei.printDescription();
        christmasParty.printDescription();
        testAutomation.printDescription();
        System.out.println();

        // check whether an employee belongs to different CorporateUnits
        for (CorporateUnit corporateUnit : new ArrayList<> (Arrays.asList(testAutomation, qaAndTesting, resolvd, dei))) {
            System.out.println(new StringBuilder()
                    .append(corporateUnit.getName())
                    .append(": ")
                    .append(employee2.checkAffiliation(corporateUnit)));
        }

        // Homework #4
        System.out.println("\n" + formatHeader("Homework #4"));

        // use overridden TimeTracker's methods
        System.out.println(izaBoopLoop);
        System.out.println(izaBoopLoop.equals(jakubBoopLoop));
        System.out.println(izaBoopLoop.hashCode() + ", " + jakubBoopLoop.hashCode() + "\n");

        // use overridden Team's methods
        System.out.println(testAutomation);
        System.out.println(testAutomation.equals(dei));
        System.out.println(testAutomation.hashCode() + ", " + dei.hashCode());
        System.out.println();

        // use a CorporateUnit's getAllEmployees() method on objects from different classes (ITCompany, Department, Team, Committee)
        ArrayList<CorporateUnit> corporateUnits = new ArrayList<> (Arrays.asList(resolvd, qaAndTesting, testAutomation, dei));
        for (CorporateUnit corporateUnit : corporateUnits) {
            System.out.println(corporateUnit.getName() + " consists of:");
            for (Employee employee : corporateUnit.getAllEmployees()) {
                System.out.println(employee.getName());
            }
            System.out.println();
        }

        // use PayableEntity's pay() method
        try {
            jetBrains.pay(420);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            employee4.pay(employee4.getHourlyWage() * 8);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();

        // create a task and close it
        HashSet<Stakeholder> task1Stakeholders = new HashSet<>(Arrays.asList(employee3, google));
        Task task1 = new Task("Whatchamacallit is shebanging in the BoopLoop's pinto logs and the mainframe hexing gets bungled ", employee4, task1Stakeholders, "open");
        System.out.println(task1.getStatus() + "\n");
        employee4.finishTask(task1);
        System.out.println("\n" + task1.getStatus());
        System.out.println();

        // use SpaceRequester's method
        employee1.requestSpace();
        christmasParty.requestSpace();

        // i can't come up with where i might want to use a static block (fragment of code that gets executed exactly once). i'd appreciate any suggestions

        // Homework #5
        System.out.println("\n" + formatHeader("Homework #5"));

        // throw NumberEqualToZeroException
        try {
            employee4.pay(0);
        } catch (NumberEqualToZeroException | NegativeNumberException e) {
            System.out.println(e);
        }

        // throw NegativeNumberException
        try {
            jetBrains.pay(-2137);
        } catch (NumberEqualToZeroException | NegativeNumberException e) {
            System.out.println(e);
        }

        // use FileOutputStream (a built-in class that implements AutoCloseable interface)
        try (FileOutputStream fos = new FileOutputStream(testAutomation.getName() + ".txt")) {
            String text = testAutomation.getName() + " team employees:\n";
            for (Employee employee : testAutomation.getAllEmployees()) {
                String employeeDescription = new StringBuilder()
                        .append("\n    Employee ID: ")
                        .append(employee.getID())
                        .append("\n    Name: ")
                        .append(employee.getName())
                        .append("\n    Working since: ")
                        .append(employee.getFirstDay())
                        .append("\n    Time zone: ")
                        .append(employee.getTimeZone())
                        .append("\n")
                        .toString();
                text = text.concat(employeeDescription);
            }
            fos.write(text.getBytes());
            System.out.println("Exported to '" + testAutomation.getName() + ".txt' successfully!\n");
        } catch (Exception e) {
            System.out.println(e);
        }

        // create class that implements the AutoCloseable interface and close it using the try with resources
        try (MysteriousObject mysteriousObject = new MysteriousObject()) {
            System.out.println("You've opened a mysterious object! It's " + mysteriousObject);
        } catch (Exception e) {
            System.out.println(e);
        }

        // throw ObjectAlreadyIncludedException
        testAutomation.printDescription();
        System.out.println();

        try {
            testAutomation.addEmployee(employee3);
        } catch (ObjectAlreadyIncludedException e) {
            System.out.print(e);
        } finally {
            System.out.println();
        }

        try {
            testAutomation.addEmployee(employee6);
        } catch (ObjectAlreadyIncludedException e) {
            System.out.print(e);
        } finally {
            System.out.println();
        }

        // throw ObjectNotIncludedException
        try {
            testAutomation.removeEmployee(employee7);
        } catch (ObjectNotIncludedException e) {
            System.out.print(e);
        } finally {
            System.out.println();
        }

        try {
            testAutomation.removeEmployee(employee6);
        } catch (ObjectNotIncludedException e) {
            System.out.print(e);
        } finally {
            System.out.println();
        }

//        // EmptyListException (unchecked)
//        qualityAssurance.printEmployeesDescription();
//        System.out.println("This won't get printed");

        // Homework #6
        System.out.println(formatHeader("Homework #6"));

        // use Map and Set interfaces
        ArrayList<Employee> allEmployees = new ArrayList<> (Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6, employee7));
        SortedMap<String, Set<String>> timeMap = new TreeMap<>(); // SortedMap is an interface (cannot be instantiated), but TreeMap is a class

        for (Employee employee : allEmployees) { // get timeMap keys
            timeMap.put(employee.getTime(), new TreeSet<>());
        }

        for (Employee employee : allEmployees) {
            String time = employee.getTime();
            String name = employee.getName();

            Set<String> allNames = timeMap.get(time); // add employees' names to timeMap values
            allNames.add(name);
            timeMap.put(time, allNames);
        }

        for (String key : timeMap.keySet()) { // print out timeMap
            System.out.println(key + " --- " + timeMap.get(key));
        }
        System.out.println();
    }
}