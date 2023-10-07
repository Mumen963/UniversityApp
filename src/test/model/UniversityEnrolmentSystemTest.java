package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversityEnrolmentSystemTest {

    private UniversityEnrolmentSystem universityTest;

    @BeforeEach
    public void setup() {
        universityTest = new UniversityEnrolmentSystem("UBC");
    }

    @Test
    public void testConstructor() {
        assertEquals("UBC", universityTest.getName());
        assertNull(universityTest.getAllFaculties());
    }
}