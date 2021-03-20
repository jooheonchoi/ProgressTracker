package ui;

import model.ListOfSubjects;
import model.Subject;
import persistence.Reader;
import persistence.Writer;
import ui.panels.ListOfSubjectHelperPanel;
import ui.panels.SubjectHelperPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


// User interface for the ProgressTracker.
// WIth GUI after P3.
//** I had the idea of the ProgressTracker last term when I took this course, but only wrote < 10 lines of code.
//      I withdrew from the course and am now using this idea again using brand new code.
//      HOWEVER, some classes will inevitably have the same name; I got the OK from the instructor about this.
public class ProgressTracker extends JFrame {

    private ListOfSubjects listOfSubjects;
    public static final int FRAME_WIDTH = 700;
    public static final int FRAME_HEIGHT = 900;
    private static final int TEXTFIELD_WIDTH = 40;

    private ImageIcon check;
    private JPanel mainPanel;
    private JPanel subjectPanel;
    private JPanel cardPanel;
    private CardLayout cl;
    private Subject currentSubject;

    private ListOfSubjectHelperPanel losHelper;
    private SubjectHelperPanel subjectHelper;

    private static final String DATA_FILE = "data/ProgressTracker.json";

    // Constructs the progressTracker app.
    public ProgressTracker() {
        super("ProgressTracker");
        initializeFields();

        loadDialogue();

        setMainPanel();
        setSubjectPanel();
        setCardLayout();
        setTitlePanel();
    }

    
    // MODIFIES: this
    // EFFECTS: instantiates the fields.
    public void initializeFields() {
        check = new ImageIcon("data/checkIcon.png");
        mainPanel = new JPanel();
        subjectPanel = new JPanel();
        cardPanel = new JPanel();
        currentSubject = null;

        // Citation: CardLayout inspiration from
        // https://www.youtube.com/watch?v=sAReaaTxNGU&list=PLnWWOcNcMFpV5pzbJDf4r1cdKXU2zp52r&index=4&t=475s
        cl = new CardLayout();
    }

    // MODIFIES: this
    // EFFECTS: sets this frame; sets its layout and size,
    //                           initializes a panel for the title and the check-image,
    //                           adds the title panel and card panel to this frame,
    //                           ands sets closing actions before setting to visible.
    public void setTitlePanel() {
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT / 6);

        JLabel title = new JLabel("ProgressTracker");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));

        JLabel image = new JLabel(check);
        image.setPreferredSize(new Dimension(30, 30));

        titlePanel.add(title);
        titlePanel.add(image);
        add(titlePanel, BorderLayout.PAGE_START);
        add(cardPanel, BorderLayout.CENTER);
        setCloseOptions();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets closing actions for this frame.
    public void setCloseOptions() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDialogue();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: sets the cardlayout, adds the main panel and subject panel to cardpanel, the parent
    public void setCardLayout() {
        cardPanel.setLayout(cl);
        cardPanel.add(mainPanel, "1");
        cardPanel.add(subjectPanel, "2");
        cl.show(cardPanel, "1");
    }

    // MODIFIES: this
    // EFFECTS: sets the main panel by adding a title label,
    //                                        a list of buttons of each subject in the list of subjects,
    //                                        and text boxes that let you add or delete a subject.
    public void setMainPanel() {
        mainPanel.setLayout(new BorderLayout());
        JLabel subjects = new JLabel("Subjects");
        losHelper = new ListOfSubjectHelperPanel(this, listOfSubjects);
        mainPanel.add(subjects, BorderLayout.PAGE_START);
        mainPanel.add(losHelper, BorderLayout.CENTER);
        mainPanel.add(addAndDeletePanel(), BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: sets a subject's screen by adding a subject tool
    public void setSubjectPanel() {
        subjectPanel.setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: updates the subject panel so that: adds a subjecthelper panel if it doesn't have any panels in it yet
    //                                             or removes old subjecthelper and adds a new one.
    public void updateSubjectPanel() {
        if (!(subjectPanel.getComponentCount() == 0)) {
            subjectPanel.remove(0);
            subjectHelper = new SubjectHelperPanel(this, currentSubject);
            subjectPanel.add(subjectHelper);
            subjectHelper.revalidate();
            subjectHelper.repaint();
        } else {
            subjectHelper = new SubjectHelperPanel(this, currentSubject);
            subjectPanel.add(subjectHelper);
            subjectHelper.revalidate();
            subjectHelper.repaint();
        }
    }

    // EFFECTS: creates a jpanel consisting of the addsubject panel and the deletesubject panel
    public JPanel addAndDeletePanel() {
        JPanel addAndDeletePanel = new JPanel();
        addAndDeletePanel.setSize(FRAME_WIDTH / 2, 100);
        addAndDeletePanel.setSize(600, 100);
        addAndDeletePanel.add(addSubjectPanel());
        addAndDeletePanel.add(deleteSubjectPanel());
        return addAndDeletePanel;
    }

    // EFFECTS: creates a jpanel that lets you add a subject by typing the subject's name in a text box.
    public JPanel addSubjectPanel() {
        JPanel addPanel = new JPanel();
        JLabel addLabel = new JLabel("Add a subject:");
        JTextField addText = new JTextField();
        addText.setColumns(TEXTFIELD_WIDTH / 4);
        addText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSubject = addText.getText();
                if (!(newSubject.equals(""))) {
                    Subject s = new Subject(newSubject);
                    if (listOfSubjects.addSubject(s)) {
                        losHelper.addSubjectButton();
                        addText.setText("");
                    }
                }
            }
        });
        addPanel.add(addLabel);
        addPanel.add(addText);
        return addPanel;
    }

    // EFFECTS: creates a jpanel that lets you delete a subject by typing the subject's name in a text box.
    public JPanel deleteSubjectPanel() {
        JPanel deletePanel = new JPanel();
        JLabel delete = new JLabel("Delete a subject:");
        JTextField deleteText = new JTextField();
        deleteText.setColumns(TEXTFIELD_WIDTH / 4);
        deleteText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = deleteText.getText();
                losHelper.deleteSubjectButton(name);
                deleteText.setText("");
            }
        });
        deletePanel.add(delete);
        deletePanel.add(deleteText);
        return deletePanel;
    }

    // MODIFIES: this
    // EFFECTS: opening optionpane of the program; asks if you would like to load from file.
    // If yes, loads a listofsubjects
    // If no, initializes a new listofsubjects
    // Citation: learned from mkyoung's tutorial on joptionpane
    // https://mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/
    public void loadDialogue() {
        int input = JOptionPane.showConfirmDialog(null,
                "Load from file?", "Load", JOptionPane.YES_NO_OPTION);

        switch (input) {
            case JOptionPane.YES_OPTION:
                try {
                    listOfSubjects = Reader.reader(DATA_FILE);
                } catch (IOException e) {
                    listOfSubjects = new ListOfSubjects();
                }
                break;
            case JOptionPane.NO_OPTION:
                listOfSubjects = new ListOfSubjects();
                break;
            case JOptionPane.CLOSED_OPTION:
                System.exit(0);
        }
    }

    // MODIFIES: data file
    // EFFECTS: shows an option pane with yes, no, and cancel options, asking if you would like to save to file.
    //          If yes is pressed, save current listofsubjects to data file before exiting.
    //          If no, exit and don't save.
    //          Cancel and close will close the option pane.
    public void saveDialogue() {
        int input = JOptionPane.showConfirmDialog(null,
                "Save to file?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);

        switch (input) {
            case JOptionPane.YES_OPTION:
                try {
                    Writer.saveListOfSubject(listOfSubjects, DATA_FILE);
                    System.exit(0);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to save file.", "Save error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    // Getters
    public CardLayout getCardLayout() {
        return cl;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    // Setters
    public void setCurrentSubject(Subject subject) {
        this.currentSubject = subject;
    }

    public static void main(String[] args) {

        // Citation: invoke learned from java swing tutorials (too many to count)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProgressTracker();
            }
        });
    }
}
