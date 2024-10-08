package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a university having a name and a list of faculties
public class University implements Writable {

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
        if (!faculties.contains(faculty)) {
            faculties.add(faculty);
            EventLog.getInstance().logEvent(new Event("Faculty added : " + faculty.getName()));
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: remove a faculty from the list of the university faculties
    // amd return true. if the faculty is not in the list, do nothing
    // and return false
    public boolean removeFaculty(Faculty faculty) {
        if (faculties.contains(faculty)) {
            faculties.remove(faculty);
            EventLog.getInstance().logEvent(new Event("Faculty removed : " + faculty.getName()));
            return true;
        }
        return false;
    }

    // EFFECTS: returns the name of the university
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns a list of all university faculties
    public List<Faculty> getAllFaculties() {
        return faculties;
    }

    //EFFECTS: returns university as a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("faculties", facultiesToJson());
        return json;
    }

    // EFFECTS: returns faculties in this university as a JSON array
    private JSONArray facultiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Faculty f : faculties) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }


}
