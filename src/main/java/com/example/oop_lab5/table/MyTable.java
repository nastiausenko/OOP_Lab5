package com.example.oop_lab5.table;

import com.example.oop_lab5.shapes.Shapes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class MyTable extends Stage {
    ObservableList<Shapes> shapes = FXCollections.observableArrayList();
    public void createTable() {
        BorderPane layout = new BorderPane();
        Scene tableScene = new Scene(layout, 301, 200);

        TableView<Shapes> table = new TableView<>();

        TableColumn<Shapes, String> shapeName = new TableColumn<>("Shape");
        shapeName.setMaxWidth(75);
        shapeName.setCellValueFactory(new PropertyValueFactory<>("shapeName"));

        TableColumn<Shapes, Double> x1 = new TableColumn<>("X1");
        x1.setMaxWidth(56);
        x1.setCellValueFactory(new PropertyValueFactory<>("x1"));

        TableColumn<Shapes, Double> y1 = new TableColumn<>("Y1");
        y1.setMaxWidth(56);
        y1.setCellValueFactory(new PropertyValueFactory<>("y1"));

        TableColumn<Shapes, Double> x2 = new TableColumn<>("X2");
        x2.setMaxWidth(56);
        x2.setCellValueFactory(new PropertyValueFactory<>("x2"));

        TableColumn<Shapes, Double> y2 = new TableColumn<>("Y2");
        y2.setMaxWidth(56);
        y2.setCellValueFactory(new PropertyValueFactory<>("y2"));

        table.setItems(getShapes());
        List<TableColumn<Shapes, ?>> columns = Arrays.asList(shapeName, x1, y1, x2, y2);
        table.getColumns().addAll(columns);

        layout.setCenter(table);

        this.setAlwaysOnTop(true);
        this.setScene(tableScene);
        this.setTitle("Table");
        this.show();
    }

    public ObservableList<Shapes> getShapes() {
        return shapes;
    }

    public void addShape(Shapes shape) {
        shapes.add(shape);
    }
}
