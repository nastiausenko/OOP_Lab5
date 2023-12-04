package com.example.oop_lab5;

import com.example.oop_lab5.shape_editor.MyEditor;
import com.example.oop_lab5.shapes.*;
import com.example.oop_lab5.table.FileHandler;
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
    public static final String POINT = "Point";
    public static final String RECTANGLE = "Rectangle";
    public static final String ELLIPSE = "Ellipse";
    public static final String LINE = "Line";
    public static final String CUBE = "Cube";
    public static final String LINEOO = "LineOO";


    @Override
    public void start(Stage stage) {
        Pane drawingArea;
        MyTable myTable;
        BorderPane layout = new BorderPane();
        FileHandler fileHandler = new FileHandler();
        Scene scene = new Scene(layout, 700, 500);
        drawingArea = new Pane();
        layout.setCenter(drawingArea);

        MyEditor shapeEditor = MyEditor.getInstance();
        myTable = new MyTable();

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu shapes = new Menu("Shapes");
        Menu help = new Menu("Help");

        menuBar.getMenus().addAll(file, shapes, help);

        MenuItem table = new MenuItem("Table");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save as...");
        MenuItem open = new MenuItem("Open");
        MenuItem create = new MenuItem("Create");
        file.getItems().addAll(table, separator, save, saveAs, open, create);

        table.setOnAction(actionEvent -> myTable.createTable());
        save.setOnAction(actionEvent -> fileHandler.save(stage, myTable));
        saveAs.setOnAction(actionEvent -> fileHandler.saveAs(stage, myTable));
        open.setOnAction(actionEvent -> fileHandler.open(stage, drawingArea, myTable));
        create.setOnAction(actionEvent -> drawingArea.getChildren().clear());

        CheckMenuItem point = new CheckMenuItem(POINT);
        CheckMenuItem line = new CheckMenuItem(LINE);
        CheckMenuItem ellipse = new CheckMenuItem(ELLIPSE);
        CheckMenuItem rectangle = new CheckMenuItem(RECTANGLE);
        CheckMenuItem cube = new CheckMenuItem(CUBE);
        CheckMenuItem lineOO = new CheckMenuItem(LINEOO);

        shapes.getItems().addAll(point, line, ellipse, rectangle, cube, lineOO);

        ToolBar toolBar = new ToolBar();
        Button btnPoint = createToolbarButton("/images/point.png", POINT);
        Button btnLine = createToolbarButton("/images/line.png", LINE);
        Button btnEllipse = createToolbarButton("/images/ellipse.png", ELLIPSE);
        Button btnRectangle = createToolbarButton("/images/rectangle.png", RECTANGLE);
        Button btnCube = createToolbarButton("/images/cube.png", CUBE);
        Button btnLineOO = createToolbarButton("/images/lineOO.png", LINEOO);

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