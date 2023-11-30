module com.example.oop_lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_lab5 to javafx.fxml;
    exports com.example.oop_lab5;
    exports com.example.oop_lab5.shapes;
    exports com.example.oop_lab5.table;
    opens com.example.oop_lab5.shapes to javafx.fxml;
}