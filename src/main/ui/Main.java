package ui;

// Main for the Progress Tracker app.
public class Main {
    public static void main(String[] args) {

        // Citation: invoke learned from java swing tutorials (too many to count)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProgressTracker();
            }
        });
    }
}
