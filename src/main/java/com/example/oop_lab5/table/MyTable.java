package com.example.oop_lab5.table;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyTable extends Stage {
    public void createTable() {
        BorderPane layout = new BorderPane();
        Scene tableScene = new Scene(layout, 300, 200);

        Label tableLabel = new Label();
        layout.setCenter(tableLabel);

        this.setScene(tableScene);
        this.setTitle("Table");
        this.show();
    }
}
