# University Enrolment System

*The application will represent a university enrolment system. It permits the addition, removal, and retrieval of student data within various academic faculties. Universities can utilize this project to maintain comprehensive student databases. This project holds an interest for me because I've always been curious about how universities manage student information. I will be creating a similar project and continually enhancing it for future use.*

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

### Instructions for Grader

- You can add a new faculty using the Add Faculty button
- You can remove faculty using the Remove Faculty button
- You can add a student to a faculty by selecting the faculty and using the Add Student button
- You can remove a student from a faculty by selecting the faculty and using the Remove Student button
- You can locate my visual component in the data package
- You can save the state of the university using the Save Data button
- You can reload the state of the university using the Load Data button



### Phase 4 Task 2 

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

### Phase 4 Task 3
In the current design, the university has a name and a list of faculties. Likewise, each faculty has a name and a list of students. Getting a student directly from the university is not possible. Therefore, having a Map in university to store faculties and corresponding students makes things better. This way, university will have lists of faculties and corresponding students and accessing students directly from the university will be easier 
