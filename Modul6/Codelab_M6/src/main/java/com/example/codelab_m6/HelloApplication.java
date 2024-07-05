package com.example.codelab_m6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        Label usernameLabel = new Label("  Username:");
        usernameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("  Password:");
        passwordLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        PasswordField passwordField = new PasswordField();

        Label errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: red;");
        errorMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 15));


        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("admin") && password.equals("admin")) {
                newWelcomeScene(primaryStage);
            } else {
                errorMessage.setText("Username / Password salah");
            }
        });


        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(errorMessage, 1, 3);

        Scene loginScene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void newWelcomeScene(Stage stage) {
        GridPane NewGrid = new GridPane();
        NewGrid.setAlignment(Pos.CENTER);
        Label NewLabel = new Label("Login Berhasil!");
        NewLabel.setFont(Font.font("Impact", FontWeight.NORMAL, 15));
        NewGrid.add(NewLabel, 0, 0);
        Label SecondLabel = new Label("Hallo Nadzar");
        SecondLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        NewGrid.add(SecondLabel, 0, 1);
        Scene welcomeScene = new Scene(NewGrid, 300, 200);
        stage.setScene(welcomeScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}