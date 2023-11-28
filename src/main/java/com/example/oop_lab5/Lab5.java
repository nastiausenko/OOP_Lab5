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

        setupShapeSelection(rectangle, btnRectangle, shapes,
                () -> shapeEditor.startEditor(new RectangleShape(scene, drawingArea, myTable)));
        setupShapeSelection(line, btnLine, shapes,
                () -> shapeEditor.startEditor(new LineShape(scene, drawingArea, myTable)));
        setupShapeSelection(point, btnPoint, shapes,
                () -> shapeEditor.startEditor(new PointShape(scene, drawingArea, myTable)));
        setupShapeSelection(ellipse, btnEllipse, shapes,
                () -> shapeEditor.startEditor(new EllipseShape(scene, drawingArea, myTable)));
        setupShapeSelection(cube, btnCube, shapes,
                () -> shapeEditor.startEditor(new CubeShape(scene, drawingArea, myTable)));
        setupShapeSelection(lineOO, btnLineOO, shapes,
                () -> shapeEditor.startEditor(new LineOOShape(scene, drawingArea, myTable)));

        VBox menuAndToolbar = new VBox(menuBar, toolBar);
        layout.setTop(menuAndToolbar);

        stage.setOnCloseRequest(event -> myTable.close());
        stage.setScene(scene);
        stage.setTitle("Lab5");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void selection(CheckMenuItem selectedItem, Menu shapesMenu) {
        selectedItem.setSelected(true);

        for (MenuItem item : shapesMenu.getItems()) {
            if (item instanceof CheckMenuItem) {
                CheckMenuItem checkMenuItem = (CheckMenuItem) item;
                if (checkMenuItem != selectedItem) {
                    checkMenuItem.setSelected(false);
                }
            }
        }
    }

    private void setupShapeSelection(CheckMenuItem menuItem, Button button, Menu shapesMenu, Runnable action) {
        menuItem.setOnAction(actionEvent -> {
            action.run();
            selection(menuItem, shapesMenu);
        });

        button.setOnAction(actionEvent -> {
            action.run();
            selection(menuItem, shapesMenu);
        });
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
}