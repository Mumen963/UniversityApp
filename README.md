# University Enrolment System

The University Enrolment System is a comprehensive application designed to manage student enrollment across various academic faculties. With this system, universities can efficiently handle the addition, removal, and retrieval of student data, ensuring streamlined and organized management of their academic records. The application is built to be user-friendly, allowing administrators to easily navigate through different functionalities and maintain a structured database that supports the institution's academic operations.

One of the key features of this application is its ability to save and load the state of the system with precision and reliability. By implementing secure data storage mechanisms, the application ensures that all changes made to student and faculty information are preserved and can be restored at any time. This means that administrators can confidently manage large volumes of data without the risk of loss or corruption. Moreover, the system meticulously records all activities and transactions, providing a detailed audit trail that can be used for accountability and analysis. This comprehensive recording capability not only enhances the reliability of the system but also supports decision-making processes by offering insights into enrollment trends and patterns.

This project holds significant importance to me as it aligns with my passion for data management and accuracy. Developing this system provided me with hands-on experience in building and maintaining reliable data structures, which are essential for any robust software solution. It also allowed me to apply my knowledge of software development practices, enhancing my skills in areas such as object-oriented programming, database design, and user interface development. These experiences are invaluable as I pursue a career in computer science, equipping me with practical skills that will be instrumental in tackling complex data-driven challenges.
### User Stories :
- *As a user, I want to be able to display all faculties*
- *As a user, I want to be able to add a new faculty*
- *As a user, I want to be able to remove a faculty*
- *As a user, I want to be able to select a faculty and add a student to it*
- *As a user, I want to be able to select a faculty and remove a student from it*
- *As a user, I want to be able to select a faculty and view all students in it*
- *As a user, I want to be able to select a faculty and get number of students in it*
- *As a user, I want to be able to select a student in a faculty and add GPA to him*
- *As a user, I want to be able to select a student in a faculty and gets his info*
- *As a user, I want to have the option to save my university data to a file*
- *As a user, I want to have the option to load my university data from a file*

### Instructions for Using the GUI

- You can add a new faculty using the Add Faculty button
- You can remove faculty using the Remove Faculty button
- You can add a student to a faculty by selecting the faculty and using the Add Student button
- You can remove a student from a faculty by selecting the faculty and using the Remove Student button
- You can locate my visual component in the data package
- You can save the state of the university using the Save Data button
- You can reload the state of the university using the Load Data button

### Instructions for Using the University Enrollment System Application
- You can display all faculties using the Display Faculties option (option 1).
- You can add a new faculty using the Add a New Faculty option (option 2).
- You can add a student to a faculty by using the Add a New Student to Faculty option (option 3).
- You can remove a student from a faculty by using the Remove a Student from a Faculty option (option 4).
- You can view all students in a faculty by using the View All Students in a Faculty option (option 5).
- You can get the number of students in a faculty using the Get Number of Students in a Faculty option (option 6).
- You can add a new GPA for a student in a faculty using the Add a New GPA for a Student in a Faculty option (option 7).
- You can get a student's information by using the Get a Student's Info option (option 8).
- You can save the state of the university using the Save University Data to File option (option 9).
- You can reload the state of the university using the Load University Data from File option (option 10).

### The App monitors all activities and displays them upon exiting.

Wed Nov 29 15:05:54 PST 2023
Faculty added : Science

*Wed Nov 29 15:05:56 PST 2023*
*Faculty added : Arts*

*Wed Nov 29 15:06:04 PST 2023*
*Faculty added : Computer Science*

*Wed Nov 29 15:06:07 PST 2023*
*Faculty added : Physics*

*Wed Nov 29 15:06:12 PST 2023*
*A student called Alex added to the following faculty : Science*

*Wed Nov 29 15:06:16 PST 2023*
*A student called Sara added to the following faculty : Science*

*Wed Nov 29 15:06:22 PST 2023*
*A student called Emily added to the following faculty : Arts*

*Wed Nov 29 15:06:27 PST 2023*
*A student called Sally added to the following faculty : Arts*

*Wed Nov 29 15:06:32 PST 2023*
*A student called Alex added to the following faculty : Computer Science*

*Wed Nov 29 15:06:35 PST 2023*
*A student called Mo added to the following faculty : Computer Science*

*Wed Nov 29 15:06:37 PST 2023*
*A student called Mo removed from the following faculty : Computer Science*

*Wed Nov 29 15:06:39 PST 2023*
*A student called Alex removed from the following faculty : Computer Science*

*Wed Nov 29 15:06:41 PST 2023*
*A student called Sally removed from the following faculty : Arts*

*Wed Nov 29 15:06:43 PST 2023*
*A student called Emily removed from the following faculty : Arts*

*Wed Nov 29 15:06:44 PST 2023*
*Faculty removed : Arts*

*Wed Nov 29 15:06:47 PST 2023*
*Faculty removed : Computer Science*

*Wed Nov 29 15:06:48 PST 2023*
*Data saved*



### Future Developments
*In the current design, the university has a name and a list of faculties. Likewise, each faculty has a name and a list of students. Getting a student directly from the university is not possible. Therefore, having a Map in university to store faculties and corresponding students makes things better. This way, university will have lists of faculties and corresponding lists of students for each faculty, and accessing students directly from the university will be possible.*


