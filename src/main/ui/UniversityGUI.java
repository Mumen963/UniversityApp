package ui;

import model.Faculty;
import model.Student;
import model.University;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import persistence.Writable;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;


// UniversityGUI class represents a graphical user interface for managing faculties and students in a university.
public class UniversityGUI extends JFrame {

    private static final String JSON_STORE = "./data/universityGUI.json";

    private DefaultListModel<String> facultyListModel;
    private JList<String> facultyList;
    private DefaultListModel<String> studentListModel;
    private JList<String> studentList;
    private University university;
    private Map<String, List<Student>> facultyStudentsMap;  //For associating faculty names with lists of students

    // MODIFIES: this
    // EFFECTS: Initializes the UniversityGUI.
    public UniversityGUI() {
        super("University Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        university = new University("Bright Future University");
        facultyStudentsMap = new HashMap<>();
        facultyListModel = new DefaultListModel<>();
        facultyList = new JList<>(facultyListModel);
        facultyList.addListSelectionListener(e -> updateStudentList());
        JScrollPane facultyScrollPane = new JScrollPane(facultyList);
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        JScrollPane studentScrollPane = new JScrollPane(studentList);
        createAndAddButtons();
        designLayout();
//        setSize(550, 300);
        setVisible(true);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds buttons to the GUI.
    private void createAndAddButtons() {
        createButton("Add Faculty", e -> addFaculty());
        createButton("Remove Faculty", e -> removeFaculty());
        createButton("Add Student", e -> addStudent());
        createButton("Remove Student", e -> removeStudent());
        createButton("Save Data", e -> saveData());
        createButton("Load Data", e -> loadData());
    }

    // REQUIRES: label and actionListener are not null.
    // EFFECTS: Creates a JButton with the specified label and ActionListener.
    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: Adds a new faculty to the university.
    private void addFaculty() {
        String facultyName = JOptionPane.showInputDialog("Enter faculty name:");
        if (facultyName != null && !facultyName.isEmpty()) {
            Faculty faculty = new Faculty(facultyName);
            if (university.addFaculty(faculty)) {
                facultyListModel.addElement(facultyName);
                facultyStudentsMap.put(facultyName, new ArrayList<>());
            } else {
                JOptionPane.showMessageDialog(this, "Faculty already exists!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the selected faculty from the university.
    private void removeFaculty() {
        int selectedIndex = facultyList.getSelectedIndex();
        if (selectedIndex != -1) {
            String removedFacultyName = facultyListModel.remove(selectedIndex);
            facultyStudentsMap.remove(removedFacultyName);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a new student to the selected faculty.
    private void addStudent() {
        Faculty selectedFaculty = getSelectedFaculty();
        if (selectedFaculty != null) {
            String studentName = JOptionPane.showInputDialog("Enter student name:");
            if (studentName != null && !studentName.isEmpty()) {
                double studentGpa = Double.parseDouble(JOptionPane.showInputDialog("Enter student GPA:"));
                Student student = new Student(studentName, studentGpa);
                List<Student> students = facultyStudentsMap.computeIfAbsent(
                        selectedFaculty.getName(), k -> new ArrayList<>());
                students.add(student);
                studentListModel.addElement(studentName);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the selected student from the selected faculty.
    private void removeStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            String removedStudentName = studentListModel.remove(selectedIndex);
            Faculty selectedFaculty = getSelectedFaculty();
            if (selectedFaculty != null) {
                List<Student> students = facultyStudentsMap.get(selectedFaculty.getName());
                students.removeIf(student -> student.getName().equals(removedStudentName));
            }
        }
    }

    // EFFECTS: Saves the current state of the university and associated data to a JSON file.
    @SuppressWarnings("methodlength")
    private void saveData() {
        try {
            JSONObject json = new JSONObject();
            json.put("name", university.getName());

            JSONArray facultiesArray = new JSONArray();
            for (Faculty faculty : university.getAllFaculties()) {
                JSONObject facultyJson = new JSONObject();
                facultyJson.put("name", faculty.getName());

                JSONArray studentsArray = new JSONArray();
                List<Student> students = facultyStudentsMap.get(faculty.getName());
                for (Student student : students) {
                    JSONObject studentJson = new JSONObject();
                    studentJson.put("name", student.getName());
                    studentJson.put("gpa", student.getGpa());
                    studentsArray.put(studentJson);
                }

                facultyJson.put("students", studentsArray);
                facultiesArray.put(facultyJson);
            }

            json.put("faculties", facultiesArray);

            FileWriter writer = new FileWriter(JSON_STORE);
            writer.write(json.toString(4));
            writer.close();

            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data.");
        }
    }

    // EFFECTS: Loads university state from a JSON file and updates the GUI.
    @SuppressWarnings("methodlength")
    private void loadData() {
        try {
            FileReader reader = new FileReader(JSON_STORE);
            JSONObject json = new JSONObject(new JSONTokener(reader));
            university = new University(json.getString("name"));
            JSONArray facultiesArray = json.getJSONArray("faculties");

            for (Object facultyObj : facultiesArray) {
                JSONObject facultyJson = (JSONObject) facultyObj;
                String facultyName = facultyJson.getString("name");
                Faculty faculty = new Faculty(facultyName);
                university.addFaculty(faculty);

                JSONArray studentsArray = facultyJson.getJSONArray("students");
                List<Student> students = new ArrayList<>();

                for (Object studentObj : studentsArray) {
                    JSONObject studentJson = (JSONObject) studentObj;
                    String studentName = studentJson.getString("name");
                    double studentGpa = studentJson.getDouble("gpa");
                    Student student = new Student(studentName, studentGpa);
                    students.add(student);
                }

                facultyStudentsMap.put(facultyName, students);
            }

            updateFacultyList();
            updateStudentList();

            reader.close();
            JOptionPane.showMessageDialog(this, "Data loaded successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "No saved data found.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data.");
        }
    }

    // MODIFIES: facultyListModel
    // EFFECTS: Updates the faculty list with the current faculties.
    private void updateFacultyList() {
        facultyListModel.clear();
        for (Faculty faculty : university.getAllFaculties()) {
            facultyListModel.addElement(faculty.getName());
        }
    }

    // MODIFIES: studentListModel
    // EFFECTS: Updates the student list based on the selected faculty.
    private void updateStudentList() {
        studentListModel.clear();
        Faculty selectedFaculty = getSelectedFaculty();
        if (selectedFaculty != null) {
            List<Student> students = facultyStudentsMap.get(selectedFaculty.getName());
            for (Student student : students) {
                studentListModel.addElement(student.getName());
            }
        }
    }

    // EFFECTS: Returns the selected faculty from the list.
    private Faculty getSelectedFaculty() {
        int selectedIndex = facultyList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedFacultyName = facultyListModel.get(selectedIndex);
            for (Faculty faculty : university.getAllFaculties()) {
                if (faculty.getName().equals(selectedFacultyName)) {
                    return faculty;
                }
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Sets up the layout of the GUI using Swing components.
    private void designLayout() {
        setLayout(new BorderLayout());
        JPanel facultyPanel = new JPanel(new BorderLayout());
        facultyPanel.add(new JScrollPane(facultyList), BorderLayout.CENTER);
        JPanel facultyButtonPanel = new JPanel();
        facultyButtonPanel.add(createButton("Add Faculty", e -> addFaculty()));
        facultyButtonPanel.add(createButton("Remove Faculty", e -> removeFaculty()));
        facultyPanel.add(facultyButtonPanel, BorderLayout.SOUTH);
        add(facultyPanel, BorderLayout.WEST);

        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        JPanel studentButtonPanel = new JPanel();
        studentButtonPanel.add(createButton("Add Student", e -> addStudent()));
        studentButtonPanel.add(createButton("Remove Student", e -> removeStudent()));
        studentPanel.add(studentButtonPanel, BorderLayout.SOUTH);
        add(studentPanel, BorderLayout.EAST);

        JPanel dataButtonPanel = new JPanel();
        dataButtonPanel.add(createButton("Save Data", e -> saveData()));
        dataButtonPanel.add(createButton("Load Data", e -> loadData()));
        add(dataButtonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: run the UniversityGUI application after displaying the splash screen.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SplashScreen(() -> SwingUtilities.invokeLater(UniversityGUI::new)));
    }
}