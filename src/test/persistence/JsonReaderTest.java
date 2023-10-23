package persistence;

import model.Faculty;
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
    void testReaderEmptyWorkRoom() {
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
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUniversity.json");
        try {
            University university = reader.read();
            assertEquals("My university", university.getName());
            List<Faculty> faculties = university.getAllFaculties();
            assertEquals(2, faculties.size());
            assertEquals("Forestry", university.getAllFaculties().get(0).getName());
            assertEquals("Bio", university.getAllFaculties().get(1).getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
