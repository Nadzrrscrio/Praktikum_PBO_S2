package books;
import java.time.LocalDate;

public class Book {
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

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public void borrowBook() {
        if (stock > 0) {
            stock--;
            borrowedCount++;
            setBorrowedDate(LocalDate.now());
            setDurationDays(14); // Defaultnya 14 hari
            System.out.println("Buku berhasil dipinjam selama 14 hari.");
        } else {
            System.out.println("Tidak ada stok yang tersedia dari buku ini.");
        }
    }

    public void borrowBook(int durationDays) {
        if (stock > 0) {
            stock--;
            borrowedCount++;
            setBorrowedDate(LocalDate.now());
            setDurationDays(durationDays);
            System.out.println("Buku berhasil dipinjam selama " + durationDays + " hari.");
        } else {
            System.out.println("Tidak ada stok yang tersedia dari buku ini.");
        }
    }

    public void returnBook() {
        if (borrowedCount > 0) {
            stock++;
            borrowedCount--;
            borrowedDate = null;
            durationDays = 0;
            System.out.println("Pengembalian buku berhasil.");
        } else {
            System.out.println("Buku dengan ID tersebut tidak dipinjam olehmu.");
        }
    }
}