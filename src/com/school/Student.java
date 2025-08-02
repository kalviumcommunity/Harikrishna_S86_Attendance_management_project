package com.school;

public class Student {
    private int studentId;
    private String name;

    public void setDetails(int id, String name) {
        this.studentId = id;
        this.name = name;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
    }
}
