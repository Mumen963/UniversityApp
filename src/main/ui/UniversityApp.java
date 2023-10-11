package ui;

import model.Faculty;
import model.Student;
import model.University;

import java.util.List;
import java.util.Scanner;

//University Enrolment System application
public class UniversityApp {

    private University university;
    private Scanner input;

    // runs the university app
    public UniversityApp() {
        runUniversity();
    }

    //MODIFIES : this
    //EFFECTS : process user inout
    private void runUniversity() {
        System.out.println("\nWelcome to UBC!");
        boolean keepGoing = true;
        int command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 9) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }

    //MODIFIES : this
    //EFFECTS : initializes university having two faculties with students
    private void init() {
        input = new Scanner(System.in);
        university = new University("Bright Future University");
        Faculty science = new Faculty("Science");
        Faculty arts = new Faculty("Arts");
        university.addFaculty(science);
        university.addFaculty(arts);
        Student mari = new Student("Mari", 3.4);
        Student sara = new Student("Sara", 3.8);
        Student dave = new Student("Dave", 2.8);
        science.addStudent(mari);
        science.addStudent(sara);
        arts.addStudent(dave);
    }

    //EFFECTS : displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("1 -> Display faculties");
        System.out.println("2 -> Add a new faculty");
        System.out.println("3 -> Add a new student to faculty");
        System.out.println("4 -> Remove a student from a faculty");
        System.out.println("5 -> View all students in a faculty");
        System.out.println("6 -> get number of students in a faculty");
        System.out.println("7 -> add a new GPA for a student in a faculty");
        System.out.println("8 -> get a student info");
        System.out.println("9 -> quit");
    }

    //REQUIRES : numerical input
    //EFFECTS: process user command
    private void processCommand(int command) {
        if (command == 1) {
            displayFaculties();
        } else if (command == 2) {
            addNewFaculty();
        } else if (command == 3) {
            addNewStudent();
        } else if (command == 4) {
            removeStudent();
        } else if (command == 5) {
            displayStudents();
        } else if (command == 6) {
            displayNumStudents();
        } else if (command == 7) {
            addGPA();
        } else if (command == 8) {
            displayStudentInfo();
        } else {
            System.out.println("Selection is not valid...");
        }
    }

    //EFFECTS: displays all university faculties
    private void displayFaculties() {
        for (Faculty f : university.getAllFaculties()) {
            System.out.println("\t-" + f.getName());
        }
    }

    //REQUIRES : faculty name has non zero length
    //MODIFIES: this
    //EFFECTS: conducts adding a faculty
    private void addNewFaculty() {
        String facultyName;
        System.out.println("Please enter the name of your faculty: ");
        facultyName = input.next();
        university.addFaculty(new Faculty(facultyName));
        System.out.println("Done!");
    }

    //MODIFIES: this
    //EFFECTS: conducts adding a student to a specific faculty
    private void addNewStudent() {
        String facultyName = getFacultyName();
        int indexChosenFaculty = getFacultyIndex(facultyName);
        Student student = createNewStudent();
        List<Faculty> faculties = university.getAllFaculties();
        faculties.get(indexChosenFaculty).addStudent(student);
        System.out.println("Done!");
    }

    //EFFECTS : return true if the chosen faculty matches
    //any of the available faculties, false otherwise
    private boolean isAvailableFaculty(String command) {
        List<Faculty> faculties = university.getAllFaculties();
        for (Faculty f : faculties) {
            if (command.equals(f.getName())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS : return the index of the chosen faculty
    private int getFacultyIndex(String facultyName) {
        List<Faculty> faculties = university.getAllFaculties();
        int index = 0;
        for (Faculty f : faculties) {
            if (facultyName.equals(f.getName())) {
                return index;
            }
            index++;
        }
        return 0;
    }

    //REQUIRES : student name has non-zero length
    // this :
    //EFFECTS : creates a new student to be added to a faculty
    private Student createNewStudent() {
        String studentName;
        double gpa;
        System.out.println("Please enter student name: ");
        studentName = input.next();
        gpa = getValidGpa();
        return new Student(studentName, gpa);
    }

    //REQUIRES : faculty/student names are already added
    // input has non-zero length
    //MODIFIES : this
    //EFFECTS : remove a student from a chosen faculty
    private void removeStudent() {
        String facultyName = getFacultyName();
        int indexChosenFaculty = getFacultyIndex(facultyName);
        String studentName;
        System.out.println("Write the name of the student you wish to remove");
        displayStudents(indexChosenFaculty);
        studentName = input.next();

        if (isStudentNotAdded(indexChosenFaculty, studentName)) {
            System.out.println("Student is not added");
        } else {
            processRemoving(indexChosenFaculty, studentName);
        }
        System.out.println("Done!");
    }

    //REQUIRES : faculty is already added to the list of faculties
    //input has non-zero length
    //EFFECTS : prompts the user to enter the faculty name until
    //it matches an existing faculty name and return it
    private String getFacultyName() {
        String facultyName;
        System.out.println("Write the name of student's faculty");
        displayFaculties();
        facultyName = input.next();

        while (!isAvailableFaculty(facultyName)) {
            System.out.println("Faculty not available, try again...");
            facultyName = input.next();
        }
        return facultyName;
    }

    //EFFECTS : return true if the student is added to a given faculty
    //false otherwise
    private boolean isStudentNotAdded(int indexChosenFaculty, String studentName) {
        List<Student> students = getStudents(indexChosenFaculty);
        for (Student s : students) {
            if (studentName.equals(s.getName())) {
                return false;
            }
        }
        return true;
    }

    //EFFECTS : display all students in a given faculty
    private void displayStudents() {
        int indexChosenFaculty = getFacultyIndex(getFacultyName());
        displayStudents(indexChosenFaculty);
    }

    //EFFECTS : display all students in a given faculty
    private void displayStudents(int facultyIndex) {
        List<Student> students = getStudents(facultyIndex);
        for (Student s : students) {
            System.out.println(s.getName());
        }
    }

    //MODIFIES : this
    //EFFECTS : finish removing the desired student
    private void processRemoving(int indexChosenFaculty, String studentName) {
        List<Student> students = getStudents(indexChosenFaculty);
        int indexChosenStudent = getIndexChosenStudent(indexChosenFaculty, studentName);
        students.remove(indexChosenStudent);
    }

    //EFFECTS : return the index of the chosen student using his name
    //and his faculty
    private int getIndexChosenStudent(int indexChosenFaculty, String studentName) {
        List<Student> students = getStudents(indexChosenFaculty);
        int index = 0;
        for (Student f : students) {
            if (studentName.equals(f.getName())) {
                return index;
            }
            index++;
        }
        return 0;
    }

    //EFFECTS : return all students in a given faculty
    private List<Student> getStudents(int indexChosenFaculty) {
        List<Faculty> faculties = university.getAllFaculties();
        Faculty chosenFaculty = faculties.get(indexChosenFaculty);
        return chosenFaculty.getAllStudents();
    }

    //EFFECTS : return the number of students in a given faculty
    private void displayNumStudents() {
        String facultyName = getFacultyName();
        int indexChosenFaculty = getFacultyIndex(facultyName);
        List<Student> students = getStudents(indexChosenFaculty);
        System.out.println("The faculty has: " + students.size() + " student(s)");
    }

    //MODIFIES : this
    //EFFECTS : add a new gpa to a given student
    private void addGPA() {
        String facultyName = getFacultyName();
        int facultyIndex = getFacultyIndex(facultyName);
        List<Student> students = getStudents(facultyIndex);
        displayStudents(facultyIndex);
        Student student = getStudent(facultyIndex, students);
        double gpa = getValidGpa();
        student.setGpa(gpa);
        System.out.println("Done!");

    }

    //REQUIRES : input has non-zero length, student name already added
    //EFFECTS : prompts the user to enter a student name until
    //it matches an existing student name and return the student object
    private Student getStudent(int indexChosenFaculty, List<Student> students) {
        String studentName;
        System.out.println("Please enter student name:");
        studentName = input.next();

        while (isStudentNotAdded(indexChosenFaculty, studentName)) {
            System.out.println("Student name is invalid, try again...");
            studentName = input.next();
        }

        int studentIndex = getIndexChosenStudent(indexChosenFaculty, studentName);
        return students.get(studentIndex);
    }

    //REQUIRES : numerical input
    //EFFECTS : prompts the user to enter a gpa util
    //it's within the specified range
    private double getValidGpa() {
        double gpa;
        System.out.println("Enter the student's gpa: ");
        gpa = input.nextDouble();
        while (gpa > 4 || gpa < 0) {
            System.out.println("Invalid gpa, try again");
            gpa = input.nextDouble();
        }
        return gpa;
    }

    //EFFECTS : displays student id and gpa
    private void displayStudentInfo() {
        String facultyName = getFacultyName();
        int facultyIndex = getFacultyIndex(facultyName);
        List<Student> students = getStudents(facultyIndex);
        displayStudents(facultyIndex);
        Student student = getStudent(facultyIndex, students);
        System.out.println("\t" + student.getName() + "'s ID is: " + student.getId());
        System.out.println("\t" + student.getName() + "'s GPA is: " + student.getGpa());
    }

}
