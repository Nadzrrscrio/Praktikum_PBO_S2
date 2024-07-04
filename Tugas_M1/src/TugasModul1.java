import java.util.Scanner;

public class TugasModul1 {
    private static final String Admin_UN = "admin";
    private static final String Admin_PW = "menyalaaa";

    public static void main(String[] args) {
        Scanner nadzar = new Scanner(System.in);

        System.out.println("Sistem library");
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Mahasiswa");
        System.out.println("3. Exit");

        System.out.print("Pilihan Anda: ");
        int choice = nadzar.nextInt();

        switch (choice) {
            case 1:
                adminLogin(nadzar);
                break;
            case 2:
                mahasiswaLogin(nadzar);
                break;
            case 3:
                System.out.println("Terima kasih telah menggunakan sistem library.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void adminLogin(Scanner nadzar) {
        System.out.print("Masukkan username: ");
        String username = nadzar.next();
        System.out.print("Masukkan password: ");
        String password = nadzar.next();

        if (username.equals(Admin_UN) && password.equals(Admin_PW)) {
            System.out.println("Login berhasil sebagai admin.");
        } else {
            System.out.println("Username atau password salah.");
        }
    }

    private static void mahasiswaLogin(Scanner nadzar) {
        System.out.print("Masukkan NIM: ");
        String nim = nadzar.next();

        if (nim.length() == 15) {
            System.out.println("Login berhasil sebagai mahasiswa.");
        } else if (nim.length() < 15){
            System.out.println("Login berhasil sebagai mahasiswa");
        } else {
            System.out.println("Panjang NIM tidak boleh lebih dari 15 karakter");
        }
    }
}