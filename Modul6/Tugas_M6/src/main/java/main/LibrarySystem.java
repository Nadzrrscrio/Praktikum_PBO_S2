package main;

import data.Admin;
import data.Student;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LibrarySystem extends Application {

    public Scene initialScene;
    private Scene loginScene;
    private Scene adminScene;
    public Scene studentScene;
    private Admin admin = new Admin();
    private Map<String, Map<String, Integer>> studentBorrowedBooks = new HashMap<>();
    private Student currentStudent;

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library System");

        initialScene = createInitialScene(primaryStage);
        loginScene = createLoginScene(primaryStage);
        adminScene = admin.createAdminScene(primaryStage, this);


        primaryStage.setScene(initialScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    private Scene createInitialScene(Stage primaryStage) {
        Label titleLabel = new Label("iBook");
        titleLabel.setFont(Font.font("Impact", FontWeight.NORMAL, 50));
        titleLabel.setStyle("-fx-text-fill: FF204E;");

        Image ibook = new Image("file:src/main/java/image/iBook.png");
        ImageView ibookview = new ImageView(ibook);

        //size image settings
        ibookview.setFitWidth(300);
        ibookview.setFitHeight(300);

        //position image settings
        ibookview.setTranslateY(-100 );


        Button adminButton = new Button("Admin");
        Button studentButton = new Button("Student");


        adminButton.setOnAction(e -> primaryStage.setScene(loginScene));
        studentButton.setOnAction(e -> primaryStage.setScene(createStudentLoginScene(primaryStage)));

        VBox initialLayout = new VBox(5, titleLabel, adminButton, studentButton);
        StackPane stackPane = new StackPane(ibookview,initialLayout);

        initialLayout.setAlignment(Pos.CENTER);
        return new Scene(stackPane, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private Scene createLoginScene(Stage primaryStage) {
        Label userLabel = new Label("Masukkan Username :");
        TextField userTextField = new TextField();
        Label passLabel = new Label("Masukkan Password :");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        Label errorLabel = new Label();

        loginButton.setOnAction(e -> {
            if (userTextField.getText().equals("admin") && passField.getText().equals("admin")) {
                primaryStage.setScene(adminScene);
            } else {
                errorLabel.setText("Error: Invalid Credentials");
                errorLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userTextField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.add(loginButton, 1, 2);
        gridPane.add(errorLabel, 1, 3);

        VBox borderBox = new VBox(gridPane);
        borderBox.setAlignment(Pos.CENTER);
        borderBox.setStyle("-fx-border-color: black; -fx-padding: 20;");
        borderBox.setPrefWidth(400);

        borderBox.setMaxWidth(400);
        borderBox.setPadding(new Insets(20));

        VBox mainLayout = new VBox(borderBox);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        return new Scene(mainLayout, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private Scene createStudentLoginScene(Stage primaryStage) {
        Label nimLabel = new Label("Masukkan NIM :");
        TextField nimField = new TextField();

        Label passLabel = new Label("Masukkan Password :");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");
        Label errorLabel = new Label();

        loginButton.setOnAction(e -> {
            String nim = nimField.getText();
            String password = passField.getText();
            if (admin.verifyStudent(nim, password)) {
                currentStudent = admin.getStudentByNim(nim);
                studentScene = currentStudent.createStudentScene(primaryStage, this, admin, studentBorrowedBooks.getOrDefault(nim, new HashMap<>()));
                primaryStage.setScene(studentScene);
            } else {
                errorLabel.setText("NIM atau password salah, Coba Lagi!");
                errorLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(nimLabel, 0, 0);
        gridPane.add(nimField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.add(loginButton, 1, 2);
        gridPane.add(errorLabel, 1, 3);

        VBox borderBox = new VBox(gridPane);
        borderBox.setAlignment(Pos.CENTER);
        borderBox.setStyle("-fx-border-color: black; -fx-padding: 20;");
        borderBox.setPrefWidth(400);

        borderBox.setMaxWidth(400);
        borderBox.setPadding(new Insets(20));

        VBox mainLayout = new VBox(borderBox);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        return new Scene(mainLayout, SCENE_WIDTH, SCENE_HEIGHT);
    }

    public void updateStudentBorrowedBooks(String nim, Map<String, Integer> borrowedBooks) {
        studentBorrowedBooks.put(nim, borrowedBooks);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
