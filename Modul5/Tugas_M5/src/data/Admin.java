package data;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import exception.custom.IllegalAdminAccessException;
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
        try {
            System.out.print("Masukkan username : ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password : ");
            String password = scanner.nextLine();

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                return true;
            } else {
                throw new IllegalAdminAccessException("Invalid credentials");
            }
        } catch (IllegalAdminAccessException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void menu() {

        boolean exit = true;
        while (exit) {
            System.out.println("\n===== Menu Admin =====");
            System.out.println("1. Input buku");
            System.out.println("2. Tampilkan buku");
            System.out.println("3. Tambahkan siswa");
            System.out.println("4. Tampilkan daftar siswa");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi! : ");

            try {
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
                        System.out.println("Pilihan tidak valid, coba lagi!");
                }

            } catch (Exception e) {
                System.out.println("Pilihan tidak valid, masukkan angka!.");
                scanner.nextLine();
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

        System.out.print("Masukkan judul : ");
        String title = scanner.nextLine();

        System.out.print("Masukkan penulis : ");
        String author = scanner.nextLine();

        System.out.println("Masukkan Kategori");
        System.out.println("1. History Book");
        System.out.println("2. Story Book");
        System.out.println("3. Text Book");
        System.out.print("Pilih Opsi! : ");
        String category = scanner.nextLine();

        System.out.print("Masukkan jumlah stok : ");
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
                System.out.println("Tipe buku tidak valid.");
                return;
        }

        LibrarySystem.addBook(newBook);
    }

    private void displayBooks() {
        System.out.println("\n========== Daftar Buku ==========");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", "ID buku", "Judul", "Penulis", "Kategori", "Stok");
        System.out.println("=================================================================================================================");

        for (Book book : LibrarySystem.getBookList()) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
    }

    private void addStudent() {
        System.out.print("Masukkan nama siswa : ");
        String name = scanner.nextLine();

        String nim;
        while (true) {
            System.out.print("Masukkan NIM : ");
            nim = scanner.nextLine();
            if (nim.length() == 15) {
                break;
            } else {
                System.out.println("NIM harus 15 digit, coba lagi!!!");
            }
        }

        System.out.print("Masukkan password : ");
        String password = scanner.nextLine();

        System.out.print("Masukkan fakultas : ");
        String faculty = scanner.nextLine();

        System.out.print("Masukkan program studi : ");
        String program = scanner.nextLine();

        Student newStudent = new Student(name, nim, faculty, program, password);
        LibrarySystem.addStudent(newStudent);
    }

    private void displayStudents() {
        System.out.println("\n===== Daftar nama siswa =====");
        for (Student student : LibrarySystem.studentList) {
            System.out.println("Nama : " + student.getName());
            System.out.println("NIM : " + student.getNim());
            System.out.println("Fakultas : " + student.getFaculty());
            System.out.println("Program studi : " + student.getProgram());
            System.out.println();
        }
    }


}