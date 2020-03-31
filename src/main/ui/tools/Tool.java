package ui.tools;

import ui.ProgressTracker;

import javax.swing.*;

// Represents an abstract for tools that are jpanels.
public abstract class Tool extends JPanel {

    protected static final int PANEL_WIDTH = 200;
    protected static final int PANEL_HEIGHT = 200;
    protected ProgressTracker progressTracker;

    // EFFECTS: Constructs a jpanel tool with a layout and dimension.
    public Tool() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
    }

    // EFFECTS: creates a jpanel for this tool
    public abstract void createPanel();
}
