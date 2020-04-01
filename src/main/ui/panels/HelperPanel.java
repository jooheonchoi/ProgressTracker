package ui.panels;

import ui.ProgressTracker;

import javax.swing.*;

import static ui.ProgressTracker.FRAME_HEIGHT;
import static ui.ProgressTracker.FRAME_WIDTH;

// Represents an abstract for tools that are jpanels.
public abstract class HelperPanel extends JPanel {

    protected ProgressTracker progressTracker;

    // EFFECTS: Constructs a jpanel helper with a layout and dimension.
    public HelperPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(FRAME_WIDTH, FRAME_HEIGHT * 5 / 6);
    }

    // EFFECTS: creates a jpanel for this tool
    public abstract void createPanel();
}
