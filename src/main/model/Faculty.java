package model;

import java.util.ArrayList;
import java.util.List;

// represent a faculty having a name and a list of students
public class Faculty {

    private String name;            // name of the faculty
    private List<Student> students; // the faculty students

    // REQUIRES: facultyName has a non-zero length
    // EFFECTS: creates an empty list of students
    // and sets the name of the faculty to facultyName
    public Faculty(String facultyName) {
        this.name = facultyName;
        students = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a student to the list of the faculty students
    // and return true, if the student is already added, do nothing
    // and return false. items are maintained in the order in
    // which they were added
    public boolean addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: remove a student from the list of the faculty students
    // amd return true. if the student is not in the list, do nothing
    // and return false
    public boolean removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            return true;
        }
        return false;
    }

    // EFFECTS: returns the name of the faculty
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns a list of all faculty students
    public List<Student> getAllStudents() {
        return students;
    }


}
