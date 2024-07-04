package data;

import java.util.ArrayList;
import books.Book;
import main.LibrarySystem;
import util.iMenu;

public class Student extends User implements iMenu {
    private String nim;
    private String faculty;
    private String program;

    public Student(String name, String nim, String faculty, String program) {
        super(name);
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgram() {
        return program;
    }

    @Override
    public void menu() {

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Student Dashboard =====");
            System.out.println("1. Display Book");
            System.out.println("2. Choice Book");
            System.out.println("3. Borrowed Books");
            System.out.println("4. Return Book");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        displayBooks();
                        break;
                    case 2:
                        choiceBook();
                        break;
                    case 3:
                        showBorrowedBooks();
                        break;
                    case 4:
                        returnBook();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void choiceBook() {
        System.out.println("\n===== Available Books =====");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", "Book ID", "Title", "Author", "Category", "Stock");
        System.out.println("===================================================================================");

        ArrayList<Book> bookList = LibrarySystem.getBookList();
        for (Book book : bookList) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }

        System.out.print("Enter Book ID to borrow: ");
        String bookId = scanner.nextLine();

        System.out.print("Enter the duration in days: ");
        int durationDays = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book book : LibrarySystem.getBookList()) {
            if (book.getBookId().equals(bookId)) {
                found = true;
                if (book.getStock() > 0) {
                    book.borrowBook(durationDays);
                } else {
                    System.out.println("No more copies available for this book.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Book with the given ID not found.");
        }
    }

    private void displayBooks() {
        System.out.println("\n===== Available Books =====");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", "Book ID", "Title", "Author", "Category", "Stock");
        System.out.println("===================================================================================");

        ArrayList<Book> bookList = LibrarySystem.getBookList();
        for (Book book : bookList) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
    }

    private void showBorrowedBooks() {
        System.out.println("\n===== Borrowed Books =====");
        boolean hasBorrowedBooks = false;

        for (Book book : LibrarySystem.getBookList()) {
            if (book.getBorrowedCount() > 0) {
                hasBorrowedBooks = true;
                System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-10s || %-10s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getBorrowedDate(), "Due in " + book.getDurationDays() + " days");
            }
        }

        if (!hasBorrowedBooks) {
            System.out.println("You haven't borrowed any books yet.");
        }
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine();

        boolean found = false;
        for (Book book : LibrarySystem.getBookList()) {
            if (book.getBookId().equals(bookId)) {
                found = true;
                book.returnBook();
                break;
            }
        }

        if (!found) {
            System.out.println("Book with the given ID not found or not borrowed by you.");
        }
    }
}