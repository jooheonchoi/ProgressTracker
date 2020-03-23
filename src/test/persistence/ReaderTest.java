package persistence;

import model.ListOfSubjects;
import model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    private static final String TEST_FILE = "data/test1.json";


    @Test
    void testReaderExceptionNotThrown() {
        try {
            ListOfSubjects listOfSubjects = Reader.reader(TEST_FILE);
            Subject subject = listOfSubjects.getListOfSubjects().getFirst();
            assertEquals("math", subject.getName());
            assertEquals("good", subject.getUpdateLog().getFirst().getReport());
            assertEquals("better", subject.getUpdateLog().getFirst().getNextGoal());
            assertEquals("master", subject.getBigGoal());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


    @Test
    void testIOException() {
        try {
            Reader.reader("nonexistentfile.json");
            fail("Exception should have been thrown");
        } catch (IOException e) {
            // expected
        }
    }
}
