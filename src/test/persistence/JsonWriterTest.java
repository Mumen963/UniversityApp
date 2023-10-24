package persistence;

import model.Faculty;
import model.Student;
import model.University;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            University university = new University("My university");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUniversity() {
        try {
            University university = new University("My university");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUniversity.json");
            writer.open();
            writer.write(university);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUniversity.json");
            university = reader.read();
            assertEquals("My university", university.getName());
            assertEquals(0, university.getAllFaculties().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            University university = new University("My university");
            Faculty chem = new Faculty("Chem");
            chem.addStudent(new Student("Sali", 2.2));
            chem.addStudent(new Student("Mira", 1.9));
            Faculty math = new Faculty("Math");
            math.addStudent(new Student("Josef", 3.5));
            math.addStudent(new Student("Ebro", 3.4));
            university.addFaculty(chem);
            university.addFaculty(math);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUniversity.json");
            writer.open();
            writer.write(university);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUniversity.json");
            university = reader.read();
            assertEquals("My university", university.getName());
            List<Faculty> faculties = university.getAllFaculties();
            assertEquals(2, faculties.size());
            assertEquals("Chem", faculties.get(0).getName());
            assertEquals("Math", faculties.get(1).getName());
            List<Student> studentsC = faculties.get(0).getAllStudents();
            List<Student> studentsM = faculties.get(1).getAllStudents();
            assertEquals("Sali", studentsC.get(0).getName());
            assertEquals("Mira", studentsC.get(1).getName());
            assertEquals(2.2, studentsC.get(0).getGpa());
            assertEquals(1.9, studentsC.get(1).getGpa());
            assertEquals("Josef", studentsM.get(0).getName());
            assertEquals("Ebro", studentsM.get(1).getName());
            assertEquals(3.5, studentsM.get(0).getGpa());
            assertEquals(3.4, studentsM.get(1).getGpa());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
