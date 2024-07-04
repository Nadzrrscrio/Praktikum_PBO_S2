import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String nama;
    String nim;
    String fakultas;
    String programStudi;

    Student(String nama, String nim, String fakultas, String programStudi) {
        this.nama = nama;
        this.nim = nim;
        this.fakultas = fakultas;
        this.programStudi = programStudi;
    }
}

class Buku {
    public String judul;
    public String penulis;
    public String id;
    public String genre;
    public int stok;

    public Buku(String judul, String penulis, String id, String genre, int stok) {
        this.judul = judul;
        this.penulis = penulis;
        this.id = id;
        this.genre = genre;
        this.stok = stok;
    }
}

public class Tugas {
    private ArrayList<Buku> listBuku;
    private ArrayList<Student> userSiswa;
    private Scanner scanner;
    static Scanner inputuser = new Scanner(System.in);

    static String adminusername = "admin", adminpassword = "menyala";


    public Tugas() {
        listBuku = new ArrayList<>();
        userSiswa = new ArrayList<>();
        scanner = new Scanner(System.in);

        listBuku.add(new Buku("One Piece", "Eiichiro Oda", "1", "Fantasi", 10));
        listBuku.add(new Buku("Fantastic Beast", "J.K. Rowling", "2", "Fantasi", 7));
        listBuku.add(new Buku("Bulan", "Tere Liye", "3", "Fiksi", 3));
    }

    public void menu() {
        System.out.println("\n===== Menu Perpustakaan =====");
        System.out.println("1. Masuk Sebagai Siswa");
        System.out.println("2. Masuk Sebagai Admin");
        System.out.print("Pilihan? : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                inputNim();
                break;
            case 2:
                loginadmin();
                menuAdmin();
                break;
            default:
                System.out.println("Pilihan tidak Valid, coba lagi!!!");
                menu();
        }
    }

    private void inputNim() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        if (ceckNim(nim)) {
            menuSiswa();
        } else {
            System.out.println("NIM Tidak VAlid!!!.");
            inputNim();
        }
    }

    private boolean ceckNim(String nim) {
        for (Student student : userSiswa) {
            if (student.nim.equals(nim)) {
                return true;
            }
        }
        return false;
    }

    static void loginadmin(){
        int adminloop;
        do {
            System.out.println("\n==== Login ====");
            System.out.print("Masukkan Username: \n");
            String username = inputuser.nextLine();

            System.out.print("Massukkan password: \n");
            String password = inputuser.nextLine();

            if (username.equals(adminusername) && password.equals(adminpassword)) {
                System.out.println("==== Login berhasil ====\n");
                adminloop = 1;
            } else {
                System.out.println("==== Admin tidak ditemukan ====");
                adminloop = 0;
            }
        }while (adminloop == 0);
    }

    private void menuAdmin() {
        System.out.println("\n===== Admin Menu =====");
        System.out.println("1. Tambah Siswa");
        System.out.println("2. Tampilkan Registrasi Siswa");
        System.out.println("3. Keluar");
        System.out.print("Pilihan?: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                tambahSiswa();
                break;
            case 2:
                tampilanSiswa();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Pilihan Tidak Valid, coba lagi!!!.");
                menuAdmin();
        }
    }

    private void menuSiswa() {
        System.out.println("\n===== Dashboard Siswa =====");
        System.out.println("1. Tampilkan buku yang tersedia");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Buku Pinjaman");
        System.out.println("4. Keluar");
        System.out.print("Pilihanmu?: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                tampilanBuku();
                break;
            case 2:
                pinjamBuku();
                break;
            case 3:
                bukuPinjaman();
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                menuSiswa();
        }
    }

    private void tampilanBuku() {
        System.out.println("\n===== Available Books =====");
        System.out.printf("|| %-25s || %-25s || %-25s || %-20s || %-3s ||", "ID Buku", "Judul", "Penulis", "Genre", "Stok");
        System.out.println("\n================================================================================");
        for (Buku book : listBuku) {

            System.out.printf("|| %-25s || %-25s || %-25s || %-20s || %-3s ||", book.id, book.judul, book.penulis, book.genre, book.stok);
            System.out.println();
        }
        menuSiswa();
    }

    void pinjamBuku() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (Buku book : listBuku) {
            if (book.id.equals(id)) {
                found = true;
                if (book.stok > 0) {
                    book.stok--;
                    System.out.println("Kamu telah meminjam Buku " + book.judul + ".");
                } else {
                    System.out.println("Maaf, buku ini tidak tersedia.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Buku dengan ID tersebut tidak ditemukan.");
        }
        menuSiswa();
    }

    private void bukuPinjaman() {
        System.out.println("\n===== Buku Terpinjam =====");

        ArrayList<Buku> borrowedBooks = new ArrayList<>();

        for (Buku book : listBuku) {
            int originalStock = book.stok;
            if (book.stok < originalStock) {
                borrowedBooks.add(book);
            }
        }

        if (borrowedBooks.isEmpty()) {
            System.out.println("Kamu belum meminjam buku apapun.");
        } else {
            for (Buku book : borrowedBooks) {
                System.out.println("Judul : " + book.judul);
                System.out.println("Penulis : " + book.penulis);
                System.out.println("ID : " + book.id);
                System.out.println("Genre : " + book.genre);
                System.out.println();
            }
        }

        menuSiswa();
    }

    private void logout() {
        System.out.println("Keluar kuyy!!!...");
        menu();
    }


    private void tampilanSiswa() {
        System.out.println("\n===== Registrasi Siswa =====");
        for (Student student : userSiswa) {
            System.out.println("Nama: " + student.nama);
            System.out.println("NIM: " + student.nim);
            System.out.println("Fakultas: " + student.fakultas);
            System.out.println("Program Studi: " + student.programStudi);
            System.out.println();
        }
        menuAdmin();
    }

    private void tambahSiswa() {
        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        if (nim.length() != 15) {
            System.out.println("Invalid NIM. NIM harus panjang 15 angka.");
            tambahSiswa();
            return;
        }
        System.out.print("Masukkan fakultas: ");
        String fakultas = scanner.nextLine();
        System.out.print("Masukkan program studi: ");
        String programStudi = scanner.nextLine();

        Student newStudent = new Student( nama, nim, fakultas, programStudi);
        userSiswa.add(newStudent);
        System.out.println("Mahasiswa Berhasil ditambahkan.");
        menuAdmin();
    }


    public static void main(String[] args) {
        Tugas app = new Tugas();
        app.menu();
    }
}