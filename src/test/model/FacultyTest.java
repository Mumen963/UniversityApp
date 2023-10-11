package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FacultyTest {

    private Faculty facultyTest;
    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    public void setup() {
        facultyTest = new Faculty("Science");
        student1 = new Student("Mary", 3.5);
        student2 = new Student("Alex", 3.2);
        student3 = new Student("Dave", 2.8);
    }

    @Test
    public void testConstructor() {
        assertEquals("Science", facultyTest.getName());
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(0, students.size());
    }

    @Test
    public void testAddStudentOnce() {
        facultyTest.addStudent(student1);
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(1, students.size());
        assertEquals(student1, students.get(0));
    }

    @Test
    public void testAddStudentMultipleTimes() {
        facultyTest.addStudent(student1);
        facultyTest.addStudent(student2);
        facultyTest.addStudent(student3);
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(3, students.size());
        assertEquals(student1, students.get(0));
        assertEquals(student2, students.get(1));
        assertEquals(student3, students.get(2));
    }

    @Test
    public void testAddSameStudentTwice() {
        assertTrue(facultyTest.addStudent(student1));
        assertFalse(facultyTest.addStudent(student1));
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(1, students.size());
        assertEquals(student1, students.get(0));
    }

    @Test
    public void testAddSameStudentTwiceWithDifferentStudent() {
        assertTrue(facultyTest.addStudent(student1));
        assertTrue(facultyTest.addStudent(student3));
        assertFalse(facultyTest.addStudent(student1));
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(2, students.size());
        assertEquals(student1, students.get(0));
        assertEquals(student3, students.get(1));
    }

    @Test
    public void testRemoveStudentWhoIsAdded() {
        facultyTest.addStudent(student1);
        assertTrue(facultyTest.removeStudent(student1));
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(0, students.size());
    }

    @Test
    public void testRemoveStudentWhoIsNotAdded() {
        facultyTest.addStudent(student1);
        assertFalse(facultyTest.removeStudent(student2));
    }

    @Test
    public void testRemoveStudentMultipleTimes() {
        facultyTest.addStudent(student1);
        facultyTest.addStudent(student2);
        facultyTest.addStudent(student3);
        assertTrue(facultyTest.removeStudent(student1));
        assertTrue(facultyTest.removeStudent(student2));
        assertTrue(facultyTest.removeStudent(student3));
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(0, students.size());
    }

    @Test
    public void testRemoveStudentMiddleOfTheList() {
        facultyTest.addStudent(student1);
        facultyTest.addStudent(student2);
        facultyTest.addStudent(student3);
        assertTrue(facultyTest.removeStudent(student2));
        List<Student> students = facultyTest.getAllStudents();
        assertEquals(2, students.size());
        assertEquals(student1, students.get(0));
        assertEquals(student3, students.get(1));
    }


}
