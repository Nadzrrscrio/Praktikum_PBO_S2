package data;

import books.Book;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.LibrarySystem;

import java.util.Map;

public class Student extends User {
    private String name;
    private String nim;
    private String password;
    private String faculty;
    private String program;
    public Student(String name, String nim, String password, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.password = password;
        this.faculty = faculty;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgram() {
        return program;
    }

    public Scene createStudentScene(Stage primaryStage, LibrarySystem librarySystem, Admin admin, Map<String, Integer> borrowedBooks) {
        Button displayBooksButton = new Button("Tampilkan Daftar Buku");
        Button borrowBookButton = new Button("Pinjam Buku");
        Button displayBorrowedBooksButton = new Button("Tampilkan Buku Terpinjam");
        Button returnBookButton = new Button("Kembalikan Buku");
        Button exitButton = new Button("Keluar");

        double buttonWidth = 200;
        displayBooksButton.setPrefWidth(buttonWidth);
        borrowBookButton.setPrefWidth(buttonWidth);
        displayBorrowedBooksButton.setPrefWidth(buttonWidth);
        returnBookButton.setPrefWidth(buttonWidth);
        exitButton.setPrefWidth(buttonWidth);

        displayBooksButton.setOnAction(e -> primaryStage.setScene(admin.createDisplayBooksScene(primaryStage, librarySystem, false)));
        borrowBookButton.setOnAction(e -> primaryStage.setScene(createBorrowBookScene(primaryStage, librarySystem, admin, borrowedBooks)));
        displayBorrowedBooksButton.setOnAction(e -> primaryStage.setScene(createDisplayBorrowedBooksScene(primaryStage, librarySystem, admin, borrowedBooks)));
        returnBookButton.setOnAction(e -> primaryStage.setScene(createReturnBookScene(primaryStage, librarySystem, admin, borrowedBooks)));
        exitButton.setOnAction(e -> primaryStage.setScene(librarySystem.initialScene));

        VBox studentLayout = new VBox(10, displayBooksButton, borrowBookButton, displayBorrowedBooksButton, returnBookButton, exitButton);
        studentLayout.setAlignment(Pos.CENTER);

        VBox borderBox = new VBox(studentLayout);
        borderBox.setAlignment(Pos.CENTER);
        borderBox.setStyle("-fx-border-color: black; -fx-padding: 20;");
        borderBox.setPrefWidth(250);
        borderBox.setMaxWidth(250);
        borderBox.setPadding(new Insets(20));

        Label studentMenuText = new Label("Menu Mahasiswa"); // Changed this line
        studentMenuText.setFont(Font.font("Impact", FontWeight.BOLD, 24));
        studentMenuText.setStyle("-fx-text-fill: FF204E;");

        VBox mainLayout = new VBox(5, studentMenuText, borderBox); // Changed this line
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        return new Scene(mainLayout, 800, 600); // Changed this line
    }


    private Scene createBorrowBookScene(Stage primaryStage, LibrarySystem librarySystem, Admin admin, Map<String, Integer> borrowedBooks) {
        VBox borrowBookLayout = new VBox(10);
        borrowBookLayout.setAlignment(Pos.CENTER);

        // Display list of available books
        VBox displayBooksLayout = new VBox(10);
        displayBooksLayout.setAlignment(Pos.CENTER);
        for (Book book : admin.getAllBooks()) {
            Label bookLabel = new Label("ID : " + book.getId() +
                    " | Judul buku : " + book.getTitle() +
                    " | Penulis : " + book.getAuthor() +
                    " | Kategori: " + book.getCategory() +
                    " | Stok : " + book.getStock());
            displayBooksLayout.getChildren().add(bookLabel);
        }

        Label idLabel = new Label("Masukkan ID buku untuk meminjam :");
        TextField idField = new TextField();
        idField.setMaxWidth(300);

        Label durationLabel = new Label("Masukkan durasi peminjaman (max 14 hari):");
        TextField durationField = new TextField();
        durationField.setMaxWidth(300);

        Button borrowButton = new Button("Pinjam");
        Button backButton = new Button("Keluar");
        Label confirmationLabel = new Label();

        borrowButton.setOnAction(e -> {
            String id = idField.getText();
            int duration;
            try {
                duration = Integer.parseInt(durationField.getText());
                if (duration > 14) {
                    confirmationLabel.setText("Durasi peminjaman maksimal 14 hari.");
                    confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                    confirmationLabel.setStyle("-fx-text-fill: red;");
                    return;
                }
            } catch (NumberFormatException ex) {
                confirmationLabel.setText("Tolong masukkan angka yang valid untuk durasi!.");
                confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                confirmationLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            Book book = admin.getBookById(id);
            if (book != null) {
                if (book.getStock() > 0) {
                    borrowedBooks.put(id, duration);
                    book.setStock(book.getStock() - 1); // Mengurangi stok
                    confirmationLabel.setText("Peminjaman buku berhasil.");
                    confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                    confirmationLabel.setStyle("-fx-text-fill: green;");
                } else {
                    confirmationLabel.setText("Tidak ada stok yang tersedia pada buku ini.");
                    confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                    confirmationLabel.setStyle("-fx-text-fill: red;");
                }
            } else {
                confirmationLabel.setText("Buku dengan ID tersebut tidak ditemukan.");
                confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                confirmationLabel.setStyle("-fx-text-fill: red;");
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(librarySystem.studentScene));

        borrowBookLayout.getChildren().addAll(displayBooksLayout, idLabel, idField, durationLabel, durationField, borrowButton, backButton, confirmationLabel);
        return new Scene(borrowBookLayout, 800, 600);
    }


    private Scene createDisplayBorrowedBooksScene(Stage primaryStage, LibrarySystem librarySystem, Admin admin, Map<String, Integer> borrowedBooks) {
        VBox displayLayout = new VBox(10);
        displayLayout.setAlignment(Pos.CENTER);

        for (Map.Entry<String, Integer> entry : borrowedBooks.entrySet()) {
            Book book = admin.getBookById(entry.getKey());
            if (book != null) {
                Label bookLabel = new Label("ID buku : " + book.getId() +
                        " | Judul buku : " + book.getTitle() +
                        " | Penulis : " + book.getAuthor() +
                        " | Kategori : " + book.getCategory() +
                        " | Sisa durasi peminjaman : " + entry.getValue());
                displayLayout.getChildren().add(bookLabel);
            }
        }

        Button backButton = new Button("Keluar");
        backButton.setOnAction(e -> primaryStage.setScene(librarySystem.studentScene));

        displayLayout.getChildren().add(backButton);
        return new Scene(displayLayout, 800, 600);
    }

    private Scene createReturnBookScene(Stage primaryStage, LibrarySystem librarySystem, Admin admin, Map<String, Integer> borrowedBooks) {
        VBox returnBookLayout = new VBox(10);
        returnBookLayout.setAlignment(Pos.CENTER);

        for (Map.Entry<String, Integer> entry : borrowedBooks.entrySet()) {
            Book book = admin.getBookById(entry.getKey());
            if (book != null) {
                Label bookLabel = new Label("ID buku : " + book.getId() +
                        " | Judul buku : " + book.getTitle() +
                        " | Penulis : " + book.getAuthor() +
                        " | Kategori : " + book.getCategory() +
                        " | Stok : " + book.getStock() +
                        " | Durasi peminjaman: " + entry.getValue());
                returnBookLayout.getChildren().add(bookLabel);
            }
        }

        Label idLabel = new Label("Masukkan ID buku untuk mengembalikan :");
        TextField idField = new TextField();
        idField.setMaxWidth(300);

        Button returnButton = new Button("Kembalikan");
        Button backButton = new Button("Keluar");
        Label confirmationLabel = new Label();

        returnButton.setOnAction(e -> {
            String id = idField.getText();
            if (borrowedBooks.containsKey(id)) {
                borrowedBooks.remove(id);
                Book book = admin.getBookById(id);
                if (book != null) {
                    book.setStock(book.getStock() + 1); // Menambah stok
                }
                confirmationLabel.setText("Pengembalian buku berhasil.");
                confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                confirmationLabel.setStyle("-fx-text-fill: green;");
            } else {
                confirmationLabel.setText("Buku dengan ID tersebut tidak ditemukan.");
                confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                confirmationLabel.setStyle("-fx-text-fill: red;");
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(librarySystem.studentScene));

        returnBookLayout.getChildren().addAll(idLabel, idField, returnButton, backButton, confirmationLabel);
        return new Scene(returnBookLayout, 800, 600);
    }


}