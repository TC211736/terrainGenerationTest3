module com.example.terraingenerationtest3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.terraingenerationtest3 to javafx.fxml;
    exports com.example.terraingenerationtest3;
}