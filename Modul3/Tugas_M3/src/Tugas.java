import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int stock;
    private int borrowedCount = 0;
    private LocalDate borrowedDate;
    private int durationDays;

    public Book(String bookId, String title, String author, String category, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    // Getters and Setters
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public void borrowBook(int durationDays) {
        if (stock > 0) {
            stock--;
            borrowedCount++;
            setBorrowedDate(LocalDate.now());
            setDurationDays(durationDays);
            System.out.println("Durasi peminjaman buku adalah " + durationDays + " hari.");
        } else {
            System.out.println("No more copies available for this book.");
        }
    }

    public void returnBook() {
        if (borrowedCount > 0) {
            stock++;
            borrowedCount--;
            borrowedDate = null;
            durationDays = 0;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("No copies borrowed for this book.");
        }
    }
}

class HistoryBook extends Book {
    public HistoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
}

class TextBook extends Book {
    public TextBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
}

class StoryBook extends Book {
    public StoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
}

// User class
class User {
    public String name;
    protected ArrayList<Book> bookList;

    public User(String name) {
        this.name = name;
        bookList = new ArrayList<>();
    }

    public User(String name, ArrayList<Book> bookList) {
        this.name = name;
        this.bookList = bookList;
    }

    public void displayBooks() {
        System.out.println("\n===== Available Books =====");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||", "Book ID", "Title", "Author", "Category", "Stock");
        System.out.println("\n================================================================================");
        for (Book book : bookList) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
            System.out.println();
        }
    }

    public void addBook(String bookId, String title, String author, String category, int stock, String bookType) {
        Book newBook;
        switch (bookType) {
            case "History":
                newBook = new HistoryBook(bookId, title, author, category, stock);
                break;
            case "Text":
                newBook = new TextBook(bookId, title, author, category, stock);
                break;
            case "Story":
                newBook = new StoryBook(bookId, title, author, category, stock);
                break;
            default:
                System.out.println("Invalid book type.");
                return;
        }
        bookList.add(newBook);
        System.out.println("Book added successfully.");
    }
}

class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    public void addStudent(ArrayList<Student> userStudent) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter NIM: ");
        String nim = scanner.nextLine();
        if (nim.length() != 15) {
            System.out.println("Invalid NIM. NIM must be 15 digits.");
            addStudent(userStudent);
            return;
        }
        System.out.print("Enter Faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter Program: ");
        String program = scanner.nextLine();

        Student newStudent = new Student(name, nim, faculty, program);
        userStudent.add(newStudent);
        System.out.println("Student added successfully.");
    }

    public void inputBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Category (History, Text, or Story): ");
        String category = scanner.nextLine();
        System.out.print("Enter Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String bookType = category;

        super.addBook(bookId, title, author, category, stock, bookType);
    }
}

class Student extends User {
    public String nim;
    public String faculty;
    public String program;

    public Student(String name, String nim, String faculty, String program) {
        super(name);
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public void borrowBook(ArrayList<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID to borrow: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter the duration in days (e.g., 7, 14, 30): ");
        int durationDays = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                found = true;
                book.borrowBook(durationDays);
                break;
            }
        }
        if (!found) {
            System.out.println("Book with the given ID not found.");
        }
    }

    public void returnBook(ArrayList<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine();

        boolean found = false;
        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                found = true;
                book.returnBook();
                break;
            }
        }
        if (!found) {
            System.out.println("Book with the given ID not found or not borrowed by you.");
        }
    }

    public void showBorrowedBook(ArrayList<Book> bookList) {
        System.out.println("\n===== Borrowed Books =====");
        boolean hasBorrowedBooks = false;
        for (Book book : bookList) {
            if (book.getBorrowedCount() > 0) {
                hasBorrowedBooks = true;
                System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-10s || %-10s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getBorrowedDate(), "Due in " + book.getDurationDays() + " days");
            }
        }
        if (!hasBorrowedBooks) {
            System.out.println("You haven't borrowed any books yet.");
        }
    }
}

public class Tugas {
    private ArrayList<Student> userStudent;
    private Scanner scanner;
    static Scanner inputuser = new Scanner(System.in);

    static String adminusername = "admin", adminpassword = "admin";
    private Admin admin;
    private User user;

    public Tugas() {
        userStudent = new ArrayList<>();
        scanner = new Scanner(System.in);
        admin = new Admin("Admin");
        user = new User("User", admin.bookList);


    }

    public void menu() {
        System.out.println("\n===== Library System =====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.print("Choose an option: ");
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
                System.out.println("Invalid choice. Try again.");
                menu();
        }
    }

    private void inputNim() {
        System.out.print("Enter your NIM: ");
        String nim = scanner.nextLine();
        if (checkNim(nim)) {
            Student currentStudent = getUserByNim(nim);
            menuStudent(currentStudent);
        } else {
            System.out.println("Invalid NIM. Please try again.");
            inputNim();
        }
    }

    private boolean checkNim(String nim) {
        // Check if nim exists in userStudent
        for (Student student : userStudent) {
            if (student.nim.equals(nim)) {
                return true;
            }
        }
        return false;
    }

    private Student getUserByNim(String nim) {
        for (Student student : userStudent) {
            if (student.nim.equals(nim)) {
                return student;
            }
        }
        return null;
    }

    static void loginadmin() {
        int adminloop;
        do {
            System.out.println("\n==== Login ====");
            System.out.print("Enter Username: \n");
            String username = inputuser.nextLine();

            System.out.print("Enter Password: \n");
            String password = inputuser.nextLine();

            if (username.equals(adminusername) && password.equals(adminpassword)) {
                System.out.println("==== Login Successful ====\n");
                adminloop = 1;
            } else {
                System.out.println("==== Admin not found ====");
                adminloop = 0;
            }
        } while (adminloop == 0);
    }

    private void menuAdmin() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Input Book");
            System.out.println("2. Display Books");
            System.out.println("3. Add Student");
            System.out.println("4. Display Registered Students");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.inputBook();
                    break;
                case 2:
                    admin.displayBooks();
                    break;
                case 3:
                    admin.addStudent(userStudent);
                    break;
                case 4:
                    displayStudent();
                    break;
                case 5:
                    menu();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void menuStudent(Student currentStudent) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Student Dashboard =====");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Borrowed Books");
            System.out.println("4. Return Book");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    user.displayBooks();
                    break;
                case 2:
                    currentStudent.borrowBook(user.bookList);
                    break;
                case 3:
                    currentStudent.showBorrowedBook(user.bookList);
                    break;
                case 4:
                    currentStudent.returnBook(user.bookList);
                    break;
                case 5:
                    menu();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void displayStudent() {
        System.out.println("\n===== Registered Students =====");
        for (Student student : userStudent) {
            System.out.println("Name: " + student.name);
            System.out.println("NIM: " + student.nim);
            System.out.println("Faculty: " + student.faculty);
            System.out.println("Program: " + student.program);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tugas app = new Tugas();
        app.menu();
    }
}