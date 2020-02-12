package model;

// Represents an update on progress.
public class Update {
    private String report;
    private String nextGoal;

    public Update(String report, String nextGoal) {
        this.report = report;
        this.nextGoal = nextGoal;
    }

    // MODIFIES: this
    // EFFECTS: sets the report for this week
    public void setReport(String report) {
        this.report = report;
    }

    // MODIFIES: this
    // EFFECTS: sets the next goal
    public void setNextGoal(String nextGoal) {
        this.nextGoal = nextGoal;
    }

    //EFFECTS: returns report
    public String getReport() {
        return report;
    }

    //EFFECTS: returns the next goal
    public String getNextGoal() {
        return nextGoal;
    }
}

