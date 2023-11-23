package ui;

import model.Faculty;
import model.Student;
import model.University;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UniversityGUI extends JFrame {

    private DefaultListModel<String> facultyListModel;
    private JList<String> facultyList;
    private DefaultListModel<String> studentListModel;
    private JList<String> studentList;
    private University university;
    private Map<String, List<Student>> facultyStudentsMap;


    public UniversityGUI() {

        super("University GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        university = new University("My University");

        facultyStudentsMap = new HashMap<>();

        facultyListModel = new DefaultListModel<>();
        facultyList = new JList<>(facultyListModel);
        facultyList.addListSelectionListener(e -> updateStudentList());

        JScrollPane facultyScrollPane = new JScrollPane(facultyList);

        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);


        JScrollPane studentScrollPane = new JScrollPane(studentList);

        JButton addFacultyButton = new JButton("Add Faculty");
        addFacultyButton.addActionListener(e -> {
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
        });

        JButton removeFacultyButton = new JButton("Remove Faculty");
        removeFacultyButton.addActionListener(e -> {
            int selectedIndex = facultyList.getSelectedIndex();
            if (selectedIndex != -1) {
                String removedFacultyName = facultyListModel.remove(selectedIndex);
                facultyStudentsMap.remove(removedFacultyName);
            }
        });

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> {
            Faculty selectedFaculty = getSelectedFaculty();
            if (selectedFaculty != null) {
                String studentName = JOptionPane.showInputDialog("Enter student name:");
                if (studentName != null && !studentName.isEmpty()) {
                    double studentGpa = Double.parseDouble(JOptionPane.showInputDialog("Enter student GPA:"));
                    Student student = new Student(studentName, studentGpa);
                    List<Student> students = facultyStudentsMap.get(selectedFaculty.getName());
                    if (students.add(student)) {
                        studentListModel.addElement(studentName);
                    } else {
                        JOptionPane.showMessageDialog(this, "Student already exists in the faculty!");
                    }
                }
            }
        });

        JButton removeStudentButton = new JButton("Remove Student");
        removeStudentButton.addActionListener(e -> {
            int selectedIndex = studentList.getSelectedIndex();
            if (selectedIndex != -1) {
                String removedStudentName = studentListModel.remove(selectedIndex);
                Faculty selectedFaculty = getSelectedFaculty();
                if (selectedFaculty != null) {
                    List<Student> students = facultyStudentsMap.get(selectedFaculty.getName());
                    students.removeIf(student -> student.getName().equals(removedStudentName));
                }
            }
        });

        setLayout(new BorderLayout());

        JPanel facultyPanel = new JPanel(new BorderLayout());
        facultyPanel.add(facultyScrollPane, BorderLayout.CENTER);
        JPanel facultyButtonPanel = new JPanel();
        facultyButtonPanel.add(addFacultyButton);
        facultyButtonPanel.add(removeFacultyButton);
        facultyPanel.add(facultyButtonPanel, BorderLayout.SOUTH);
        add(facultyPanel, BorderLayout.WEST);

        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.add(studentScrollPane, BorderLayout.CENTER);
        JPanel studentButtonPanel = new JPanel();
        studentButtonPanel.add(addStudentButton);
        studentButtonPanel.add(removeStudentButton);
        studentPanel.add(studentButtonPanel, BorderLayout.SOUTH);
        add(studentPanel, BorderLayout.EAST);

        setSize(800, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UniversityGUI());
    }


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

    private Faculty getSelectedFaculty() {
        int selectedIndex = facultyList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedFacultyName = facultyListModel.get(selectedIndex);
            return new Faculty(selectedFacultyName);
        }
        return null;
    }
}