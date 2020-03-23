package persistence;

import model.ListOfSubjects;
import model.Subject;
import model.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SaverTest {
    private static final String TEST_FILE = "data/test2.json";
    private ListOfSubjects listOfSubjects;
    private Subject subject;
    private Update update;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        listOfSubjects = new ListOfSubjects();
        subject = new Subject("math");
        subject.setBigGoal("master");
        update = new Update("good", "better");
        subject.getUpdateLog().add(update);
        listOfSubjects.addSubject(subject);


    }

    @Test
    void testSaverExceptionNotThrown() {
        try {
            Saver.saveListOfSubject(listOfSubjects, TEST_FILE);

            Reader.reader(TEST_FILE);
            assertEquals("math", subject.getName());
            assertEquals("good", subject.getUpdateLog().getFirst().getReport());
            assertEquals("better", subject.getUpdateLog().getFirst().getNextGoal());
            assertEquals("master", subject.getBigGoal());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }


    }

    @Test
    void testSaverExceptionThrown() {
        try {
            Saver.saveListOfSubject(listOfSubjects, "nonexistentfile.json");
        } catch (IOException e) {
            //Expected
        }
    }

}