package com.school;

import java.util.ArrayList;
import java.util.List;

public class Course implements Storable {
    private static int nextCourseIdCounter = 101;
    private int courseId;
    private String courseName;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String courseName) {
        this.courseId = nextCourseIdCounter++;
        this.courseName = courseName;
        this.capacity = 30; // Default capacity
        this.enrolledStudents = new ArrayList<>();
    }

    public Course(String courseName, int capacity) {
        this.courseId = nextCourseIdCounter++;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public void displayDetails() {
        System.out.println("Course ID: C" + this.courseId);
        System.out.println("Course Name: " + this.courseName);
        System.out.println("Capacity: " + this.capacity);
        System.out.println("Enrolled Students: " + this.enrolledStudents.size() + "/" + this.capacity);
    }

    @Override
    public String toDataString() {
        return courseId + "," + courseName + "," + capacity;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getEnrollmentCount() {
        return enrolledStudents.size();
    }

    public boolean addStudent(Student student) {
        if (enrolledStudents.size() >= capacity) {
            return false;
        }
        enrolledStudents.add(student);
        return true;
    }
}