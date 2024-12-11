package com.solvd.itcompany2;

import com.solvd.itcompany2.corporatestructure.*;
import com.solvd.itcompany2.exceptions.*;
import com.solvd.itcompany2.outsideentities.Client;
import com.solvd.itcompany2.outsideentities.InternetServiceProvider;
import com.solvd.itcompany2.outsideentities.Provider;
import com.solvd.itcompany2.outsideentities.SoftwareVendor;
import com.solvd.itcompany2.projectresources.Project;
import com.solvd.itcompany2.projectresources.Stakeholder;
import com.solvd.itcompany2.projectresources.Task;
import com.solvd.itcompany2.projectresources.TimeTracker;
import com.solvd.itcompany2.techstack.Skill;
import com.solvd.itcompany2.techstack.Tool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.*;

import static com.solvd.itcompany2.helpers.Formatter.formatHeader;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Homework #2
        log.info(formatHeader("Homework #2"));

        // create employees
        Employee employee1 = new Employee(1, "Karol Zakrętka", "Antarctica/Troll", "2002-01-09", 714.0);
        Employee employee2 = new Employee(2, "Zofia Cętka", "US/Pacific", "2013-12-29", 301.7);
        Employee employee3 = new Employee(3, "Michał Dętka", "Japan", "2017-07-07", 162.0);
        Employee employee4 = new Employee(4, "Izabella Jętka", "Poland", "2024-10-28", 92.93);
        Employee employee5 = new Employee(5, "Jakub Piętka", "Portugal", "2024-04-29", 87.21);
        Employee employee6 = new Employee(6, "Agata Smętka", "Poland", "2024-04-27", 107.03);
        Employee employee7 = new Employee(7, "Maciej Kocimiętka", "Asia/Seoul", "2022-11-07", 111.76);

        // create teams
        Team testAutomation = new Team("Test Automation", employee3, new HashSet<>(Arrays.asList(employee4, employee5)));
        Team qualityAssurance = new Team();

        // print some of employees' data
        employee1.printTimeZone();
        log.info("");
        try {
            testAutomation.printEmployeesDescription();
        } catch (EmptyListException e) {
            log.error(e);
        }
        employee2.printWorkYears();
        log.info("");

        //create departments
        Department qaAndTesting = new Department("Quality Assurance and Testing", employee2, new HashSet<>(Arrays.asList(testAutomation, qualityAssurance)));
        Department productAndDesign = new Department();

        // create a company
        ITCompany resolvd = new ITCompany();
        resolvd.setName("Resolvd");
        resolvd.setCeo(employee1);
        resolvd.setDepartments(new HashSet<>(Arrays.asList(qaAndTesting, productAndDesign)));

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
        log.info(boopLoop.getPersonHours());
        log.info(izaBoopLoop.getPersonHours());
        log.info(jakubBoopLoop.getPersonHours() + "\n");

        // count project earnings
        log.info(boopLoop.getEarnings() + "\n");

        // create a tool
        Set<Employee> intellijEmployees = new HashSet<>(Arrays.asList(employee3, employee1));
        Tool intellij = new Tool("IntelliJ IDEA Ultimate (Enterprise Edition)", intellijEmployees);

        // check an employee's access to a tool
        log.info(intellij.checkAccess(employee3));
        log.info(intellij.checkAccess(employee1) + "\n");

        // create a skill
        Set<Employee> programmingJavaEmployees = new HashSet<>(Arrays.asList(employee1, employee3, employee4, employee5));
        Skill programmingJava = new Skill("Programming in Java", programmingJavaEmployees);

        // check skilled employees' access to a tool
        programmingJava.printAccess(intellij);

        // Homework #3
        log.info(formatHeader("Homework #3"));

        // print employees' hash codes
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6));
        for (Employee employee : employeeList) {
            log.info(employee.hashCode());
        }
        log.info("");

        // print toString's output for all employees
        for (Employee employee : employeeList) {
            log.info(employee.toString());
        }
        log.info("");

        // compare all employees to employee6
        for (Employee employee : employeeList) {
            log.info(employee.equals(employee6));
        }
        log.info("");

        // you can find comments about overriding in the Employee class

        // create service providers and call them
        Provider inea = new InternetServiceProvider("Inea", "112", "Poland");
        Set<Tool> jetBrainsTools = new HashSet<>(Arrays.asList(intellij));
        Provider jetBrains = new SoftwareVendor("JetBrains", "987123654", jetBrainsTools);
        inea.phoneCall();
        jetBrains.phoneCall();

        // create a committe & a taskforce, then print their descriptions
        Team dei = new Committee("Diversity, Equity and Inclusion", employee2, new HashSet<>(Arrays.asList(employee3, employee4)));
        Team christmasParty = new TaskForce("Christmas Party Organizers", employee4, new HashSet<>(Arrays.asList(employee2, employee5)));
        dei.printDescription();
        christmasParty.printDescription();
        testAutomation.printDescription();
        log.info("");

        // check whether an employee belongs to different CorporateUnits
        for (CorporateUnit corporateUnit : new ArrayList<>(Arrays.asList(testAutomation, qaAndTesting, resolvd, dei))) {
            log.info(new StringBuilder()
                    .append(corporateUnit.getName())
                    .append(": ")
                    .append(employee2.checkAffiliation(corporateUnit)));
        }

        // Homework #4
        log.info(formatHeader("Homework #4"));

        // use overridden TimeTracker's methods
        log.info(izaBoopLoop);
        log.info(izaBoopLoop.equals(jakubBoopLoop));
        log.info(izaBoopLoop.hashCode() + ", " + jakubBoopLoop.hashCode() + "\n");

        // use overridden Team's methods
        log.info(testAutomation);
        log.info(testAutomation.equals(dei));
        log.info(testAutomation.hashCode() + ", " + dei.hashCode());
        log.info("");

        // use a CorporateUnit's getAllEmployees() method on objects from different classes (ITCompany, Department, Team, Committee)
        List<CorporateUnit> corporateUnits = new ArrayList<>(Arrays.asList(resolvd, qaAndTesting, testAutomation, dei));
        for (CorporateUnit corporateUnit : corporateUnits) {
            log.info(corporateUnit.getName() + " consists of:");
            for (Employee employee : corporateUnit.getAllEmployees()) {
                log.info(employee.getName());
            }
            log.info("");
        }

        // use PayableEntity's pay() method
        try {
            jetBrains.pay(420);
        } catch (Exception e) {
            log.error(e);
        }
        try {
            employee4.pay(employee4.getHourlyWage() * 8);
        } catch (Exception e) {
            log.error(e);
        }
        log.info("");

        // create a task and close it
        Set<Stakeholder> task1Stakeholders = new HashSet<>(Arrays.asList(employee3, google));
        Task task1 = new Task("Whatchamacallit is shebanging in the BoopLoop's pinto logs and the mainframe hexing gets bungled ", employee4, task1Stakeholders, "open");
        log.info(task1.getStatus() + "\n");
        employee4.finishTask(task1);
        log.info("\n" + task1.getStatus());
        log.info("");

        // use SpaceRequester's method
        employee1.requestSpace();
        christmasParty.requestSpace();

        // i can't come up with where i might want to use a static block (fragment of code that gets executed exactly once). i'd appreciate any suggestions

        // Homework #5
        log.info(formatHeader("Homework #5"));

        // throw NumberEqualToZeroException
        try {
            employee4.pay(0);
        } catch (NumberEqualToZeroException | NegativeNumberException e) {
            log.error(e);
        }

        // throw NegativeNumberException
        try {
            jetBrains.pay(-2137);
        } catch (NumberEqualToZeroException | NegativeNumberException e) {
            log.error(e);
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
            log.info("Exported to '" + testAutomation.getName() + ".txt' successfully!\n");
        } catch (Exception e) {
            log.error(e);
        }

        // create class that implements the AutoCloseable interface and close it using the try with resources
        try (MysteriousObject mysteriousObject = new MysteriousObject()) {
            log.info("You've opened a mysterious object! It's" + mysteriousObject);
        } catch (Exception e) {
            log.error(e);
        }

        // throw ObjectAlreadyIncludedException
        testAutomation.printDescription();
        log.info("");

        try {
            testAutomation.addEmployee(employee3);
        } catch (ObjectAlreadyIncludedException e) {
            log.error(e);
        } finally {
            log.info("");
        }

        try {
            testAutomation.addEmployee(employee6);
        } catch (ObjectAlreadyIncludedException e) {
            log.error(e);
        } finally {
            log.info("");
        }

        // throw ObjectNotIncludedException
        try {
            testAutomation.removeEmployee(employee7);
        } catch (ObjectNotIncludedException e) {
            log.error(e);
        } finally {
            log.info("");
        }

        try {
            testAutomation.removeEmployee(employee6);
        } catch (ObjectNotIncludedException e) {
            log.error(e);
        } finally {
            log.info("");
        }

//        // EmptyListException (unchecked)
//        qualityAssurance.printEmployeesDescription();
//        log.fatal("This won't get printed");

        // Homework #6
        log.info(formatHeader("Homework #6"));

        // use Map and Set interfaces
        List<Employee> allEmployees = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6, employee7));
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
            log.info(key + " --- " + timeMap.get(key));
        }

        // Homework #8
        log.info(formatHeader("Homework #8"));

        // read text from the file and calculate the word count. use as few lines of code as possible
        try {
            List<String> words = new ArrayList<>(Arrays.asList(FileUtils.readFileToString(new File("src/main/resources/pratchett.txt"), "UTF-8").toLowerCase().split("[^a-z]+"))); // read file, split it into words, save words as List

            Map<String, Integer> uniqueWords = new TreeMap<>(); // create a Map for words and their counts
            words.stream().filter(key -> !key.isBlank()).forEach(key -> uniqueWords.put(key, (uniqueWords.containsKey(key) ? uniqueWords.get(key) + 1 : 1))); // put words and their counts into this Map

            StringBuilder content = new StringBuilder(); // create a StringBuilder for file content
            uniqueWords.forEach((key, val) -> content.append(val).append(val > 999 ? "\t" : "\t\t").append(StringUtils.capitalize(key)).append("\n")); // append this StringBuilder with each word and its count
            FileUtils.writeByteArrayToFile(new File("pratchett frequency.txt"), content.toString().getBytes()); // write this StringBuilder to file. not using write() bc it's deprecated

        } catch (Exception e) {
            log.error(e);
        }

        log.info("Check out the 'pratchett frequency.txt'!\n");

        // replace all syso with logger. log in two places: console and file
        // https://logging.apache.org/log4j/1.x/apidocs/org/apache/log4j/Level.html
        log.trace("TRACE Level designates finer-grained informational events than the DEBUG"); // this won't get logged
        log.debug("DEBUG Level designates fine-grained informational events that are most useful to debug an application."); // this won't get logged
        log.info("INFO level designates informational messages that highlight the progress of the application at coarse-grained level. ");
        log.warn("WARN level designates potentially harmful situations.");
        log.error("ERROR level designates error events that might still allow the application to continue running.");
        log.fatal("FATAL level designates very severe error events that will presumably lead the application to abort.");
    }
}