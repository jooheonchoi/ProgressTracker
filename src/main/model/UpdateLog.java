package model;

import java.util.LinkedList;
import java.util.List;

// A log of updates.
public class UpdateLog {
    private LinkedList<Update> updateLog;

    public UpdateLog() {
        updateLog = new LinkedList<Update>();
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
}
