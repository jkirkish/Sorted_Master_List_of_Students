package com.coderscampus.com;

public class Assignment4App {

    public static void main(String[] args) {
        System.out.println("🚀 Starting Student Segregation Process...");

        try {
            StudentService studentService = new StudentService();
            studentService.segregateStudents();
            System.out.println("✅ Student segregation completed successfully!");

        } catch (Exception e) {
            System.err.println("❌ An error occurred while processing student data:");
            e.printStackTrace();
            System.exit(1); // non-zero exit code indicates failure
        }
    }
}

