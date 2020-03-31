package ui.tools;

import model.ListOfSubjects;
import model.Subject;
import ui.ProgressTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListOfSubjectTool extends Tool {



    private ListOfSubjects listOfSubjects;

    public ListOfSubjectTool(ProgressTracker progressTracker, ListOfSubjects listOfSubjects) {
        super();
        this.progressTracker = progressTracker;
        this.listOfSubjects = listOfSubjects;
        createPanel();
    }

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

