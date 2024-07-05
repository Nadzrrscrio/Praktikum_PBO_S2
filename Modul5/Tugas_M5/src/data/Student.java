package data;

import java.util.ArrayList;
import books.Book;
import main.LibrarySystem;
import util.iMenu;

public class Student extends User implements iMenu {
    private String nim;
    private String faculty;
    private String program;
    private String password;

    public Student(String name, String nim, String faculty, String program, String password) {
        super(name);
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    @Override
    public void menu() {

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Menu Siswa =====");
            System.out.println("1. Tampilkan buku");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Buku terpinjam");
            System.out.println("4. Kembalikan buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi : ");

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
                        System.out.println("Pilihan tidak valid, coba lagi!");
                }

            } catch (Exception e) {
                System.out.println("Pilihan tidak valid, masukkan angka!");
                scanner.nextLine();
            }
        }
    }

    private void choiceBook() {
        System.out.println("\n========== Daftar Buku ==========");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", "ID buku", "Judul", "Penulis", "Kategori", "Stok");
        System.out.println("=================================================================================================================");

        ArrayList<Book> bookList = LibrarySystem.getBookList();
        for (Book book : bookList) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }

        System.out.print("Masukkan ID buku untuk meminjam : ");
        String bookId = scanner.nextLine();

        System.out.print("Masukkan durasi peminjaman(hari) : ");
        int durationDays = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book book : LibrarySystem.getBookList()) {
            if (book.getBookId().equals(bookId)) {
                found = true;
                if (book.getStock() > 0) {
                    book.borrowBook(durationDays);
                } else {
                    System.out.println("Tidak ada stok yang tersedia dari buku ini.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Buku dengan ID tersebut tidak ditemukan.");
        }
    }

    private void displayBooks() {
        System.out.println("\n========== Daftar Buku ==========");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", "ID buku", "Judul", "Penulis", "Kategori", "Stok");
        System.out.println("=================================================================================================================");

        ArrayList<Book> bookList = LibrarySystem.getBookList();
        for (Book book : bookList) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
    }

    private void showBorrowedBooks() {
        System.out.println("\n===== Buku Terpinjam =====");
        boolean hasBorrowedBooks = false;

        for (Book book : LibrarySystem.getBookList()) {
            if (book.getBorrowedCount() > 0) {
                hasBorrowedBooks = true;
                System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-10s || %-10s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getBorrowedDate(), "Due in " + book.getDurationDays() + " days");
            }
        }

        if (!hasBorrowedBooks) {
            System.out.println("Kamu belum meminjam buku apapun.");
        }
    }

    private void returnBook() {
        System.out.print("Masukkan ID buku untuk mengembalikan : ");
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
            System.out.println("Buku dengan ID tersebut tidak dipinjam olehmu.");
        }
    }
}