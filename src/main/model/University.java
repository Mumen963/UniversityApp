package model;

import java.util.ArrayList;
import java.util.List;

// represents a university having a name and a list of faculties
public class University {

    private String name;                // name of the university
    private List<Faculty> faculties;    // the university faculties

    // REQUIRES: universityName has a non-zero length
    // EFFECTS: creates an empty list of faculties
    // and sets the name of the university to universityName
    public University(String universityName) {
        this.name = universityName;
        faculties = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a faculty to the list of university faculties
    // and return true. if the faculty already added, do nothing
    // and return false. items are maintained in the order
    // in which they were added
    public boolean addFaculty(Faculty faculty) {
        for (Faculty f : faculties) {
            if (faculty.getName().equals(f.getName())) {
                return false;
            }
        }
        faculties.add(faculty);
        return true;
    }

    // EFFECTS: returns the name of the university
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns a list of all university faculties
    public List<Faculty> getAllFaculties() {
        return faculties;
    }


}
