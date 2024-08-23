import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void loadStudentsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String id = parts[1].trim();
                    double grade = Double.parseDouble(parts[2].trim());
                    students.add(new Student(name, id, grade));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortStudentsByGrade() {
        students.sort((s1, s2) -> Double.compare(s2.getGrade(), s1.getGrade()));
    }

    public Student getTopStudent() {
        return students.stream().max(Comparator.comparingDouble(Student::getGrade)).orElse(null);
    }

    public Map<Double, List<Student>> groupStudentsByGrade() {
        Map<Double, List<Student>> gradeMap = new TreeMap<>(Collections.reverseOrder());
        for (Student student : students) {
            gradeMap.computeIfAbsent(student.getGrade(), k -> new ArrayList<>()).add(student);
        }
        return gradeMap;
    }

    public void printStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}