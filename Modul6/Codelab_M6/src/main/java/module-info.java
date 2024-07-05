module org.example.codelab_m6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.codelab_m6 to javafx.fxml;
    exports org.example.codelab_m6;
    exports com.example.codelab_m6;
    opens com.example.codelab_m6 to javafx.fxml;
}