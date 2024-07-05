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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Admin extends User {

    private List<Book> bookList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();

    public Admin(String name) {
        super(name);
    }

    public Admin() {
        super();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookList);
    }
    private Scene adminScene;

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    public Scene createAdminScene(Stage primaryStage, LibrarySystem librarySystem) {
        Button inputBookButton = new Button("Tambah Buku");
        Button displayBooksButton = new Button("Tampilkan daftar Buku");
        Button inputStudentButton = new Button("Tambah Siswa");
        Button displayStudentsButton = new Button("Tampilkan daftar Siswa");
        Button exitButton = new Button("Keluar");

        double buttonWidth = 200;
        inputBookButton.setPrefWidth(buttonWidth);
        displayBooksButton.setPrefWidth(buttonWidth);
        inputStudentButton.setPrefWidth(buttonWidth);
        displayStudentsButton.setPrefWidth(buttonWidth);
        exitButton.setPrefWidth(buttonWidth);

        inputBookButton.setOnAction(e -> primaryStage.setScene(createInputBookScene(primaryStage)));
        displayBooksButton.setOnAction(e -> primaryStage.setScene(createDisplayBooksScene(primaryStage, librarySystem, true)));
        inputStudentButton.setOnAction(e -> primaryStage.setScene(createInputStudentScene(primaryStage)));
        displayStudentsButton.setOnAction(e -> primaryStage.setScene(createDisplayStudentsScene(primaryStage)));
        exitButton.setOnAction(e -> primaryStage.setScene(librarySystem.initialScene));

        VBox adminLayout = new VBox(10, inputBookButton, displayBooksButton, inputStudentButton, displayStudentsButton, exitButton);
        adminLayout.setAlignment(Pos.CENTER);

        VBox borderBox = new VBox(adminLayout);
        borderBox.setAlignment(Pos.CENTER);
        borderBox.setStyle("-fx-border-color: black; -fx-padding: 20;");
        borderBox.setPrefWidth(250);
        borderBox.setMaxWidth(250);
        borderBox.setPadding(new Insets(20));

        Label adminMenuText = new Label("Menu Admin");
        adminMenuText.setFont(Font.font("Impact", FontWeight.BOLD, 24));
        adminMenuText.setStyle("-fx-text-fill: FF204E;");

        VBox mainLayout = new VBox(5, adminMenuText, borderBox);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        adminScene = new Scene(mainLayout, SCENE_WIDTH, SCENE_HEIGHT);
        return adminScene;
    }

    private Scene createInputBookScene(Stage primaryStage) {
        Label titleLabel = new Label("Masukkan judul :");
        TextField titleField = new TextField();
        titleField.setMaxWidth(300);

        Label authorLabel = new Label("Masukkan penulis :");
        TextField authorField = new TextField();
        authorField.setMaxWidth(300);

        Label categoryLabel = new Label("Kategori :");
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.setMaxWidth(300);
        categoryComboBox.getItems().addAll("History", "Story", "Text");

        Label stockLabel = new Label("Masukkan jumlah stok :");
        TextField stockField = new TextField();
        stockField.setMaxWidth(300);

        Button addButton = new Button("Tambah buku");
        Button backButton = new Button("Keluar");
        Label confirmationLabel = new Label();

        addButton.setOnAction(e -> {
            String id = generateBookID();
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryComboBox.getValue();
            int stock;
            try {
                stock = Integer.parseInt(stockField.getText());
            } catch (NumberFormatException ex) {
                confirmationLabel.setText("Tolong masukkan angka yang valid untuk stok.");
                confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                confirmationLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            bookList.add(new Book(id, title, author, category, stock));
            confirmationLabel.setText("Buku berhasil ditambahkan dengan ID : " + id);
            confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            confirmationLabel.setStyle("-fx-text-fill: green;");
        });

        backButton.setOnAction(e -> primaryStage.setScene(adminScene));

        VBox inputBookLayout = new VBox(10, titleLabel, titleField, authorLabel, authorField, categoryLabel, categoryComboBox, stockLabel, stockField, addButton, backButton, confirmationLabel);
        inputBookLayout.setAlignment(Pos.CENTER);
        return new Scene(inputBookLayout, SCENE_WIDTH, SCENE_HEIGHT);
    }

    public Scene createDisplayBooksScene(Stage primaryStage, LibrarySystem librarySystem, boolean isAdmin) {
        VBox displayLayout = new VBox(10);
        displayLayout.setAlignment(Pos.CENTER);

        for (Book book : bookList) {
            Label bookLabel = new Label("ID : " + book.getId() +
                    " | Judul buku : " + book.getTitle() +
                    " | Penulis : " + book.getAuthor() +
                    " | Kategori: " + book.getCategory() +
                    " | Stok : " + book.getStock());
            displayLayout.getChildren().add(bookLabel);
        }

        Button backButton = new Button("Keluar");
        backButton.setOnAction(e -> {
            if (isAdmin) {
                primaryStage.setScene(adminScene);
            } else {
                primaryStage.setScene(librarySystem.studentScene);
            }
        });

        displayLayout.getChildren().add(backButton);
        return new Scene(displayLayout, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private Scene createInputStudentScene(Stage primaryStage) {
        Label nameLabel = new Label("Masukkan nama :");
        TextField nameField = new TextField();
        nameField.setMaxWidth(300);

        Label nimLabel = new Label("Masukkan NIM (15 digits) :");
        TextField nimField = new TextField();
        nimField.setMaxWidth(300);

        Label passwordLabel = new Label("Masukkan password :");
        TextField passwordField = new TextField();
        passwordField.setMaxWidth(300);

        Label facultyLabel = new Label("Masukkan fakultas :");
        TextField facultyField = new TextField();
        facultyField.setMaxWidth(300);

        Label programLabel = new Label("Masukkan program studi :");
        TextField programField = new TextField();
        programField.setMaxWidth(300);

        Button addButton = new Button("Tambah siswa");
        Button backButton = new Button("Keluar");
        Label confirmationLabel = new Label();

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String password = passwordField.getText();
            String faculty = facultyField.getText();
            String program = programField.getText();
            if (nim.length() == 15) {
                studentList.add(new Student(name, nim, password, faculty, program));
                confirmationLabel.setText("Penambahan siswa berhasil.");
                confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                confirmationLabel.setStyle("-fx-text-fill: green;");
            } else {
                confirmationLabel.setText("NIM harus 15 digit!");
                confirmationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                confirmationLabel.setStyle("-fx-text-fill: red;");
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(adminScene));

        VBox inputStudentLayout = new VBox(10, nameLabel, nameField, nimLabel, nimField, passwordLabel, passwordField, facultyLabel, facultyField, programLabel, programField, addButton, backButton, confirmationLabel);
        inputStudentLayout.setAlignment(Pos.CENTER);
        return new Scene(inputStudentLayout, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private Scene createDisplayStudentsScene(Stage primaryStage) {
        VBox displayLayout = new VBox(10);
        displayLayout.setAlignment(Pos.CENTER);

        for (Student student : studentList) {
            Label studentLabel = new Label("Nama : " + student.getName() + " | NIM : " + student.getNim() + " | Password : " + student.getPassword() + " | Fakultas: " + student.getFaculty() + " | Program studi : " + student.getProgram());
            displayLayout.getChildren().add(studentLabel);
        }

        Button backButton = new Button("Keluar");
        backButton.setOnAction(e -> primaryStage.setScene(adminScene));

        displayLayout.getChildren().add(backButton);
        return new Scene(displayLayout, SCENE_WIDTH, SCENE_HEIGHT);
    }


    private String generateBookID() {
        String uniqueID = UUID.randomUUID().toString();
        String bookId = uniqueID.replaceAll("-", "").toLowerCase();
        return String.format("%s-%s-%s", bookId.substring(0, 3), bookId.substring(3, 6), bookId.substring(6, 9));
    }

    public Book getBookById(String id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }


    public Student getStudentByNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }

    public boolean verifyStudent(String nim, String password) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim) && student.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
