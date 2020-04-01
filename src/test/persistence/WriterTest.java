package persistence;

import model.ListOfSubjects;
import model.Subject;
import model.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
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
            Writer.saveListOfSubject(listOfSubjects, TEST_FILE);

            listOfSubjects = Reader.reader(TEST_FILE);
            subject = listOfSubjects.getListOfSubjects().getFirst();
            assertEquals("math", subject.getName());
            assertEquals("good", subject.getUpdateLog().getFirst().getReport());
            assertEquals("better", subject.getUpdateLog().getFirst().getNextGoal());
            assertEquals("master", subject.getBigGoal());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }


    }
}
