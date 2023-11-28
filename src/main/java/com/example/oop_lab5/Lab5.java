package com.example.oop_lab5;

import com.example.oop_lab5.shape_editor.MyEditor;
import com.example.oop_lab5.shapes.*;
import com.example.oop_lab5.table.MyTable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.Objects;

public class Lab5 extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();
        Pane drawingArea = new Pane();
        layout.setCenter(drawingArea);
        Scene scene = new Scene(layout, 700, 500);

        MyEditor shapeEditor = MyEditor.getInstance();
        MyTable myTable = new MyTable();

        MenuBar menuBar = new MenuBar();
        drawingArea.setMaxHeight(scene.getHeight() - menuBar.getHeight());
        Menu file = new Menu("File");
        Menu shapes = new Menu("Shapes");
        Menu help = new Menu("Help");

        menuBar.getMenus().addAll(file, shapes, help);

        MenuItem table = new MenuItem("Table");
        file.getItems().addAll(table);

        table.setOnAction(actionEvent -> {
            myTable.createTable();
        });

        CheckMenuItem point = new CheckMenuItem("Point");
        CheckMenuItem line = new CheckMenuItem("Line");
        CheckMenuItem ellipse = new CheckMenuItem("Ellipse");
        CheckMenuItem rectangle = new CheckMenuItem("Rectangle");
        CheckMenuItem cube = new CheckMenuItem("Cube");
        CheckMenuItem lineOO = new CheckMenuItem("LineOO");


        shapes.getItems().addAll(point, line, ellipse, rectangle, cube, lineOO);

        ToolBar toolBar = new ToolBar();
        Button btnPoint = createToolbarButton("/images/point.png", "Point");
        Button btnLine = createToolbarButton("/images/line.png", "Line");
        Button btnEllipse = createToolbarButton("/images/ellipse.png", "Ellipse");
        Button btnRectangle = createToolbarButton("/images/rectangle.png", "Rectangle");
        Button btnCube = createToolbarButton("/images/cube.png", "Cube");
        Button btnLineOO = createToolbarButton("/images/lineOO.png", "LineOO");


        toolBar.getItems().addAll(btnPoint, btnLine, btnEllipse, btnRectangle, btnCube, btnLineOO);

        VBox menuAndToolbar = new VBox(menuBar, toolBar);
        layout.setTop(menuAndToolbar);

        rectangle.setOnAction(actionEvent -> {
            shapeEditor.startEditor(new RectangleShape(scene, drawingArea));
            selection(rectangle, point, ellipse, line, lineOO, cube);
        });
        line.setOnAction(actionEvent -> {
            shapeEditor.startEditor(new LineShape(scene, drawingArea, myTable));
            selection(line, point, ellipse, rectangle, lineOO, cube);
        });
        point.setOnAction(actionEvent -> {
            shapeEditor.startEditor(new PointShape(scene, drawingArea));
            selection(point, line, ellipse, rectangle, lineOO, cube);
        });
        ellipse.setOnAction(actionEvent ->{
            shapeEditor.startEditor(new EllipseShape(scene, drawingArea, myTable));
            selection(ellipse, line, point, rectangle, lineOO, cube);
        });
        cube.setOnAction(actionEvent ->{
            shapeEditor.startEditor(new CubeShape(scene, drawingArea));
            selection(cube, ellipse, line, point, rectangle, lineOO);
        });
        lineOO.setOnAction(actionEvent ->{
            shapeEditor.startEditor(new LineOOShape(scene, drawingArea, myTable));
            selection(lineOO, ellipse, line, point, rectangle, cube);
        });

        stage.setOnCloseRequest(event -> myTable.close());

        btnRectangle.setOnAction(actionEvent -> shapeEditor.startEditor(new RectangleShape(scene, drawingArea)));
        btnLine.setOnAction(actionEvent -> shapeEditor.startEditor(new LineShape(scene, drawingArea, myTable)));
        btnPoint.setOnAction(actionEvent -> shapeEditor.startEditor(new PointShape(scene, drawingArea)));
        btnEllipse.setOnAction(actionEvent -> shapeEditor.startEditor(new EllipseShape(scene, drawingArea, myTable)));
        btnCube.setOnAction(actionEvent -> shapeEditor.startEditor(new CubeShape(scene, drawingArea)));
        btnLineOO.setOnAction(actionEvent -> shapeEditor.startEditor(new LineOOShape(scene, drawingArea, myTable)));

        stage.setScene(scene);
        stage.setTitle("Lab5");
        stage.show();
    }

    private void selection(CheckMenuItem selectedItem, CheckMenuItem... others) {
        selectedItem.setSelected(true);
        for (CheckMenuItem item : others) {
            item.setSelected(false);
        }
    }

    private Button createToolbarButton(String imagePath, String tooltipText) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageView = new ImageView(image);

        Button button = new Button();
        button.setGraphic(imageView);

        Tooltip tooltip = new Tooltip(tooltipText);
        Tooltip.install(button, tooltip);

        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}