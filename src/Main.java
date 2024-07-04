import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        Scanner nadzar = new Scanner(System.in);

        System.out.print("Nama : ");
        String nama = nadzar.nextLine();

        System.out.print("Kelamin P/L : ");
        String kelaminSatu = nadzar.nextLine();
        String kelamin = kelaminSatu.equalsIgnoreCase("L") ? "laki-laki" : "perempuan";

        System.out.print("Tanggal Lahir (tahun-bulan-tanggal) : ");
        String tanggalLahirStr = nadzar.nextLine();
        LocalDate tanggalLahir = LocalDate.parse(tanggalLahirStr);

        LocalDate tanggalSekarang = LocalDate.now();
        int usia = Period.between(tanggalLahir,tanggalSekarang).getYears();

        System.out.print("Nama : " + nama);
        System.out.print("Kelamin : " + kelamin);
        System.out.print("Usia : " + usia +"tahun");

        nadzar.close();

    }
}