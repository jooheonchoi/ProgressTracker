package ui.panels;

import model.Subject;
import model.Update;
import ui.ProgressTracker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// Represents a jpanel for an updatelog.
public class UpdateTablePanel extends JPanel {

    private Subject subject;
    private static final int DISTANCE_BETWEEN_TEXTFIELD = 40;
    DefaultTableModel model;

    // EFFECTS: Constructs a jpanel for the table and associating elements of a subject's update log.
    public UpdateTablePanel(Subject subject) {
        super(new GridLayout(1, 0));
        this.subject = subject;

        String[] columns = {"Report", "Next Week's Goal"};

        // Citation: I learned of the defaultTableModel from Piazza and code inspired by
        // https://stackoverflow.com/questions/20526917/load-arraylist-data-into-jtable
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        updateRows();

        // Citation: simple table demo on java swing tutorials
        table.setPreferredScrollableViewportSize(new Dimension(ProgressTracker.FRAME_WIDTH, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        addUpdatePanel(makeUpdatePanel(), makeReportPanel(), makeNextWeekPanel());
        add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: turns each update in the log into an array and adds the arrays into the default table model.
    public void updateRows() {
        for (Update update : subject.getUpdateLog()) {
            Object[] row = new Object[2];
            row[0] = update.getReport();
            row[1] = update.getNextGoal();
            model.addRow(row);
        }
    }

    // MODIFIES: this
    // EFFECTS: Makes a panel that lets you add an update; makes a text field for report and next week's goal,
    //          then appends the text field for a report and for next week's goal;
    //          if either report or next week's goal are empty, don't add the update to the update log.
    public void addUpdatePanel(JPanel updatePanel, JPanel reportPanel, JPanel nextWeekPanel) {

        JTextField reportText = new JTextField();
        reportText.setColumns(15);
        JTextField nextWeekText = new JTextField();
        nextWeekText.setColumns(15);

        reportPanel.add(reportText);
        nextWeekPanel.add(nextWeekText);

        updatePanel.add(reportPanel);
        updatePanel.add(nextWeekPanel);

        JButton addUpdateButton = getAddUpdateButton(reportText, nextWeekText);
        addUpdateButton.setBounds(0, DISTANCE_BETWEEN_TEXTFIELD * 2, ProgressTracker.FRAME_WIDTH / 2,
                DISTANCE_BETWEEN_TEXTFIELD);
        updatePanel.add(addUpdateButton);
        add(updatePanel);
    }

    // MODIFIES: this
    // EFFECTS: adds a jbutton to add an update. If neither string is empty, then make an update from the strings
    //          from the text fields and add to model
    public JButton getAddUpdateButton(JTextField reportText, JTextField nextWeekText) {
        JButton addUpdateButton = new JButton("Add Update");
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!((reportText.getText().equals("")) || (nextWeekText.getText().equals("")))) {
                    subject.addUpdate(new Update(reportText.getText(), nextWeekText.getText()));
                    Object[] row = new Object[2];
                    row[0] = reportText.getText();
                    row[1] = nextWeekText.getText();
                    model.insertRow(0, row);
                }
            }
        });
        return addUpdateButton;
    }

    // EFFECTS: make a jpanel with an updatepanel's label
    public JPanel makeUpdatePanel() {
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(null);
        JLabel label = new JLabel("New Update:");
        updatePanel.add(label);
        return updatePanel;
    }

    // EFFECTS: make a jpanel with a report's label
    public JPanel makeReportPanel() {
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(new FlowLayout());
        reportPanel.setBounds(0, 0, ProgressTracker.FRAME_WIDTH / 2, DISTANCE_BETWEEN_TEXTFIELD);
        JLabel label = new JLabel("Report:");
        reportPanel.add(label);
        return reportPanel;
    }

    // EFFECTS: make a jpanel with a next week's goal's label
    public JPanel makeNextWeekPanel() {
        JPanel nextWeekPanel = new JPanel();
        JLabel label = new JLabel("Next week's goal: ");
        nextWeekPanel.setBounds(0, DISTANCE_BETWEEN_TEXTFIELD,
                ProgressTracker.FRAME_WIDTH / 2, DISTANCE_BETWEEN_TEXTFIELD);
        nextWeekPanel.add(label);
        return nextWeekPanel;
    }
}




