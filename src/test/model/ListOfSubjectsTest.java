package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfSubjectsTest {

    private ListOfSubjects los1;

    private Subject s1;
    private Subject s2;

    @BeforeEach
    void runBefore() {
        los1 = new ListOfSubjects();
        s1 = new Subject("first");
        s2 = new Subject("second");
    }

    @Test
    void testConstructor() {
        assertTrue(los1.getListOfSubjects().isEmpty());
    }

    @Test
    void testAddSubject() {
        los1.addSubject(s1);
        assertEquals(s1, los1.getListOfSubjects().getFirst());
        los1.addSubject(s2);
        assertEquals(s2, los1.getListOfSubjects().getFirst());
    }

    @Test
    void testGetListOfSubjects() {
        assertTrue(los1.getListOfSubjects().isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertTrue(los1.isEmpty());
    }

    @Test
    void testRemoveSubject() {
        los1.removeSubject("second");
        assertFalse(los1.getListOfSubjects().contains(s2));
        los1.removeSubject("first");
        assertTrue(los1.isEmpty());
    }
}