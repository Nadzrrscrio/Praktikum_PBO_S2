package main;

import java.util.ArrayList;
import java.util.Scanner;
import books.Book;
import data.Admin;
import data.Student;
import exception.custom.IllegalAdminAccessException;

public class  LibrarySystem {
    private static ArrayList<Book> bookList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    private static Admin admin;
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IllegalAdminAccessException {
        admin = new Admin("Admin");

        mainMenu();

    }

    private static void mainMenu() throws IllegalAdminAccessException {
        while (true) {
            System.out.println("\n===== Perpustakaan =====");
            System.out.println("1. Masuk sebagai siswa ");
            System.out.println("2. Masuk sebagi admin");
            System.out.print("Pilih opsi! : ");

            try {
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
                        System.out.println("Pilihan tidak valid, coba lagi!");
                }
            } catch (Exception e) {
                System.out.println("Pilihan tidak valid, masukkan angka!");
                scanner.nextLine();
            }
        }
    }

    private static void studentLogin() {
        System.out.print("Masukkan NIM kamu: ");
        String nim = scanner.nextLine();

        Student currentStudent = getStudentByNim(nim);
        if (currentStudent != null) {
            System.out.print("Masukkan password kamu : ");
            String password = scanner.nextLine();
            if (currentStudent.getPassword().equals(password)) {
                currentStudent.menu();
            } else {
                System.out.println("Password salah, coba lagi!");
            }

        } else {
            System.out.println("Siswa tak terdaftar, coba lagi!");
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
        System.out.println("Penambahan siswa berhasil.");
    }
    public static void addBook(Book book) {
        bookList.add(book);
        System.out.println("Penambahan buku berhasil.");
    }
    public static ArrayList<Book> getBookList() {
        return bookList;
    }
}