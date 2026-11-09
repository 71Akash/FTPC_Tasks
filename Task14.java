import java.util.Arrays;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String course;
    private int[] marks;

    public Student() {
        this(0, "", "", new int[0]);
    }

    public Student(int id, String name, String course, int[] marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        setMarks(marks);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public int[] getMarks() { return marks.clone(); }
    public void setMarks(int[] marks) { this.marks = marks == null ? new int[0] : marks.clone(); }

    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) total += mark;
        return total;
    }

    public double calculatePercentage() {
        return marks.length == 0 ? 0.0 : (double) calculateTotal() / marks.length;
    }

    public String getGrade() {
        double percentage = calculatePercentage();
        if (percentage >= 90) return "A";
        if (percentage >= 80) return "B";
        if (percentage >= 70) return "C";
        if (percentage >= 60) return "D";
        return "F";
    }

    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + Arrays.toString(marks));
        System.out.println("Total: " + calculateTotal());
        System.out.printf("Percentage: %.2f%%\n", calculatePercentage());
        System.out.println("Grade: " + getGrade());
    }
}

public class Task14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[3];

        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline after the ID.

            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Course: ");
            String course = scanner.nextLine();

            System.out.print("Number of subjects: ");
            int subjectCount = scanner.nextInt();
            int[] marks = new int[subjectCount];
            for (int j = 0; j < subjectCount; j++) {
                System.out.print("Mark for subject " + (j + 1) + ": ");
                marks[j] = scanner.nextInt();
            }
            scanner.nextLine(); // Consume the newline before the next student's name.
            students[i] = new Student(id, name, course, marks);
        }

        System.out.println("\nStudent Details:");
        for (Student student : students) {
            student.printDetails();
            System.out.println();
        }
        scanner.close();
    }
}
