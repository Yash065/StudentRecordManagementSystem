import java.io.*;
import java.util.*;

public class StudentRecordManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Record Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    System.out.println("Exiting Program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student details:");
        System.out.print("Student ID: ");
        int id = scanner.nextInt();
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Grade: ");
        String grade = scanner.next();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_records.txt", true))) {
            writer.write(id + "," + name + "," + grade);
            writer.newLine();
            System.out.println("Student added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_records.txt"))) {
            System.out.println("All Students:");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("ID: " + parts[0] + ", Name: " + parts[1] + ", Grade: " + parts[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void searchStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student ID to search: ");
        int searchId = scanner.nextInt();

        try (BufferedReader reader = new BufferedReader(new FileReader("student_records.txt"))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);

                if (id == searchId) {
                    found = true;
                    System.out.println("Student Found - ID: " + parts[0] + ", Name: " + parts[1] + ", Grade: " + parts[2]);
                    break;
                }
            }

            if (!found) {
                System.out.println("Student with ID " + searchId + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
