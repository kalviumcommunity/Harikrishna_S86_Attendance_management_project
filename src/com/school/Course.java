package com.school;

public class Course {
    private int courseId;
    private String courseName;

    public void setDetails(int id, String name) {
        this.courseId = id;
        this.courseName = name;
    }

    public void displayDetails() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
    }
}
