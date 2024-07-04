import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static Scanner nadzar = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = nadzar.nextInt();
            nadzar.nextLine();

            switch (pilihan) {
                case 1:
                    tambahData();
                    break;
                case 2:
                    tampilkanData();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 3);
    }

    static void tambahData() {
        System.out.print("\nMasukkan Nama Mahasiswa: ");
        String nama = nadzar.nextLine();
        String nim;
        do {
            System.out.print("Masukkan NIM Mahasiswa (panjang 15 digit): ");
            nim = nadzar.nextLine();
            if (nim.length() != 15) {
                System.out.println("NIM harus 15 digit!!!");
            }
        } while (String.valueOf(nim).length() != 15);

        System.out.print("Masukkan jurusan : ");
        String jurusan = nadzar.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa(nama, nim, jurusan);
        daftarMahasiswa.add(mahasiswa);
        System.out.println("Data Mahasiswa berhasil ditambahkan.");
    }

    static void tampilkanData() {
        System.out.println("\nData Mahasiswa:");
        Mahasiswa.tampilUniversitas();
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            System.out.println("Nama: " + mahasiswa.nama + "\nNIM: " + mahasiswa.nim + "\nJurusan: " + mahasiswa.jurusan);
        }
    }
}

class Mahasiswa {
    String nama;
    String nim;
    String jurusan;

    Mahasiswa(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    static void tampilUniversitas() {
        System.out.println("Universitas Muhammadiyah Malang");
    }
}