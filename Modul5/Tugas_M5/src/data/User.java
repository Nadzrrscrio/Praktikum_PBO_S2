package data;
import java.util.Scanner;
public class User {
    protected String name;
    protected static Scanner scanner = new Scanner(System.in);

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}