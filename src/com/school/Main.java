package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RegistrationService regService = new RegistrationService();

        // Create students using constructor and register them
        Student s1 = new Student("Alice", "Grade 10");
        Student s2 = new Student("Bob", "Grade 11");
        Student s3 = new Student("Charlie", "Grade 12");
        Student s4 = new Student("Diana", "Grade 10");
        regService.addStudent(s1);
        regService.addStudent(s2);
        regService.addStudent(s3);
        regService.addStudent(s4);

        // Create courses using constructor and register them
        Course c1 = new Course("Mathematics");
        Course c2 = new Course("Physics");
        Course c3 = new Course("Chemistry");
        regService.addCourse(c1);
        regService.addCourse(c2);
        regService.addCourse(c3);

        // Create Teacher and Staff objects and register them
        Teacher teacher1 = new Teacher("Mr. Smith", "Mathematics");
        Teacher teacher2 = new Teacher("Ms. Johnson", "Physics");
        Staff staff1 = new Staff("Mrs. Brown", "Clerk");
        Staff staff2 = new Staff("Mr. Green", "Lab Assistant");
        regService.addTeacher(teacher1);
        regService.addTeacher(teacher2);
        regService.addStaff(staff1);
        regService.addStaff(staff2);

        // Demonstrate polymorphism via a List<Person>
        List<Person> people = new java.util.ArrayList<>();
        // Add students (Student extends Person)
        people.addAll(regService.getStudents());
        // Add teachers and staff
        people.addAll(regService.getTeachers());
        people.addAll(regService.getStaffMembers());

        System.out.println("----- School Directory (Polymorphic Display) -----");
        displaySchoolDirectory(people);
        System.out.println();

        System.out.println("----- Student Details -----");
        for (Student s : regService.getStudents()) {
            s.displayDetails();
            System.out.println();
        }

        System.out.println("----- Teacher Details -----");
        teacher1.displayDetails();
        System.out.println();
        teacher2.displayDetails();
        System.out.println();

        System.out.println("----- Staff Details -----");
        staff1.displayDetails();
        System.out.println();
        staff2.displayDetails();
        System.out.println();

        System.out.println("----- Course Details -----");
        for (Course c : regService.getCourses()) {
            c.displayDetails();
            System.out.println();
        }

        // ===== PART 8: ATTENDANCE SERVICE WITH OVERLOADED METHODS =====
        AttendanceService attendanceService = new AttendanceService(regService);

        // Demonstrate overloaded markAttendance methods
        System.out.println("\n===== Part 8: Overloaded Attendance Methods =====\n");

        // Method 1: Using Student and Course objects directly
        System.out.println("----- Method 1: markAttendance(Student, Course, String) -----");
        attendanceService.markAttendance(s1, c1, "Present");
        attendanceService.markAttendance(s2, c2, "Absent");
        attendanceService.markAttendance(s3, c3, "Present");
        attendanceService.markAttendance(s4, c1, "Present");
        System.out.println();

        // Method 2: Using student and course IDs with lookup
        System.out.println("----- Method 2: markAttendance(int, int, String, Lists) with ID lookup -----");
        attendanceService.markAttendance(s1.getId(), c2.getCourseId(), "Absent");
        attendanceService.markAttendance(s2.getId(), c3.getCourseId(), "Present");
        attendanceService.markAttendance(s3.getId(), c1.getCourseId(), "Absent");
        System.out.println();

        // Demonstrate overloaded displayAttendanceLog methods
        System.out.println("----- Method 3: displayAttendanceLog() - All Records -----");
        attendanceService.displayAttendanceLog();
        System.out.println();

        // Display records filtered by student
        System.out.println("----- Method 4: displayAttendanceLog(Student) - Filter by Student -----");
        attendanceService.displayAttendanceLog(s1); // Alice's records
        System.out.println();

        attendanceService.displayAttendanceLog(s2); // Bob's records
        System.out.println();

        // Display records filtered by course
        System.out.println("----- Method 5: displayAttendanceLog(Course) - Filter by Course -----");
        attendanceService.displayAttendanceLog(c1); // Mathematics records
        System.out.println();

        attendanceService.displayAttendanceLog(c2); // Physics records
        System.out.println();

        // ===== PART 10: CAPACITY MANAGEMENT & SOLID PRINCIPLES =====
        System.out.println("\n===== Part 10: Capacity Management & SOLID Principles =====\n");

        // Create courses with specific capacity using createCourse method
        System.out.println("----- Creating Courses with Capacity -----");
        Course mathCourse = regService.createCourse("Advanced Mathematics", 2);
        Course scienceCourse = regService.createCourse("General Science", 3);
        System.out.println();

        System.out.println("----- Course Details (with Capacity) -----");
        mathCourse.displayDetails();
        System.out.println();
        scienceCourse.displayDetails();
        System.out.println();

        // Demonstrate student enrollment with capacity checking
        System.out.println("----- Enrolling Students in Courses -----");
        regService.enrollStudentInCourse(s1, mathCourse);
        regService.enrollStudentInCourse(s2, mathCourse);
        System.out.println();

        // Try to exceed capacity
        System.out.println("----- Attempting to Exceed Course Capacity -----");
        regService.enrollStudentInCourse(s3, mathCourse);
        System.out.println();

        // Enroll students in science course (with available capacity)
        System.out.println("----- Enrolling Multiple Students in Science Course -----");
        regService.enrollStudentInCourse(s1, scienceCourse);
        regService.enrollStudentInCourse(s2, scienceCourse);
        regService.enrollStudentInCourse(s3, scienceCourse);
        System.out.println();

        // Try to exceed science course capacity
        System.out.println("----- Attempting to Exceed Science Course Capacity -----");
        regService.enrollStudentInCourse(s4, scienceCourse);
        System.out.println();

        // Display updated course information
        System.out.println("----- Updated Course Details -----");
        mathCourse.displayDetails();
        System.out.println();
        scienceCourse.displayDetails();
        System.out.println();

        // Display enrolled students
        System.out.println("----- Enrolled Students in Math Course -----");
        for (Student student : mathCourse.getEnrolledStudents()) {
            System.out.println("  - " + student.getName() + " (ID: " + student.getId() + ")");
        }
        System.out.println();

        System.out.println("----- Enrolled Students in Science Course -----");
        for (Student student : scienceCourse.getEnrolledStudents()) {
            System.out.println("  - " + student.getName() + " (ID: " + student.getId() + ")");
        }
        System.out.println();

        // File Storage
        System.out.println("----- File Storage -----");
        // Save registered data using RegistrationService
        regService.saveAll();

        // Save attendance records using AttendanceService
        attendanceService.saveAttendanceLog("attendance_log.txt");
        System.out.println("Total attendance records: " + attendanceService.getRecordCount());

        // SOLID Principles Discussion
        System.out.println("\n===== SOLID Principles Discussion =====");
        System.out.println("Part 10 demonstrates the following SOLID principles:\n");
        System.out.println("1. Single Responsibility Principle (SRP):");
        System.out.println("   - Course class handles course data management");
        System.out.println("   - RegistrationService handles enrollment logic separately");
        System.out.println("   - AttendanceService handles attendance tracking\n");
        System.out.println("2. Liskov Substitution Principle (LSP):");
        System.out.println("   - Student extends Person and can be used wherever Person is expected");
        System.out.println("   - All course types implement Storable interface\n");
        System.out.println("3. Interface Segregation Principle (ISP):");
        System.out.println("   - Storable interface defines minimal required methods");
        System.out.println("   - Classes implement only what they need\n");
        System.out.println("4. Open/Closed Principle (OCP):");
        System.out.println("   - New course types can be added without modifying existing code");
        System.out.println("   - Enrollment validation logic is extensible\n");
        System.out.println("5. Dependency Inversion Principle (DIP):");
        System.out.println("   - Services depend on abstractions (Storable, Person)");
        System.out.println("   - FileStorageService abstracts file operations\n");
    }

    public static void displaySchoolDirectory(List<Person> people) {
        for (Person person : people) {
            person.displayDetails();
            System.out.println();
        }
    }
}