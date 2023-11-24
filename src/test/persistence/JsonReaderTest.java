package persistence;

import model.Faculty;
import model.Student;
import model.University;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            University university = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUniversity() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUniversity.json");
        try {
            University university = reader.read();
            assertEquals("My university", university.getName());
            assertEquals(0, university.getAllFaculties().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUniversity() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUniversity.json");
        try {
            University university = reader.read();
            assertEquals("My university", university.getName());
            List<Faculty> faculties = university.getAllFaculties();
            assertEquals(2, faculties.size());
            assertEquals("Forestry", faculties.get(0).getName());
            assertEquals("Bio", faculties.get(1).getName());
            List<Student> studentsF = faculties.get(0).getAllStudents();
            List<Student> studentsB = faculties.get(1).getAllStudents();
            assertEquals("Joe", studentsF.get(0).getName());
            assertEquals("Mo", studentsF.get(1).getName());
            assertEquals(3.5, studentsF.get(0).getGpa());
            assertEquals(2.5, studentsF.get(1).getGpa());
            assertEquals("Eric", studentsB.get(0).getName());
            assertEquals("Harrison", studentsB.get(1).getName());
            assertEquals(2.9, studentsB.get(0).getGpa());
            assertEquals(3.1, studentsB.get(1).getGpa());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
