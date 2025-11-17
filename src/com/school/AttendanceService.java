package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceService implements Storable {
    private List<AttendanceRecord> attendanceLog;
    private FileStorageService fileStorageService;

    public AttendanceService() {
        this.attendanceLog = new ArrayList<>();
        this.fileStorageService = new FileStorageService();
    }

    /**
     * Overloaded markAttendance method - using Student and Course objects directly
     * 
     * @param student the Student object
     * @param course the Course object
     * @param status the attendance status (Present or Absent)
     */
    public void markAttendance(Student student, Course course, String status) {
        AttendanceRecord record = new AttendanceRecord(student, course, status);
        attendanceLog.add(record);
        System.out.println("Attendance marked: " + student.getName() + " for " + course.getCourseName() + " - " + status);
    }

    /**
     * Overloaded markAttendance method - using IDs and lookup lists
     * Performs lookups to find Student and Course objects by their IDs
     * 
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     * @param status the attendance status (Present or Absent)
     * @param allStudents list of all students for lookup
     * @param allCourses list of all courses for lookup
     */
    public void markAttendance(int studentId, int courseId, String status, 
                              List<Student> allStudents, List<Course> allCourses) {
        Student student = allStudents.stream()
                .filter(s -> s.getId() == studentId)
                .findFirst()
                .orElse(null);
        
        Course course = allCourses.stream()
                .filter(c -> c.getCourseId() == courseId)
                .findFirst()
                .orElse(null);
        
        if (student != null && course != null) {
            markAttendance(student, course, status);
        } else {
            System.out.println("Error: Student ID " + studentId + " or Course ID " + courseId + " not found.");
        }
    }

    /**
     * Overloaded displayAttendanceLog method - displays all attendance records
     */
    public void displayAttendanceLog() {
        if (attendanceLog.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }
        System.out.println("===== All Attendance Records =====");
        for (AttendanceRecord record : attendanceLog) {
            record.displayRecord();
        }
    }

    /**
     * Overloaded displayAttendanceLog method - displays records filtered by student
     * Uses Java Streams for filtering
     * 
     * @param student the student to filter by
     */
    public void displayAttendanceLog(Student student) {
        List<AttendanceRecord> filteredRecords = attendanceLog.stream()
                .filter(record -> record.getStudent() != null && 
                        record.getStudent().getId() == student.getId())
                .collect(Collectors.toList());
        
        if (filteredRecords.isEmpty()) {
            System.out.println("No attendance records found for student: " + student.getName());
            return;
        }
        System.out.println("===== Attendance Records for Student: " + student.getName() + " =====");
        for (AttendanceRecord record : filteredRecords) {
            record.displayRecord();
        }
    }

    /**
     * Overloaded displayAttendanceLog method - displays records filtered by course
     * Uses Java Streams for filtering
     * 
     * @param course the course to filter by
     */
    public void displayAttendanceLog(Course course) {
        List<AttendanceRecord> filteredRecords = attendanceLog.stream()
                .filter(record -> record.getCourse() != null && 
                        record.getCourse().getCourseId() == course.getCourseId())
                .collect(Collectors.toList());
        
        if (filteredRecords.isEmpty()) {
            System.out.println("No attendance records found for course: " + course.getCourseName());
            return;
        }
        System.out.println("===== Attendance Records for Course: " + course.getCourseName() + " =====");
        for (AttendanceRecord record : filteredRecords) {
            record.displayRecord();
        }
    }

    /**
     * Get the list of all attendance records
     * 
     * @return the list of attendance records
     */
    public List<AttendanceRecord> getAttendanceLog() {
        return attendanceLog;
    }

    /**
     * Save the attendance log to a file
     * 
     * @param filename the name of the file to save to
     */
    public void saveAttendanceLog(String filename) {
        fileStorageService.saveData(attendanceLog, filename);
        System.out.println("Attendance log saved to " + filename);
    }

    /**
     * Get the count of attendance records
     * 
     * @return the number of attendance records
     */
    public int getRecordCount() {
        return attendanceLog.size();
    }

    @Override
    public String toDataString() {
        return "AttendanceService with " + attendanceLog.size() + " records";
    }
}
