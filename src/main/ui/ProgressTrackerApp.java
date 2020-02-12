package ui;

import model.ListOfSubjects;
import model.Subject;
import model.Update;

import java.util.Scanner;

public class ProgressTrackerApp {
    private Scanner input;
    private ListOfSubjects listOfSubjects;

    public ProgressTrackerApp() {
        listOfSubjects = new ListOfSubjects();
        runApp();
    }

    private void runApp() {         // based on TellerApp ui class
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        System.out.println("Track your progress with the ProgressTracker!");

        while (keepGoing) {
            displayAllSubjects();
            System.out.println("\na -> Add subject");
            System.out.println("q -> Exit");
            System.out.println("(Type subject name) -> View subject");
            command = input.nextLine();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nSee you next time!");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            addNewSubject();
            return;
        } else {
            for (Subject next : listOfSubjects.getListOfSubjects()) {
                if (command.equals(next.getName())) {
                    viewSubject(next);
                    return;
                }
            }
        }
        System.out.println("Enter valid command!");
    }

    private void viewSubject(Subject subject) {
        Boolean keepInView = true;
        while (keepInView) {
            displaySubject(subject);
            String command = "";             // from TellerApp
            while (!((command.equals("s") || (command.equals("u")) || (command.equals("q"))))) {
                System.out.println("s -> Set a goal");
                System.out.println("u -> Post an update");
                System.out.println("q -> Back");
                command = input.nextLine();
            }

            if (command.equals("s")) {
                goalSetter(subject);
            } else if (command.equals("u")) {
                postUpdate(subject);
            } else {
                keepInView = false;
            }
        }
    }

    private void displaySubject(Subject subject) {
        System.out.println(subject.getName());
        System.out.println("Current Goal: " + subject.getLongTermGoal() + "\n");
        for (Update update : subject.getUpdateLog()) {
            System.out.println(update.getReport() + "//" + update.getNextGoal());
        }
    }

    private void postUpdate(Subject subject) {
        System.out.println("Description of update:");
        String description = input.nextLine();

        System.out.println("Goal for next update:");
        String nextGoal = input.nextLine();

        Update newUpdate = new Update(description, nextGoal);
        subject.addUpdate(newUpdate);
        System.out.println("Update added!");
    }


    private void goalSetter(Subject subject) {
        System.out.println("Set a goal:");
        String goal = input.nextLine();
        subject.setLongTermGoal(goal);
        System.out.println("Goal set!");
    }

    private void addNewSubject() {
        System.out.println("Choose name of subject:");
        String name = input.nextLine();
        Subject newSubject = new Subject(name);
        listOfSubjects.addSubject(newSubject);
        System.out.println(name + " added!");
        viewSubject(newSubject);
    }

    private void displayAllSubjects() {
        if (listOfSubjects.isEmpty()) {
            System.out.println("Nothing added yet");
        } else {
            for (Subject next : listOfSubjects.getListOfSubjects()) {
                System.out.println(next.getName());
            }
        }
    }
}
