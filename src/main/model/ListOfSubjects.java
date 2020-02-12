package model;

import java.util.LinkedList;

public class ListOfSubjects {
    private LinkedList<Subject> listOfSubjects;

    public ListOfSubjects() {
        listOfSubjects = new LinkedList<Subject>();
    }

    public void addSubject(Subject subject) {
        listOfSubjects.addFirst(subject);
    }

    public LinkedList<Subject> getListOfSubjects() {
        return listOfSubjects;
    }

    public boolean isEmpty() {
        return (listOfSubjects.isEmpty());
    }


}
