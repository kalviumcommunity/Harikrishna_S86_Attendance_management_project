# School Attendance System

This is a 10-part code-along project to build a comprehensive school attendance system using Java.

## Session 1: Introduction and Orientation

- Verified Java and Git setup.
- Initialized Git repository.
- Created basic project structure with `Main.java`.
- Successfully compiled and ran the initial application.

### How to Run

1. `javac src/com/school/Main.java`
2. `java -cp src com.school.Main`

## Session 2: Core Domain Modelling
- Defined `Student` class with `studentId`, `name`, `setDetails()`, and `displayDetails()` method.
- Defined `Course` class with `courseId`, `courseName`, `setDetails()`, and `displayDetails()` method.
- Utilized arrays of objects in `Main.java` to manage and display multiple students and courses.
- Introduced basic usage of `this` keyword.

### How to Run
1. Navigate to the project root directory.
2. Compile: `javac src/com/school/Student.java src/com/school/Course.java src/com/school/Main.java` (or `javac src/com/school/*.java`)
3. Run: `java -cp src com.school.Main`