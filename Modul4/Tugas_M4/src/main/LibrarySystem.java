package main;

import java.util.ArrayList;
import java.util.Scanner;
import books.Book;
import data.Admin;
import data.Student;

public class  LibrarySystem {
    private static ArrayList<Book> bookList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    private static Admin admin;
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        admin = new Admin("Admin");

        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    studentLogin();
                    break;

                case 2:
                    if (admin.isAdmin()) {
                        admin.menu();
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void studentLogin() {
        System.out.print("Enter your NIM: ");
        String nim = scanner.nextLine();

        Student currentStudent = getStudentByNim(nim);
        if (currentStudent != null) {
            currentStudent.menu();
        } else {
            System.out.println("Student not found. Please try again.");
        }
    }


    private static Student getStudentByNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }


    public static void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student added successfully.");
    }
    public static void addBook(Book book) {
        bookList.add(book);
        System.out.println("Book added successfully.");
    }
    public static ArrayList<Book> getBookList() {
        return bookList;
    }
}