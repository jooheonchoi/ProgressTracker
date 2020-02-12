package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {

    private Subject s0;
    private Subject s1;

    private Update u1;
    private Update u2;

    @BeforeEach
    void runBefore() {
        s0 = new Subject("Piano");
    }

    @Test
    void testConstructor() {
        assertEquals("Piano", s0.getName());
        assertTrue(s0.getUpdateLog().isEmpty());
        assertEquals("", s0.getBigGoal());
    }

    @Test
    void testSetName() {
        s0.setName("SetName");
        assertEquals("SetName", s0.getName());
    }

    @Test
    void testSetBigGoal() {
        s0.setBigGoal("become a master");
        assertEquals("become a master", s0.getBigGoal());
    }

    @Test
    void testGetName() {
        assertEquals("Piano", s0.getName());
    }

    @Test
    void testGetBigGoal() {
        assertEquals("", s0.getBigGoal());
    }

    @Test
    void testGetUpdateLog() {
        assertTrue(s0.getUpdateLog().isEmpty());
    }

    @Test
    void testAddUpdate() {
        u1 = new Update("1", "1");
        u2 = new Update("2", "2");
        s0.addUpdate(u1);
        assertEquals(u1, s0.getUpdateLog().getFirst());
        s0.addUpdate(u2);
        assertEquals(u2, s0.getUpdateLog().getFirst());
    }


}
