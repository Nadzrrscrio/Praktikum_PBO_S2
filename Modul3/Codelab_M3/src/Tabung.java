import java.util.Scanner;

public class Tabung extends BangunRuang {
    Tabung(String name) {
        super(name);
    }

    //public class Tabung () {
    Scanner scanner = new Scanner(System.in);
    private double tinggi;
    private double jariJari;

    public void setTinggi(double tinggi){
        this.tinggi = tinggi;
    }

    public double getTinggi(double tinggi){
        return tinggi;
    }

    public void setJariJari(double jariJari){
        this.jariJari = jariJari;
    }

    public double getJariJari(double jariJari){
        return jariJari;
    }

    @Override
    public void inputNilai(){
        super.luasPermukaan();
        System.out.print("Input tinggi: ");
        tinggi = scanner.nextInt();
        System.out.print("Input jari-jari: ");
        jariJari = scanner.nextInt();
    }

    @Override
    public void luasPermukaan(){
        double hasil = 2 * Math.PI * jariJari * (jariJari + tinggi);
        super.luasPermukaan();
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume(){
        double hasil = Math.PI * Math.pow(jariJari, 2) * tinggi;
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}