package data;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import main.LibrarySystem;
import util.iMenu;
import java.util.UUID;

public class Admin extends User implements iMenu {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public Admin(String name) {
        super(name);
    }

    public boolean isAdmin() {
        boolean loggedIn = false;
        int attemptCount = 0;
        while (!loggedIn && attemptCount < 3) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                loggedIn = true;
                menu();
            } else {
                System.out.println("Invalid username or password. Please try again.");
                attemptCount++;
            }
        }
        return loggedIn;
    }

    @Override
    public void menu() {

        boolean exit = true;
        while (exit) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Input Book");
            System.out.println("2. Display Books");
            System.out.println("3. Add Student");
            System.out.println("4. Display Registered Students");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inputBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }
    }

    private String generateId() {
        String uniqueID = UUID.randomUUID().toString();
        String bookId = uniqueID.replaceAll("-", "").toLowerCase();
        return String.format("%s-%s-%s", bookId.substring(0, 3), bookId.substring(3, 6), bookId.substring(6, 9));
    }

    private void inputBook() {
        String bookId = generateId();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.println("Enter Category");
        System.out.println("1. History Book");
        System.out.println("2. Story Book");
        System.out.println("3. Text Book");
        System.out.print("Pilih Opsi :");
        String category = scanner.nextLine();

        System.out.print("Enter Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Book newBook;
        switch (category) {
            case "1":
                newBook = new HistoryBook(bookId, title, author, stock);
                break;
            case "2":
                newBook = new StoryBook(bookId, title, author, stock);
                break;
            case "3":
                newBook = new TextBook(bookId, title, author, stock);
                break;
            default:
                System.out.println("Invalid book type.");
                return;
        }

        LibrarySystem.addBook(newBook);
    }

    private void displayBooks() {
        System.out.println("\n===== Available Books =====");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", "Book ID", "Title", "Author", "Category", "Stock");
        System.out.println("===================================================================================");

        for (Book book : LibrarySystem.getBookList()) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
    }

    private void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        String nim;
        while (true) {
            System.out.print("Enter NIM: ");
            nim = scanner.nextLine();
            if (nim.length() == 15) {
                break;
            } else {
                System.out.println("NIM harus 15 digit, coba lagi!!!");
            }
        }

        System.out.print("Enter Faculty: ");
        String faculty = scanner.nextLine();

        System.out.print("Enter Program Study: ");
        String program = scanner.nextLine();

        Student newStudent = new Student(name, nim, faculty, program);
        LibrarySystem.addStudent(newStudent);
    }

    private void displayStudents() {
        System.out.println("\n===== Registered Students =====");
        for (Student student : LibrarySystem.studentList) {
            System.out.println("Name: " + student.getName());
            System.out.println("NIM: " + student.getNim());
            System.out.println("Faculty: " + student.getFaculty());
            System.out.println("Program: " + student.getProgram());
            System.out.println();
        }
    }


}