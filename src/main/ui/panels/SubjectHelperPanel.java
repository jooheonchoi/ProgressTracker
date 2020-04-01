package ui.panels;


import model.Subject;
import ui.ProgressTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a jpanel tool for a given subject.
public class SubjectHelperPanel extends HelperPanel {

    private Subject subject;
    private UpdateLogTable updateLogTable;

    // EFFECTS: Constructs a subject panel for a given progressTracker and subject.
    public SubjectHelperPanel(ProgressTracker progressTracker, Subject subject) {
        super();
        this.progressTracker = progressTracker;
        this.subject = subject;
        updateLogTable = new UpdateLogTable(subject);
        createPanel();
        add(updateLogTable);
    }


    // MODIFIES: this
    // EFFECTS: adds a "back" button (to go back to the main screen),
    //          a label with this subject's name,
    //          and a table of updates for this subject.
    @Override
    public void createPanel() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressTracker.getCardLayout().show(progressTracker.getCardPanel(), "1");
            }
        });
        add(backButton);
        JLabel subjectLabel = new JLabel(subject.getName());
        JLabel goalLabel = new JLabel("Goal: " + subject.getBigGoal());
        add(subjectLabel);
        add(goalLabel);
    }


}
