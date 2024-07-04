import java.util.Scanner;

public class Balok extends BangunRuang{
    Balok(String name) {
        super(name);
    }

    Scanner scanner = new Scanner(System.in);
    private double panjang;
    private double lebar;
    private double tinggi;

    public void setPanjang(double panjang){
        this.panjang = panjang;
    }

    public double getPanjang(){
        return panjang;
    }

    public void setLebar(double lebar){
        this.lebar = lebar;
    }

    public double getLebar(){
        return lebar;
    }

    public void setTinggi(double tinggi){
        this.tinggi = tinggi;
    }

    public double getTinggi(){
        return tinggi;
    }

    @Override
    public void inputNilai () {
        super.inputNilai();
        System.out.print("Input panjang: ");
        panjang = scanner.nextInt();
        System.out.print("Input lebar: ");
        lebar = scanner.nextInt();
        System.out.print("Input tinggi: ");
        tinggi = scanner.nextInt();
    }

    @Override
    public void luasPermukaan(){
        double hasil = (2 * (panjang * lebar) + (panjang * tinggi) + (lebar * tinggi));
        super.luasPermukaan();
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume(){
        double hasil = (panjang * lebar * tinggi);
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}