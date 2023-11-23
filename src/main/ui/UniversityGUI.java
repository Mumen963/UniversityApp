package ui;

import model.Faculty;
import model.University;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UniversityGUI extends JFrame {

    private DefaultListModel<String> facultyListModel;
    private JList<String> facultyList;
    private University university;

    public UniversityGUI() {

        super("University GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        university = new University("My University");
        facultyListModel = new DefaultListModel<>();
        facultyList = new JList<>(facultyListModel);
        JScrollPane facultyScrollPane = new JScrollPane(facultyList);
        JButton addFacultyButton = new JButton("Add Faculty");
        addFacultyButton.addActionListener(e -> {
            String facultyName = JOptionPane.showInputDialog("Enter faculty name:");
            if (facultyName != null && !facultyName.isEmpty()) {
                Faculty faculty = new Faculty(facultyName);
                if (university.addFaculty(faculty)) {
                    facultyListModel.addElement(facultyName);
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
                List<Faculty> allFaculties = university.getAllFaculties();
                for (Faculty faculty : allFaculties) {
                    if (faculty.getName().equals(removedFacultyName)) {
                        allFaculties.remove(faculty);
                        break;
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        add(facultyScrollPane, BorderLayout.CENTER);

        JPanel facultyButtonPanel = new JPanel();
        facultyButtonPanel.add(addFacultyButton);
        facultyButtonPanel.add(removeFacultyButton);
        add(facultyButtonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UniversityGUI());
    }
}