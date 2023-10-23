package persistence;

import com.sun.security.auth.UnixNumericGroupPrincipal;
import model.Faculty;
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
            university.addFaculty(new Faculty("History"));
            university.addFaculty(new Faculty("Physics"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUniversity.json");
            writer.open();
            writer.write(university);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUniversity.json");
            university = reader.read();
            assertEquals("My university", university.getName());
            List<Faculty> faculties = university.getAllFaculties();
            assertEquals(2, faculties.size());
            assertEquals("History", university.getAllFaculties().get(0).getName());
            assertEquals("Physics", university.getAllFaculties().get(1).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
