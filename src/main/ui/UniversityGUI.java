package ui;

import model.*;
import model.Event;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.List;

// UniversityGUI class represents a graphical user interface for managing faculties and students in a university.
public class UniversityGUI extends JFrame {

    private static final String JSON_STORE = "./data/universityGUI.json";

    private DefaultListModel<String> facultyListModel;
    private JList<String> facultyList;
    private DefaultListModel<String> studentListModel;
    private JList<String> studentList;

    private University university;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // MODIFIES: this
    // EFFECTS: Initializes the UniversityGUI.
    public UniversityGUI() {
        super("University Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        university = new University("Bright Future University");

        // Add a WindowListener to handle window closing events
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClosing();
            }
        });

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        facultyListModel = new DefaultListModel<>();
        facultyList = new JList<>(facultyListModel);
        facultyList.addListSelectionListener(e -> updateStudentList());
        JScrollPane facultyScrollPane = new JScrollPane(facultyList);
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        JScrollPane studentScrollPane = new JScrollPane(studentList);
        createAndAddButtons();
        designLayout();
        setVisible(true);
        pack();
    }

    //EFFECTS : prints all important events as the app closes
    private void handleWindowClosing() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e);
        }
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
            university.removeFaculty(getSelectedFaculty(selectedIndex));
            facultyListModel.remove(selectedIndex);

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
                getSelectedFaculty().addStudent(student);
                studentListModel.addElement(studentName);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the selected student from the selected faculty.
    private void removeStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
//            String removedStudentName = studentListModel.remove(selectedIndex);
            Faculty selectedFaculty = getSelectedFaculty();
            if (selectedFaculty != null) {
//                getSelectedFaculty().getAllStudents().removeIf(student -> student.getName().equals(removedStudentName));
                getSelectedFaculty().removeStudent(getSelectedStudent(selectedIndex));
                studentListModel.remove(selectedIndex);
            }
        }
    }

    // EFFECTS: Saves the current state of the university and associated data to a JSON file.
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(university);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error saving data.");
        }
    }

    // EFFECTS: Loads university state from a JSON file and updates the GUI.
    private void loadData() {
        try {
            university = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Data loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data.");
        }
        updateFacultyList();
        updateStudentList();
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
            List<Student> students = selectedFaculty.getAllStudents();
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

    // EFFECTS: Returns the selected faculty from the list.
    private Faculty getSelectedFaculty(int selectedIndex) {
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

    // EFFECTS: Returns the selected student from the list.
    private Student getSelectedStudent(int selectedIndex) {
        if (selectedIndex != -1) {
            String selectedStudentName = studentListModel.get(selectedIndex);
            for (Student student : getSelectedFaculty().getAllStudents()) {
                if (student.getName().equals(selectedStudentName)) {
                    return student;
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