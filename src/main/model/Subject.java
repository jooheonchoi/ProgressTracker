package model;

import java.util.Date;
import java.util.GregorianCalendar;

// A subject of interest
public class Subject {
    private String name;
    private UpdateLog updateLog;
    private GregorianCalendar longTermGoal; //TODO: Gregorian calendar

    public Subject(String name) {
        this.name = name;
        updateLog = new UpdateLog();
        longTermGoal = null;
    }

    // MODIFIES: this
    // EFFECTS: change the name of subject
    public void changeName(String newName) {
        this.name = newName;
    }

    // MODIFIES: this
    // EFFECTS: set the long term goal
    public void setLongTermGoal(GregorianCalendar longTermGoal) {
        this.longTermGoal = longTermGoal;
    }

    // EFFECTS: return the name
    public String getName() {
        return name;
    }

    // EFFECTS: return the update log
    public UpdateLog getUpdateLog() {
        return updateLog;
    }

    // EFFECTS: return the long term goal date
    public GregorianCalendar getLongTermGoal() {
        return longTermGoal;
    }
}

