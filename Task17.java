import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Employee implements Comparable<Employee> {
    private int id;
    private String name;    
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    // Natural ordering sorts employees by name.
    @Override
    public int compareTo(Employee other) {
        return name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department
                + ", Salary: " + String.format("%.2f", salary);
    }
}

public class Task17 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Employee> employees = new ArrayList<>();

    private static void addEmployee() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        employees.add(new Employee(id, name, department, salary));
        System.out.println("Employee added.");
    }

    private static void removeEmployeeById() {
        System.out.print("Enter the ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = employees.removeIf(employee -> employee.getId() == id);
        System.out.println(removed ? "Employee removed." : "No employee found with that ID.");
    }

    private static void searchEmployeeByName() {
        System.out.print("Enter the name to search: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                System.out.println(employee);
                found = true;
            }
        }
        if (!found) System.out.println("No employee found with that name.");
    }

    private static void printEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        for (Employee employee : employees) System.out.println(employee);
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nEmployee Manager");
            System.out.println("1. Add employee");
            System.out.println("2. Remove employee by ID");
            System.out.println("3. Search employee by name");
            System.out.println("4. Sort by salary (Comparator)");
            System.out.println("5. Sort by name (Comparable)");
            System.out.println("6. Display employees");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    removeEmployeeById();
                    break;
                case 3:
                    searchEmployeeByName();
                    break;
                case 4:
                    employees.sort(Comparator.comparingDouble(Employee::getSalary));
                    System.out.println("Employees sorted by salary:");
                    printEmployees();
                    break;
                case 5:
                    Collections.sort(employees);
                    System.out.println("Employees sorted by name:");
                    printEmployees();
                    break;
                case 6:
                    printEmployees();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}
