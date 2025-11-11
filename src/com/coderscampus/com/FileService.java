package com.coderscampus.com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    /**
     * Reads a CSV file and returns a list of Student objects.
     * Skips the header line automatically.
     */
    public List<Student> loadStudents(String filePath) throws IOException {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) { // skip first line (header)
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 4) {
                    students.add(new Student(parts));
                }
            }
        }

        System.out.println("✅ Loaded " + students.size() + " students from " + filePath);
        return students;
    }

    /**
     * Writes a list of students to a CSV file.
     */
    public void writeStudentsToFile(String filename, List<Student> students) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        writer.write("StudentID,Student Name,Course,Grade");
        writer.newLine();

        for (Student s : students) {
            if (s != null && s.getStudentId() != null) {
                writer.write(String.join(",",
                        s.getStudentId(),
                        s.getName(),
                        s.getCourse(),
                        String.valueOf(s.getGrade())));
                writer.newLine();
            }
        }
    }
    System.out.println("File saved: " + filename);
}
}
