package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniversityTest {

    private University universityTest;
    private Faculty faculty1;
    private Faculty faculty2;
    private Faculty faculty3;

    @BeforeEach
    public void setup() {
        universityTest = new University("UBC");
        faculty1 = new Faculty("Science");
        faculty2 = new Faculty("Arts");
        faculty3 = new Faculty("Forestry");

    }

    @Test
    public void testConstructor() {
        assertEquals("UBC", universityTest.getName());
        List<Faculty> faculties = universityTest.getAllFaculties();
        assertEquals(0, faculties.size());
    }

    @Test
    public void testAddFacultyOnce() {
        assertTrue(universityTest.addFaculty(faculty1));
        List<Faculty> faculties = universityTest.getAllFaculties();
        assertEquals(1, faculties.size());
        assertEquals(faculty1, faculties.get(0));
        assertEquals("Science", faculties.get(0).getName());
    }

    @Test
    public void testAddFacultyMultipleTimes() {
        assertTrue(universityTest.addFaculty(faculty1));
        assertTrue(universityTest.addFaculty(faculty2));
        assertTrue(universityTest.addFaculty(faculty3));
        List<Faculty> faculties = universityTest.getAllFaculties();
        assertEquals(3, faculties.size());
        assertEquals(faculty1, faculties.get(0));
        assertEquals(faculty2, faculties.get(1));
        assertEquals(faculty3, faculties.get(2));
    }

    @Test
    public void testAddSameFacultyTwice() {
        assertTrue(universityTest.addFaculty(faculty1));
        assertFalse(universityTest.addFaculty(faculty1));
        List<Faculty> faculties = universityTest.getAllFaculties();
        assertEquals(1, faculties.size());
        assertEquals(faculty1, faculties.get(0));
    }

    @Test
    public void testAddSameFacultyTwiceWithDifferentFaculty() {
        assertTrue(universityTest.addFaculty(faculty1));
        assertTrue(universityTest.addFaculty(faculty2));
        assertFalse(universityTest.addFaculty(faculty1));
        List<Faculty> faculties = universityTest.getAllFaculties();
        assertEquals(2, faculties.size());
        assertEquals(faculty1, faculties.get(0));
        assertEquals(faculty2, faculties.get(1));
    }

    @Test
    public void testRemoveFacultyOnce() {
        assertTrue(universityTest.addFaculty(faculty1));
        List<Faculty> faculties = universityTest.getAllFaculties();
        assertEquals(1, faculties.size());
        assertEquals(faculty1, faculties.get(0));
        assertEquals("Science", faculties.get(0).getName());
        assertTrue(universityTest.removeFaculty(faculty1));
        assertEquals(0, faculties.size());
        assertFalse(faculties.contains(faculty1));
    }

    @Test
    public void testRemoveFacultyNotAdded() {
        assertTrue(universityTest.addFaculty(faculty1));
        assertFalse(universityTest.removeFaculty(faculty2));
        List<Faculty> faculties = universityTest.getAllFaculties();
        assertEquals(1, faculties.size());
        assertTrue(faculties.contains(faculty1));
        assertFalse(faculties.contains(faculty2));
    }
}