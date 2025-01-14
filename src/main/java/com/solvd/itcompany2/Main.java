package com.solvd.itcompany2;

import com.solvd.itcompany2.corporatestructure.*;
import com.solvd.itcompany2.exceptions.*;
import com.solvd.itcompany2.helpers.Territory;
import com.solvd.itcompany2.helpers.ThreadExtension;
import com.solvd.itcompany2.helpers.ThreadRunnable;
import com.solvd.itcompany2.outsideentities.Client;
import com.solvd.itcompany2.outsideentities.InternetServiceProvider;
import com.solvd.itcompany2.outsideentities.Provider;
import com.solvd.itcompany2.outsideentities.SoftwareVendor;
import com.solvd.itcompany2.projectresources.*;
import com.solvd.itcompany2.techstack.Skill;
import com.solvd.itcompany2.techstack.Tool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.solvd.itcompany2.helpers.Formatter.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Homework #2
        LOGGER.info(formatHeader("Homework #2"));

        // create employees
        Employee employee1 = new Employee(1, "Karol Zakrętka", Territory.ANTARCTICA, "2002-01-09", 714.0);
        Employee employee2 = new Employee(2, "Zofia Cętka", Territory.CALIFORNIA, "2013-12-29", 301.7);
        Employee employee3 = new Employee(3, "Michał Dętka", Territory.JAPAN, "2017-07-07", 162.0);
        Employee employee4 = new Employee(4, "Izabella Jętka", Territory.POLAND, "2024-10-28", 92.93);
        Employee employee5 = new Employee(5, "Jakub Piętka", Territory.PORTUGAL, "2024-04-29", 87.21);
        Employee employee6 = new Employee(6, "Agata Smętka", Territory.POLAND, "2024-04-27", 107.03);
        Employee employee7 = new Employee(7, "Maciej Kocimiętka", Territory.SOUTH_KOREA, "2022-11-07", 111.76);

        // create teams
        Team testAutomation = new Team("Test Automation", employee3, new ArrayList<>(Arrays.asList(employee4, employee5)));
        Team qualityAssurance = new Team();

        // print some of employees' data
        employee1.printTimeZone();
        LOGGER.info("");
        try {
            testAutomation.printEmployeesDescription();
        } catch (EmptyListException e) {
            LOGGER.error(e);
        }
        employee2.printWorkYears();
        LOGGER.info("");

        //create departments
        Department qaAndTesting = new Department("Quality Assurance and Testing", employee2, new ArrayList<>(Arrays.asList(testAutomation, qualityAssurance)));
        Department productAndDesign = new Department();

        // create a company
        ITCompany resolvd = new ITCompany();
        resolvd.setName("Resolvd");
        resolvd.setCeo(employee1);
        resolvd.setDepartments(new ArrayList<>(Arrays.asList(qaAndTesting, productAndDesign)));

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
        LOGGER.info(boopLoop.getPersonHours());
        LOGGER.info(izaBoopLoop.getPersonHours());
        LOGGER.info(jakubBoopLoop.getPersonHours() + "\n");

        // count project earnings
        LOGGER.info(boopLoop.getEarnings() + "\n");

        // create a tool
        List<Employee> intellijEmployees = new ArrayList<>(Arrays.asList(employee3, employee1));
        Tool intellij = new Tool("IntelliJ IDEA Ultimate (Enterprise Edition)", intellijEmployees);

        // check an employee's access to a tool
        LOGGER.info(intellij.checkAccess(employee3));
        LOGGER.info(intellij.checkAccess(employee1) + "\n");

        // create a skill
        List<Employee> programmingJavaEmployees = new ArrayList<>(Arrays.asList(employee1, employee3, employee4, employee5));
        Skill programmingJava = new Skill("Programming in Java", programmingJavaEmployees);

        // check skilled employees' access to a tool
        programmingJava.printAccess(intellij);

        // Homework #3
        LOGGER.info(formatHeader("Homework #3"));

        // print employees' hash codes
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6));
        employeeList.forEach(employee -> LOGGER.info(employee.hashCode()));
        LOGGER.info("");

        // print toString's output for all employees
        employeeList.forEach(employee -> LOGGER.info(employee.toString()));
        LOGGER.info("");

        // compare all employees to employee6
        employeeList.forEach(employee -> LOGGER.info(employee.equals(employee6)));
        LOGGER.info("");

        // you can find comments about overriding in the Employee class

        // create service providers and call them
        Provider inea = new InternetServiceProvider("Inea", "112", "Poland");
        List<Tool> jetBrainsTools = new ArrayList<>(Arrays.asList(intellij));
        Provider jetBrains = new SoftwareVendor("JetBrains", "987123654", jetBrainsTools);
        inea.phoneCall();
        jetBrains.phoneCall();

        // create a committe & a taskforce, then print their descriptions
        Team dei = new Committee("Diversity, Equity and Inclusion", employee2, new ArrayList<>(Arrays.asList(employee3, employee4)));
        Team christmasParty = new TaskForce("Christmas Party Organizers", employee4, new ArrayList<>(Arrays.asList(employee2, employee5)));
        new ArrayList<>(Arrays.asList(dei, christmasParty, testAutomation)).stream().forEach(team -> team.printDescription());
        LOGGER.info("");

        // check whether an employee belongs to different CorporateUnits
        for (CorporateUnit corporateUnit : new ArrayList<>(Arrays.asList(testAutomation, qaAndTesting, resolvd, dei))) {
            LOGGER.info(new StringBuilder()
                    .append(corporateUnit.getName())
                    .append(": ")
                    .append(employee2.checkAffiliation(corporateUnit)));
        }

        // Homework #4
        LOGGER.info(formatHeader("Homework #4"));

        // use overridden TimeTracker's methods
        LOGGER.info(izaBoopLoop);
        LOGGER.info(izaBoopLoop.equals(jakubBoopLoop));
        LOGGER.info(izaBoopLoop.hashCode() + ", " + jakubBoopLoop.hashCode() + "\n");

        // use overridden Team's methods
        LOGGER.info(testAutomation);
        LOGGER.info(testAutomation.equals(dei));
        LOGGER.info(testAutomation.hashCode() + ", " + dei.hashCode());
        LOGGER.info("");

        // use a CorporateUnit's getAllEmployees() method on objects from different classes (ITCompany, Department, Team, Committee)
        List<CorporateUnit> corporateUnits = new ArrayList<>(Arrays.asList(resolvd, qaAndTesting, testAutomation, dei));
        for (CorporateUnit corporateUnit : corporateUnits) {
            LOGGER.info(corporateUnit.getName() + " consists of:");
            corporateUnit.getAllEmployees().forEach(employee -> LOGGER.info(employee.getName()));
            LOGGER.info("");
        }

        // use PayableEntity's pay() method
        try {
            jetBrains.pay(420);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            employee4.pay(employee4.getHourlyWage() * 8);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("");

        // create a task and close it
        List<Stakeholder> task1Stakeholders = new ArrayList<>(Arrays.asList(employee3, google));
        Task task1 = new Task("Whatchamacallit is shebanging in the BoopLoop's pinto logs and the mainframe hexing gets bungled ", employee4, task1Stakeholders, TaskStatus.OPEN);
        LOGGER.info(task1.getStatus() + "\n");
        employee4.finishTask(task1);
        LOGGER.info("\n" + task1.getStatus());
        LOGGER.info("");

        // use SpaceRequester's method
        employee1.requestSpace();
        christmasParty.requestSpace();

        // i can't come up with where i might want to use a static block (fragment of code that gets executed exactly once). i'd appreciate any suggestions

        // Homework #5
        LOGGER.info(formatHeader("Homework #5"));

        // throw NumberEqualToZeroException
        try {
            employee4.pay(0);
        } catch (NumberEqualToZeroException | NegativeNumberException e) {
            LOGGER.error(e);
        }

        // throw NegativeNumberException
        try {
            jetBrains.pay(-2137);
        } catch (NumberEqualToZeroException | NegativeNumberException e) {
            LOGGER.error(e);
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
                        .append(employee.getTerritory().getTimeZone())
                        .append("\n")
                        .toString();
                text = text.concat(employeeDescription);
            }
            fos.write(text.getBytes());
            LOGGER.info("Exported to '" + testAutomation.getName() + ".txt' successfully!\n");
        } catch (Exception e) {
            LOGGER.error(e);
        }

        // create class that implements the AutoCloseable interface and close it using the try with resources
        try (MysteriousObject mysteriousObject = new MysteriousObject()) {
            LOGGER.info("You've opened a mysterious object! It's" + mysteriousObject);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        // throw ObjectAlreadyIncludedException
        testAutomation.printDescription();
        LOGGER.info("");

        try {
            testAutomation.addEmployee(employee3);
        } catch (ObjectAlreadyIncludedException e) {
            LOGGER.error(e);
        } finally {
            LOGGER.info("");
        }

        try {
            testAutomation.addEmployee(employee6);
        } catch (ObjectAlreadyIncludedException e) {
            LOGGER.error(e);
        } finally {
            LOGGER.info("");
        }

        // throw ObjectNotIncludedException
        try {
            testAutomation.removeEmployee(employee7);
        } catch (ObjectNotIncludedException e) {
            LOGGER.error(e);
        } finally {
            LOGGER.info("");
        }

        try {
            testAutomation.removeEmployee(employee6);
        } catch (ObjectNotIncludedException e) {
            LOGGER.error(e);
        } finally {
            LOGGER.info("");
        }

//        // EmptyListException (unchecked)
//        qualityAssurance.printEmployeesDescription();
//        LOGGER.fatal("This won't get printed");

        // Homework #6
        LOGGER.info(formatHeader("Homework #6"));

        // use Map and Set interfaces
        List<Employee> allEmployees = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6, employee7));
        SortedMap<String, Set<String>> timeMap = new TreeMap<>(); // SortedMap is an interface (cannot be instantiated), but TreeMap is a class

        allEmployees.forEach(employee -> timeMap.put(employee.getTime(), new TreeSet<>()));

        for (Employee employee : allEmployees) {
            String time = employee.getTime();
            String name = employee.getName();

            Set<String> allNames = timeMap.get(time); // add employees' names to timeMap values
            allNames.add(name);
            timeMap.put(time, allNames);
        }

        timeMap.keySet().forEach(key -> LOGGER.info(key + " --- " + timeMap.get(key)));

        // Homework #8
        LOGGER.info(formatHeader("Homework #8"));

        // read text from the file and calculate the word count. use as few lines of code as possible
        try {
            List<String> words = new ArrayList<>(Arrays.asList(FileUtils.readFileToString(new File("src/main/resources/pratchett.txt"), "UTF-8").toLowerCase().split("[^a-z]+"))); // read file, split it into words, save words as List

            Map<String, Integer> uniqueWords = new TreeMap<>(); // create a Map for words and their counts
            words.stream().filter(key -> !key.isBlank()).forEach(key -> uniqueWords.put(key, (uniqueWords.containsKey(key) ? uniqueWords.get(key) + 1 : 1))); // put words and their counts into this Map

            StringBuilder content = new StringBuilder(); // create a StringBuilder for file content
            uniqueWords.forEach((key, val) -> content.append(val).append(val > 999 ? "\t" : "\t\t").append(StringUtils.capitalize(key)).append("\n")); // append this StringBuilder with each word and its count
            FileUtils.writeByteArrayToFile(new File("pratchett frequency.txt"), content.toString().getBytes()); // write this StringBuilder to file. not using write() bc it's deprecated

        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.info("Check out the 'pratchett frequency.txt'!\n");

        // replace all syso with logger. log in two places: console and file
        // https://logging.apache.org/log4j/1.x/apidocs/org/apache/log4j/Level.html
        LOGGER.trace("TRACE Level designates finer-grained informational events than the DEBUG"); // this won't get logged
        LOGGER.debug("DEBUG Level designates fine-grained informational events that are most useful to debug an application."); // this won't get logged
        LOGGER.info("INFO level designates informational messages that highlight the progress of the application at coarse-grained level. ");
        LOGGER.warn("WARN level designates potentially harmful situations.");
        LOGGER.error("ERROR level designates error events that might still allow the application to continue running.");
        LOGGER.fatal("FATAL level designates very severe error events that will presumably lead the application to abort.");

        // Homework #9
        LOGGER.info(formatHeader("Homework #9"));

        LOGGER.info("Consumer:\tTakes an input and performs an operation.");
        LOGGER.info("Supplier:\tProvides a result without taking any input.");
        LOGGER.info("Function:\tTakes an input and returns a transformed result.");
        LOGGER.info("Predicate:\tTakes an input and returns a boolean value.");
        LOGGER.info("Runnable:\tRepresents a task that takes no input and returns no result.\n");

        // use Runnable, Function (formatName in Office enum)
        Runnable printAllOfficesInfo = () -> Arrays.stream(Office.values()).forEach(office -> LOGGER.info(office.getInfo()));
        printAllOfficesInfo.run();

        // use BiPredicate
        BiPredicate<Employee, CorporateUnit> checkAffiliation = (employee, unit) -> unit.getAllEmployees().contains(employee);
        allEmployees.forEach(employee -> LOGGER.info(employee.getName() + ": " + checkAffiliation.test(employee, testAutomation)));

        // use Consumer
        LOGGER.info("\n" + employee4.getHourlyWage());
        Consumer<Employee> raiseWage = employee -> employee.setHourlyWage(employee.getHourlyWage() * 2);
        raiseWage.andThen(raiseWage).accept(employee4);
        LOGGER.info(employee4.getHourlyWage());

        // use Supplier
        Supplier<String> getTaskStatuses = () -> Arrays.toString(TaskStatus.values());
        LOGGER.info("\nYou can set a task's status to: " + getTaskStatuses.get() + "\n");

        // create a lambda with generic
        Consumer<Object> colorify = item -> LOGGER.info(ansiColor(new Random().nextInt(31, 36)) + item.toString() + ansiColor(reset));
        colorify.accept("i have no other ideas for a generic lambda");
        colorify.accept(398722226);
        colorify.accept(employee4);

        // Homework #10
        LOGGER.info(formatHeader("Homework #10"));

        // use stream().foreach ---> printAllOfficesInfo in Main
        // use stream().anyMatch ---> checkAccess() in Tool

        // use stream().allMatch
        boolean over80 = testAutomation.getEmployees().stream().allMatch(employee -> employee.getHourlyWage() > 80);
        LOGGER.info("Are all test automation employees making over 80 pln/hour?: " + over80 + "\n");

        // get employees from teams that are not empty. use stream().filter, .flatMap, .map, .peek, .collect
        qualityAssurance.setEmployees(new ArrayList<>(Arrays.asList(employee6, employee7)));
        Set<String> realTeamEmployeeNames = resolvd.getDepartments().stream().filter(department -> department.getTeams() != null)
                .flatMap(department -> department.getTeams().stream().filter(team -> team.getEmployees() != null))
                .flatMap(team -> team.getAllEmployees().stream().filter(employee -> employee.getName() != null))
                .map(employee -> employee.getName())
                .peek(name -> LOGGER.info(name))
                .collect(Collectors.toSet());

        // use stream().count
        LOGGER.info("\nNumber of all employees in Resolvd inc: " + resolvd.getAllEmployees().stream().count());

        // use stream().findFirst, return Optional<>
        Optional<String> earliest = resolvd.getAllEmployees().stream()
                .map(employee -> employee.getTerritory().getTimeHour())
                .sorted().findFirst();
        earliest.ifPresent(content -> LOGGER.info("Right now, the earliest hour in our employees' locations is " + content));

        // use reflection to extract info about fields, constructors, methods (modifiers, return types, parameters)
        Class classToPrint = qaAndTesting.getClass();

        LOGGER.info(formatHeader2("Class Fields"));
        for (Field field : classToPrint.getDeclaredFields()) {
            LOGGER.info("Field:\t\t\t" + field.getName());
            LOGGER.info("Modifier:\t\t" + (Modifier.toString(field.getModifiers())));
            LOGGER.info("Type:\t\t\t" + field.getType());
            LOGGER.info("---");
        }

        LOGGER.info(formatHeader2("Class Constructors"));
        for (Constructor constructor : classToPrint.getConstructors()) {
            LOGGER.info("Constructor:\t" + constructor.getName());
            LOGGER.info("Modifier:\t\t" + (Modifier.toString(constructor.getModifiers())));
            List<Type> parameterTypes = Arrays.stream(constructor.getParameterTypes()).collect(Collectors.toList());
            LOGGER.info(parameterTypes.size() + " Parameters:\t" + parameterTypes);
            LOGGER.info("---");
        }

        LOGGER.info(formatHeader2("Class Methods"));
        for (Method method : classToPrint.getDeclaredMethods()) {
            LOGGER.info("Method:\t\t\t" + method.getName());
            LOGGER.info("Modifier:\t\t" + (Modifier.toString(method.getModifiers())));
            LOGGER.info("Return type:\t" + method.getReturnType());
            List<Type> parameterTypes = Arrays.stream(method.getParameterTypes()).collect(Collectors.toList());
            LOGGER.info(parameterTypes.size() + " Parameters:\t" + parameterTypes);
            LOGGER.info("---");
        }

        // using only reflection: create object and call method
        try {
            Class<?> reflectedClass = Class.forName(classToPrint.getName());
            Constructor<?> constructor = reflectedClass.getConstructor();
            Object newObject = constructor.newInstance();
            Method reflectedMethod = reflectedClass.getMethod("getName");

            LOGGER.info("\n" + newObject);
            LOGGER.info("\n" + reflectedMethod.invoke(newObject));

        } catch (Exception e) {
            LOGGER.error(e);
        }

        // Homework #11
        LOGGER.info(formatHeader("Homework #11"));

        LOGGER.info("Active threads: " + Thread.activeCount() + "\n");

        // create a thread using Runnable
        ThreadRunnable thread = new ThreadRunnable(1);
        Thread threadCounter = new Thread(thread);
        threadCounter.start();

        // create threads using Thread. use ThreadPool
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 7; i++) {
            executor.submit(new ThreadExtension(i));
        }
        executor.shutdown();

        // use CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // just pretend that some operations are taking place here
            try {
                Thread.sleep(6500);
            } catch (Exception e) {
                LOGGER.warn(e);
            }

            return "42";
        });

        String result = future.join();
        LOGGER.info("\nSo guess when this line gets logged?"); // future.join() blocks the thread until the future's completed
        LOGGER.info("\n*beep boop*\nThe Answer to the Ultimate Question of Life, the Universe, and Everything:\n.\n.\n.\n" + result);
    }
}