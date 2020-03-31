package ui.tools;

import ui.ProgressTracker;

import javax.swing.*;

public abstract class Tool extends JPanel {

    protected static final int PANEL_WIDTH = 200;
    protected static final int PANEL_HEIGHT = 200;
    protected ProgressTracker progressTracker;

    public Tool() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
    }

    public abstract void createPanel();
}
