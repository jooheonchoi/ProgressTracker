package ui.panels;

import model.ListOfSubjects;
import model.Subject;
import ui.ProgressTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a jpanel tool for a given listofsubjects.
public class ListOfSubjectHelperPanel extends HelperPanel {

    private ListOfSubjects listOfSubjects;

    // EFFECTS: constructs a jpanel that helps to form the main screen showing the list of subjects.
    public ListOfSubjectHelperPanel(ProgressTracker progressTracker, ListOfSubjects listOfSubjects) {
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
                    progressTracker.updateSubjectPanel();
                    progressTracker.revalidate();
                    progressTracker.repaint();
                    progressTracker.getCardLayout().show(progressTracker.getCardPanel(), "2");
                }
            });
            add(button);
        }
    }

    public void addSubjectButton() {
        Subject s = listOfSubjects.getListOfSubjects().getFirst();
        JButton button = new JButton(s.getName());
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                progressTracker.setCurrentSubject(s);
                progressTracker.updateSubjectPanel();
                progressTracker.revalidate();
                progressTracker.repaint();
                progressTracker.getCardLayout().show(progressTracker.getCardPanel(), "2");
            }
        });
        add(button, 0);
        System.out.println(s.getName() + " has been created");
        revalidate();
        repaint();
    }

    public void deleteSubjectButton(String name) {
        for (Subject subject : listOfSubjects.getListOfSubjects()) {
            if (name.equals(subject.getName())) {
                int index = listOfSubjects.getListOfSubjects().indexOf(subject);
                listOfSubjects.getListOfSubjects().remove(index);
                remove(index);
                System.out.println(name + " has been deleted");

                revalidate();
                repaint();
            }
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

