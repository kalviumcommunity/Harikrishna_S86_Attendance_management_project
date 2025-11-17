package com.school;

public class AttendanceRecord implements Storable {
    private Student student;
    private Course course;
    private String status;

    public AttendanceRecord(Student student, Course course, String status) {
        this.student = student;
        this.course = course;
        if (status != null && (status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent"))) {
            this.status = status;
        } else {
            this.status = "Invalid";
            System.out.println("Warning: Invalid attendance status. Setting status to 'Invalid'.");
        }
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    public void displayRecord() {
        String studentInfo = (student != null) ? (student.getName() + " (ID:" + student.getId() + ")")
                : "Unknown Student";
        String courseInfo = (course != null) ? (course.getCourseName() + " (C" + course.getCourseId() + ")")
                : "Unknown Course";
        System.out.println("Student: " + studentInfo + ", Course: " + courseInfo + ", Status: " + status);
    }

    @Override
    public String toDataString() {
        int sid = (student != null) ? student.getId() : -1;
        int cid = (course != null) ? course.getCourseId() : -1;
        return sid + "," + cid + "," + status;
    }
}