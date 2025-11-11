package com.coderscampus.com;

import java.util.Objects;

public class Student implements Comparable<Student> {

    private String studentId;
    private String name;
    private String course;
    private int grade; // store grade as int for efficiency

    // Constructor that takes an array of strings
    public Student(String[] values) {
        if (values == null || values.length < 4) {
            throw new IllegalArgumentException("Invalid student data array");
        }
        this.studentId = values[0];
        this.name = values[1];
        this.course = values[2];
        this.grade = parseGrade(values[3]);
    }

    // Constructor that takes individual values
    public Student(String id, String name, String course, String grade) {
        this.studentId = id;
        this.name = name;
        this.course = course;
        this.grade = parseGrade(grade);
    }

    // Helper method to safely parse grade
    private int parseGrade(String gradeStr) {
        try {
            return Integer.parseInt(gradeStr.trim());
        } catch (NumberFormatException e) {
            return 0; // default to 0 if invalid
        }
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    // toString for easy display
    @Override
    public String toString() {
        return String.format("Student [ID=%s, Name=%s, Course=%s, Grade=%d]",
                studentId, name, course, grade);
    }

    // CompareTo method — higher grades come first
    @Override
    public int compareTo(Student other) {
        if (other == null) return -1;
        return Integer.compare(other.grade, this.grade);
    }

    // Optional: override equals and hashCode for collections support
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return grade == student.grade &&
                Objects.equals(studentId, student.studentId) &&
                Objects.equals(name, student.name) &&
                Objects.equals(course, student.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, course, grade);
    }
}
