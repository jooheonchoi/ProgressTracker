package model;

import java.util.LinkedList;

public class ListOfSubjects {
    private LinkedList<Subject> listOfSubjects;

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
    public void removeSubject(String name) {
        for (Subject next : listOfSubjects) {
            if (name.equals(next.getName())) {
                listOfSubjects.remove(next);
                return;
            }
        }
    }


}
