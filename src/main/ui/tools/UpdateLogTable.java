package ui.tools;

import model.Subject;
import model.Update;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UpdateLogTable extends JPanel {

    private Subject subject;
    private DefaultTableModel tableModel;

    public UpdateLogTable(Subject subject) {
        super(new GridLayout(1, 0));
        this.subject = subject;

        String[] columns = {"Report", "Next Week's Goal"};

        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        addUpdateRows();
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);


        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void addUpdateRows() {
        for (Update update : subject.getUpdateLog()) {
            Object[] row = {update.getReport(), update.getNextGoal()};
            tableModel.addRow(row);
        }
    }
}
