package ui.tools;

import model.ListOfSubjects;
import model.Subject;
import ui.ProgressTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a jpanel tool for a given listofsubjects.
public class ListOfSubjectTool extends Tool {

    private ListOfSubjects listOfSubjects;

    // EFFECTS: constructs a jpanel that helps to form the main screen showing the list of subjects.
    public ListOfSubjectTool(ProgressTracker progressTracker, ListOfSubjects listOfSubjects) {
        super();
        this.progressTracker = progressTracker;
        this.listOfSubjects = listOfSubjects;
        createPanel();
    }

    // MODIFIES: this
    // EFFECTS: adds a button for each subject in the list of subjects that goes to the subject page when pressed.
    //
    public void createPanel() {
        for (Subject next : listOfSubjects.getListOfSubjects()) {
            JButton button = new JButton(next.getName());
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    progressTracker.setCurrentSubject(next);
                    progressTracker.getCardLayout().show(progressTracker.getCardPanel(), "2");

                }
            });
            add(button);
        }
    }
}

//    private class ListOfSubjectToolHandler implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            progressTracker.removeAll();
//        }
//    }

