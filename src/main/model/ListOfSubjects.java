package model;

import persistence.Saveable;

import java.io.PrintWriter;
import java.util.LinkedList;

// A list of subjects.
public class ListOfSubjects {
    private LinkedList<Subject> listOfSubjects;
    private String creatorName;

    // EFFECTS: constructs a new list of subjects
    public ListOfSubjects() {
        listOfSubjects = new LinkedList<Subject>();
    }

    // MODIFIES: this
    // EFFECTS: add a subject to the beginning of the list
    public void addSubject(Subject subject) {
        listOfSubjects.addFirst(subject);
    }

    // EFFECTS: returns list of subjects
    public LinkedList<Subject> getListOfSubjects() {
        return listOfSubjects;
    }

    // EFFECTS: return true if the list of subjects is empty
    public boolean isEmpty() {
        return (listOfSubjects.isEmpty());
    }

    // MODIFIES: this
    // EFFECTS: remove a subject with given name; if no such subject exists, don't do anything
    public int removeSubject(String name) {
        for (Subject next : listOfSubjects) {
            if (name.equals(next.getName())) {
                listOfSubjects.remove(next);
                return 1;
            }
        }
        return -1;
    }
}

//    @Override
//    public void save(PrintWriter printWriter) {
//        printWriter.print(creatorName);
//        printWriter.print(Reader.DELIMITER);
//        printWriter.print(listOfSubjects);


