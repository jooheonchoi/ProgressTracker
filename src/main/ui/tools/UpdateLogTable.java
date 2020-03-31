package ui.tools;

import model.Subject;
import model.Update;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a jpanel for an updatelog.
public class UpdateLogTable extends JPanel {

    private Subject subject;
    DefaultTableModel model;

    // EFFECTS: Constructs a jpanel for the table and associating elements of a subject's update log.
    public UpdateLogTable(Subject subject) {
        super(new GridLayout(1, 0));
//        super(new BorderLayout());
        this.subject = subject;

        String[] columns = {"Report", "Next Week's Goal"};

        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        updateRows();
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
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
        reportText.setColumns(30);
        JTextField nextWeekText = new JTextField();
        nextWeekText.setColumns(30);

        reportPanel.add(reportText);
        nextWeekPanel.add(nextWeekText);
        updatePanel.add(reportPanel);
        updatePanel.add(nextWeekPanel);
        JButton addReportButton = new JButton("Add Update");
        addReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!((reportText.getText().equals("")) || (nextWeekText.getText().equals("")))) {
                    subject.addUpdate(new Update(reportText.getText(), nextWeekText.getText()));
                }
                revalidate();
                repaint();
            }
        });
        updatePanel.add(addReportButton);
        add(updatePanel);
    }

    // EFFECTS: make a jpanel with an updatepanel's label
    public JPanel makeUpdatePanel() {
        JPanel updatePanel = new JPanel();
        JLabel label = new JLabel("New Update:");
        updatePanel.add(label);
        return updatePanel;
    }

    // EFFECTS: make a jpanel with a report's label
    public JPanel makeReportPanel() {
        JPanel reportPanel = new JPanel();
        JLabel label = new JLabel("Report:");
        reportPanel.add(label);
        return reportPanel;
    }

    // EFFECTS: make a jpanel with a next week's goal's label
    public JPanel makeNextWeekPanel() {
        JPanel nextWeekPanel = new JPanel();
        JLabel label = new JLabel("Goal for next week:");
        nextWeekPanel.add(label);
        return nextWeekPanel;
    }
}




