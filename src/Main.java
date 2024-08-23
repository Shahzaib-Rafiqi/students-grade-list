import java.util.List;
import java.util.Map;
import java.util.Collections;


public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.loadStudentsFromFile("src/students.txt");
        System.out.println("Students loaded from file:");
        manager.printStudents();

        System.out.println("\nSorting students by grade (descending):");
        manager.sortStudentsByGrade();
        manager.printStudents();

        System.out.println("\nTop student:");
        Student topStudent = manager.getTopStudent();
        System.out.println(topStudent);

        System.out.println("\nGrouping students by grade:");
        Map<Double, List<Student>> groupedStudents = manager.groupStudentsByGrade();
        for (Map.Entry<Double, List<Student>> entry : groupedStudents.entrySet()) {
            System.out.println("Grade " + entry.getKey() + ":");
            for (Student student : entry.getValue()) {
                System.out.println(student);
            }
        }
    }
}