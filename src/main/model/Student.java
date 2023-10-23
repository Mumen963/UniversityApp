package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// represent a student having a name, id, and a GPA
public class Student {

    private String name;                        //name of the student
    private int id;                             //student id
    private static int nextStudentId = 1000;    //tracks id of next student added
    private double gpa;                         //student gpa

    //REQUIRES: student name has a non zero length
    //EFFECTS: set student name and gpa, and add a
    // unique id starting at 1000 for the student
    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
        this.id = this.nextStudentId++;
    }


    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public double getGpa() {
        return this.gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("gpa", gpa);
        return json;
    }


}
