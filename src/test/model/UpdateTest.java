package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class UpdateTest {

    private Update u1;

    @BeforeEach
    void runBefore() {
        u1 = new Update("I practiced every other day", "I will practice every day");
    }

    @Test
    void testConstructor() {
        assertEquals("I practiced every other day", u1.getReport());
        assertEquals("I will practice every day", u1.getNextGoal());
    }

    @Test
    void testSetReport() {
        u1.setReport("it was bad");
        assertEquals("it was bad", u1.getReport());
    }

    @Test
    void testSetNextGoal() {
        u1.setNextGoal("none");
        assertEquals("none", u1.getNextGoal());
    }

    @Test
    void testGetReport() {
        assertEquals("I practiced every other day", u1.getReport());
    }

    @Test
    void testGetNextGoal() {
        assertEquals("I will practice every day", u1.getNextGoal());
    }
}
