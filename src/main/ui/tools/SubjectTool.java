package ui.tools;


import model.Subject;
import ui.ProgressTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectTool extends Tool {

    private Subject subject;
    private UpdateLogTable updateLogTable;

    public SubjectTool(ProgressTracker progressTracker, Subject subject) {
        super();
        this.progressTracker = progressTracker;
        this.subject = subject;
        createPanel();
        updateLogTable = new UpdateLogTable(subject);
        add(updateLogTable);
    }

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

        add(updateLogTable);
    }
}
