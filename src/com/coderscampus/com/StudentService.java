package com.coderscampus.com;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {

    private List<Student> students; // changed from Student[]
    private final FileService fileService = new FileService();

    public StudentService() {
        loadStudents("student-master-list.csv");
    }

    private void loadStudents(String fileName) {
        try {
            students = fileService.loadStudents(fileName); // returns List<Student>
        } catch (IOException e) {
            e.printStackTrace();
            students = new ArrayList<>();
        }
    }

    public void segregateStudents() {
        // Sort all students by descending grades
        students.sort(Comparator.naturalOrder());

        // Filter by course prefix
        Map<String, List<Student>> courseGroups = Map.of(
            "C", filterByCoursePrefix("C"),
            "S", filterByCoursePrefix("S"),
            "A", filterByCoursePrefix("A")
        );

        // Write each course group to file
        try {
            fileService.writeStudentsToFile("course1-r.csv", courseGroups.get("C"));
            fileService.writeStudentsToFile("course2-r.csv", courseGroups.get("S"));
            fileService.writeStudentsToFile("course3-r.csv", courseGroups.get("A"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Student> filterByCoursePrefix(String prefix) {
        List<Student> filtered = students.stream()
                .filter(s -> s.getCourse() != null && s.getCourse().startsWith(prefix))
                .collect(Collectors.toList());

        // Add header for CSV
        filtered.add(0, new Student("StudentID", "Student Name", "Course", "Grade"));

        // Optional: print for validation
        filtered.forEach(System.out::println);

        return filtered;
    }

    public void displaySortedArray() {
        students.stream()
                .sorted()
                .forEach(s -> System.out.println(
                        s.getStudentId() + " ," +
                        s.getName() + " ," +
                        s.getCourse() + " ," +
                        s.getGrade()));
    }
}
