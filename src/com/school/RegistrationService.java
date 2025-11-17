package com.school;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Staff> staffMembers;
    private List<Course> courses;
    private FileStorageService storage;

    public RegistrationService() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.storage = new FileStorageService();
    }

    // Add methods
    public void addStudent(Student s) {
        students.add(s);
    }

    public void addTeacher(Teacher t) {
        teachers.add(t);
    }

    public void addStaff(Staff s) {
        staffMembers.add(s);
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    // Lookup methods
    public Student findStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Course findCourseById(int courseId) {
        return courses.stream()
                .filter(c -> c.getCourseId() == courseId)
                .findFirst()
                .orElse(null);
    }

    // Getters for lists
    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Staff> getStaffMembers() {
        return staffMembers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // Create course with capacity parameter
    public Course createCourse(String courseName, int capacity) {
        Course course = new Course(courseName, capacity);
        courses.add(course);
        return course;
    }

    // Enroll a student in a course with capacity checking
    public boolean enrollStudentInCourse(Student student, Course course) {
        if (student == null || course == null) {
            System.out.println("Error: Student or Course is null");
            return false;
        }

        if (course.getEnrollmentCount() >= course.getCapacity()) {
            System.out.println("Error: Course \"" + course.getCourseName() + "\" is full. Capacity: " 
                    + course.getCapacity() + ", Enrolled: " + course.getEnrollmentCount());
            return false;
        }

        boolean enrolled = course.addStudent(student);
        if (enrolled) {
            System.out.println("Success: " + student.getName() + " enrolled in " + course.getCourseName());
        }
        return enrolled;
    }

    // Save all registered entities to files
    public void saveAll() {
        storage.saveData(students, "students.txt");
        storage.saveData(courses, "courses.txt");
        storage.saveData(teachers, "teachers.txt");
        storage.saveData(staffMembers, "staff.txt");
    }
}
