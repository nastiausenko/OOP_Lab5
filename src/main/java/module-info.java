module com.example.oop_lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_lab5 to javafx.fxml;
    exports com.example.oop_lab5;
    exports com.example.oop_lab5.shapes;
    opens com.example.oop_lab5.shapes to javafx.fxml;
}