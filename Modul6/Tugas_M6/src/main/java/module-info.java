module org.example.enam {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.enam to javafx.fxml;
    exports org.example.enam;
    exports main;
    opens main to javafx.fxml;
}