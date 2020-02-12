package model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

// A subject of interest
public class Subject {
    private String name;
    private LinkedList<Update> updateLog;
    private String longTermGoal;

    public Subject(String name) {
        this.name = name;
        updateLog = new LinkedList<Update>();
        longTermGoal = "";
    }

    // MODIFIES: this
    // EFFECTS: change the name of subject
    public void changeName(String newName) {
        this.name = newName;
    }

    // MODIFIES: this
    // EFFECTS: set the long term goal
    public void setLongTermGoal(String longTermGoal) {
        this.longTermGoal = longTermGoal;
    }

    // EFFECTS: return the name
    public String getName() {
        return name;
    }

    // EFFECTS: return the update log
    public LinkedList<Update> getUpdateLog() {
        return updateLog;
    }

    // MODIFIES: this
    // EFFECTS: add an update to the front of the log
    public void addUpdate(Update update) {
        updateLog.addFirst(update);
    }

    // MODIFIES: this
    // EFFECTS: remove an update from the log
    public void removeUpdate(Update update) {
        updateLog.remove(update);
    }

    //EFFECTS: returns last update
    public Update getLatestUpdate() {
        return updateLog.getFirst();
    }

    // EFFECTS: return the long term goal date
    public String getLongTermGoal() {
        return longTermGoal;
    }


}

