package persistence;

import model.University;
import model.Student;
import model.Faculty;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads University data from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads University data from file and returns it;
    // throws IOException if an error occurs reading data from file
    public University read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUniversity(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses University data from JSON object and returns it
    private University parseUniversity(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        University university = new University(name);
        addFaculties(university, jsonObject);
//        addStudents(university, jsonObject);
        return university;
    }

    // MODIFIES: university
    // EFFECTS: parses faculties from JSON object and adds them to university
    private void addFaculties(University university, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("faculties");
        for (Object json : jsonArray) {
            JSONObject nextFaculty = (JSONObject) json;
            addFaculty(university, nextFaculty);

        }
    }

    // MODIFIES: university
    // EFFECTS: parses faculty from JSON object and adds it to university
    private void addFaculty(University university, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Faculty faculty = new Faculty(name);
        university.addFaculty(faculty);
        addStudents(faculty, jsonObject);
    }


    // MODIFIES: faculty
    // EFFECTS: parses students from JSON object and adds them to faculty
    private void addStudents(Faculty faculty, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(faculty, nextStudent);
        }
    }

    // MODIFIES: faculty
    // EFFECTS: parses student from JSON object and adds it to faculty
    private void addStudent(Faculty faculty, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double gpa = jsonObject.getDouble("gpa");
        Student student = new Student(name, gpa);
        faculty.addStudent(student);
    }
}
