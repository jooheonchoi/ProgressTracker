package ui;

import model.ListOfSubjects;
import model.Subject;
import persistence.Reader;
import persistence.Saver;
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
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 700;
    private static final int TEXTFIELD_WIDTH = 40;

    private JPanel mainPanel;
    private JPanel subjectPanel;
    private JPanel cardPanel;
    private CardLayout cl;
    private Subject currentSubject;

    private ListOfSubjectHelperPanel losTool;
    private SubjectHelperPanel subjectTool;

    private static final String DATA_FILE = "data/ProgressTracker.json";

    // Constructs the progressTracker app.
    public ProgressTracker() {
        super("ProgressTracker");
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        mainPanel = new JPanel();
        subjectPanel = new JPanel();
        cardPanel = new JPanel();
        currentSubject = null;

        // Citation: inspiration from
        // https://www.youtube.com/watch?v=sAReaaTxNGU&list=PLnWWOcNcMFpV5pzbJDf4r1cdKXU2zp52r&index=4&t=475s
        cl = new CardLayout();

        loadDialogue();

        setMainPanel();
        setSubjectPanel();
        setCardLayout();

        JLabel title = new JLabel("ProgressTracker");
        getContentPane().add(title, BorderLayout.PAGE_START);
        add(cardPanel);
        setCloseOptions();

        setVisible(true);

    }

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
        losTool = new ListOfSubjectHelperPanel(this, listOfSubjects);
        mainPanel.add(subjects, BorderLayout.PAGE_START);
        mainPanel.add(losTool, BorderLayout.CENTER);
        mainPanel.add(addAndDeletePanel(), BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: sets a subject's screen by adding a subject tool
    public void setSubjectPanel() {
        subjectPanel.setLayout(new BorderLayout());
//        subjectTool = new SubjectTool(this, currentSubject);
//        subjectPanel.add(subjectTool);
    }

    public void updateSubjectPanel() {
        if (!(subjectPanel.getComponentCount() == 0)) {
            subjectPanel.remove(0);
            subjectTool = new SubjectHelperPanel(this, currentSubject);
            subjectPanel.add(subjectTool);
            subjectTool.revalidate();
            subjectTool.repaint();
        } else {
            subjectTool = new SubjectHelperPanel(this, currentSubject);
            subjectPanel.add(subjectTool);
            subjectTool.revalidate();
            subjectTool.repaint();
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
        addText.setColumns(TEXTFIELD_WIDTH);
        addText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSubject = addText.getText();
                if (!(newSubject.equals(""))) {
                    Subject s = new Subject(newSubject);
                    if (listOfSubjects.addSubject(s)) {
                        losTool.addSubjectButton();
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
        deleteText.setColumns(TEXTFIELD_WIDTH);
        deleteText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = deleteText.getText();
                losTool.deleteSubjectButton(name);
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
//        if (input == 0) {           // Yes option
//            try {
//                listOfSubjects = Reader.reader(DATA_FILE);
//            } catch (IOException e) {
//                listOfSubjects = new ListOfSubjects();
//            }
//        } else if (input == 1) {
//            listOfSubjects = new ListOfSubjects();
//        }


    public void saveDialogue() {
        int input = JOptionPane.showConfirmDialog(null,
                "Save to file?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);

        switch (input) {
            case JOptionPane.YES_OPTION:
                try {
                    Saver.saveListOfSubject(listOfSubjects, DATA_FILE);
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
//        if (input == 0) {                    // Yes option
//            try {
//                Saver.saveListOfSubject(listOfSubjects, DATA_FILE);
//            } catch (IOException e) {
//                JOptionPane.showMessageDialog(null,
//                        "Unable to save file.", "Save error", JOptionPane.ERROR_MESSAGE);
//            }
//        } else if (input == 1) {             // No option
//            System.exit(0);
//        } else {
//            return;
//        }
    }

    // Getters
    public CardLayout getCardLayout() {
        return cl;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getSubjectPanel() {
        return subjectPanel;
    }

    public Subject getCurrentSubject() {
        return currentSubject;
    }

    // Setters
    public void setCurrentSubject(Subject subject) {
        this.currentSubject = subject;
    }
}

// Phase 1 code

//    private Scanner input;
//    private ListOfSubjects listOfSubjects;
//    private static final String DATA_FILE = "data/ProgressTracker.json";

//    public ProgressTracker() {
//        runApp();


//    private void runApp() {         // based on TellerApp ui class
//        boolean keepGoing = true;
//        String command = null;
//        input = new Scanner(System.in);
//
//        System.out.println("Track your progress with the ProgressTracker!");
//
//        loadOption();
//
//        while (keepGoing) {
//
//            displayMenu(); // from TellerApp
//            command = input.nextLine();
//            if (command.equals("q")) {
//                saveOption();
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nSee you next time!");
//    }
//
//    public void saveOption() {
//        String command = "";             // from TellerApp
//        while (!((command.equals("y") || (command.equals("n"))))) {
//            System.out.println("Save to file?");
//            System.out.println("y -> yes");
//            System.out.println("n -> no");
//            command = input.nextLine();
//            if (command.equals("y")) {
//                try {
//                    Saver.saveListOfSubject(listOfSubjects, DATA_FILE);
//                    return;
//                } catch (IOException e) {
//                    System.out.println("File doesn't exist");
//                    return;
//                }
//            }
//            if (command.equals("n")) {
//                return;
//
//
//            }
//        }
//    }
//
//    public void loadOption() {
//        String command = "";             // from TellerApp
//        while (!((command.equals("y") || (command.equals("n"))))) {
//            System.out.println("Load from file?");
//            System.out.println("y -> yes");
//            System.out.println("n -> no");
//            command = input.nextLine();
//            if (command.equals("y")) {
//                try {
//                    listOfSubjects = Reader.reader(DATA_FILE);
//                    return;
//                } catch (IOException e) {
//                    System.out.println("Nothing in file");
//                    init();
//                    return;
//                }
//            }
//            if (command.equals("n")) {
//                init();
//                return;
//            }
//        }
//    }
//
//
//    public void init() {
//        System.out.println("Initializing..");
//        listOfSubjects = new ListOfSubjects();
//    }
//
//    private void processCommand(String command) {
//        if (command.equals("a")) {
//            addNewSubject();
//            return;
//        } else if (command.equals("r")) {
//            removeSubject();
//            return;
//        } else {
//            for (Subject next : listOfSubjects.getListOfSubjects()) {
//                if (command.equals(next.getName())) {
//                    viewSubject(next);
//                    return;
//                }
//            }
//        }
//        System.out.println("Enter valid command!");
//    }
//
//    private void removeSubject() {
//        if (listOfSubjects.isEmpty()) {
//            System.out.println("No subjects yet!");
//        } else {
//            String command = "";
//            while (!((command.equals("q")))) {
//                System.out.println("Type name of subject to be removed:");
//                System.out.println("q - > back");
//                command = input.nextLine();
//
//                for (Subject next : listOfSubjects.getListOfSubjects()) {
//                    if (command.equals(next.getName())) {
//                        listOfSubjects.getListOfSubjects().remove(next);
//                        System.out.println("Removed " + next.getName() + "!");
//                        return;
//                    }
//                }
//                System.out.println(command + " not found!");
//            }
//        }
//    }
//
//    private void viewSubject(Subject subject) {
//        Boolean keepInView = true;
//        while (keepInView) {
//            displaySubject(subject);
//            String command = "";             // from TellerApp
//            while (!((command.equals("s") || (command.equals("u")) || (command.equals("q"))))) {
//                System.out.println("s -> Set a goal");
//                System.out.println("u -> Post an update");
//                System.out.println("q -> Back");
//                command = input.nextLine();
//            }
//
//            if (command.equals("s")) {
//                goalSetter(subject);
//            } else if (command.equals("u")) {
//                postUpdate(subject);
//            } else {
//                keepInView = false;
//            }
//        }
//    }
//
//    private void displaySubject(Subject subject) {
//        System.out.println(subject.getName());
//        System.out.println("Current Goal: " + subject.getBigGoal() + "\n");
//        for (Update update : subject.getUpdateLog()) {
//            System.out.println(update.getReport() + "\t||\t" + update.getNextGoal());
//        }
//    }
//
//    private void postUpdate(Subject subject) {
//        System.out.println("Description of update:");
//        String description = input.nextLine();
//
//        System.out.println("Goal for next update:");
//        String nextGoal = input.nextLine();
//
//        Update newUpdate = new Update(description, nextGoal);
//        subject.addUpdate(newUpdate);
//        System.out.println("Update added!");
//    }
//
//
//    private void goalSetter(Subject subject) {
//        System.out.println("Set a goal:");
//        String goal = input.nextLine();
//        subject.setBigGoal(goal);
//        System.out.println("Goal set!");
//    }
//
//    private void addNewSubject() {
//        System.out.println("Choose name of subject:");
//        String name = input.nextLine();
//        Subject newSubject = new Subject(name);
//        listOfSubjects.addSubject(newSubject);
//        System.out.println(name + " added!");
//        viewSubject(newSubject);
//    }
//
//    private void displayMenu() {
//        if (listOfSubjects == null) {
//            init();
//        } else if (listOfSubjects.isEmpty()) {
//            System.out.println("Nothing added yet");
//        } else {
//            for (Subject next : listOfSubjects.getListOfSubjects()) {
//                System.out.println(next.getName());
//            }
//        }
//
//        System.out.println("\nType subject name -> View subject");
//        System.out.println("a -> Add subject");
//        System.out.println("r -> Remove subject");
//        System.out.println("q -> Exit");
//    }
