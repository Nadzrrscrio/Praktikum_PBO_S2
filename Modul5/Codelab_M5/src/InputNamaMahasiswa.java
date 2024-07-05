import java.util.ArrayList;
import java.util.Scanner;

public class InputNamaMahasiswa {
    public static void main(String[] args) {
        ArrayList<String> daftarMahasiswa = new ArrayList<>();
        Scanner ini = new Scanner(System.in);

        while (true) {
            System.out.print("Masukkan nama dan (Ketik 'selesai' untuk mengakhiri) :");
            String nama = ini.nextLine();

            try {
                if (nama.isEmpty()) {
                    throw new IllegalArgumentException("!!! Nama tidak boleh kosong !!!");
                }

                if (nama.equalsIgnoreCase("selesai")) {
                    break;
                }

                daftarMahasiswa.add(nama);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }


        System.out.println("\nDaftar Nama Mahasiswa:");
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("Nama ke-" + (i + 1) + ": " + daftarMahasiswa.get(i));
        }
    }
}