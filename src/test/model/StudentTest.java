package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    public void setup() {
        student1 = new Student("Mary", 3.5);
        student2 = new Student("Alex", 3.2);
        student3 = new Student("Dave", 2.8);
    }

    @Test
    public void testConstructorMultipleInstances() {
        assertEquals("Mary", student1.getName());
        assertEquals("Alex", student2.getName());
        assertEquals("Dave", student3.getName());
        assertEquals(3.5, student1.getGpa());
        assertEquals(3.2, student2.getGpa());
        assertEquals(2.8, student3.getGpa());
        assertEquals(1000, student1.getId());
        assertEquals(1001, student2.getId());
        assertEquals(1002, student3.getId());
    }

    @Test
    public void testSetGpa() {
        student1.setGpa(1.3);
        assertEquals(1.3,student1.getGpa());
    }
}
