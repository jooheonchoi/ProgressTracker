package persistence;

import model.ListOfSubjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of listofsubjects parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<ListOfSubjects> readAccounts(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of listofsubjects parsed from list of strings
    // where each string contains data for one account
    private static List<Account> parseContent(List<String> fileContent) {
        List<Account> accounts = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            accounts.add(parseAccount(lineComponents));
        }

        return accounts;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static ListOfSubjects parseAccount(List<String> components) {
        String creatorName = components.get(0);
        ListOfSubjects listOfSubjects = components.get(1);
        int id = Integer.parseInt(components.get(1));
        String name = components.get(2);
        double balance = Double.parseDouble(components.get(3));
        return new ListOfSubjects(nextId, id, name, balance);
    }

}
