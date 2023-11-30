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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.util.Objects;

public class Lab5 extends Application {
    private MyTable myTable;
    private Pane drawingArea;
    
    public static final String POINT = "Point";
    public static final String RECTANGLE = "Rectangle";
    public static final String ELLIPSE = "Ellipse";
    public static final String LINE = "Line";
    public static final String CUBE = "Cube";
    public static final String LINEOO = "LineOO";



    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();
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
        //add save functionality
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save as...");
        MenuItem open = new MenuItem("Open");
        file.getItems().addAll(table, save, saveAs, open);

        table.setOnAction(actionEvent -> myTable.createTable());
        saveAs.setOnAction(actionEvent -> saveAs(stage));
        open.setOnAction(actionEvent -> open(stage));

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

    private void saveShapesToFile(File file) {
        try (ObjectOutputStream ignored = new ObjectOutputStream(new FileOutputStream(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Shapes shape : myTable.getShapes()) {
                String line = String.format("%s" +
                                " %.2f %.2f %.2f %.2f\n",
                        shape.getShapeName(),
                        shape.getX1(),
                        shape.getY1(),
                        shape.getX2(),
                        shape.getY2());
                writer.write(line);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private void loadShapesFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            drawingArea.getChildren().clear();
            while ((line = reader.readLine()) != null) {
                createShapeFromLine(line);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private void createShapeFromLine(String line) {
        String[] tokens = line.trim().split("\\s+");
        if (tokens.length <= 6) {
            String shapeName = tokens[0];
            double x1 = Double.parseDouble(tokens[1].replace(",", "."));
            double y1 = Double.parseDouble(tokens[2].replace(",", "."));
            double x2 = Double.parseDouble(tokens[3].replace(",", "."));
            double y2 = Double.parseDouble(tokens[4].replace(",", "."));

            Shapes shape;
            switch (shapeName.toLowerCase()) {
                case "point":
                    shape = new PointShape(POINT, x1, y1, x2, y2);
                    myTable.addShape(shape);
                    shape.drawing(x1, y1, x2, y2, drawingArea);
                    break;
                case "line":
                    shape = new LineShape(LINE, x1, y1, x2, y2);
                    myTable.addShape(shape);
                    shape.drawing(x1, y1, x2, y2, drawingArea);
                    break;
                case "ellipse":
                    shape = new EllipseShape(ELLIPSE, x1, y1, x2, y2);
                    myTable.addShape(shape);
                    shape.drawing(x1, y1, x2, y2, drawingArea);
                    break;
                case "rectangle":
                    shape = new RectangleShape(RECTANGLE, x1, y1, x2, y2);
                    myTable.addShape(shape);
                    shape.drawing(x1, y1, x2, y2, drawingArea);
                    break;
                case "cube":
                    shape = new CubeShape(CUBE, x1, y1, x2, y2);
                    myTable.addShape(shape);
                    shape.drawing(x1, y1, x2, y2, drawingArea);
                    break;
                case "lineoo":
                    shape = new LineOOShape(LINEOO, x1, y1, x2, y2);
                    myTable.addShape(shape);
                    shape.drawing(x1, y1, x2, y2, drawingArea);
                    break;
                default:
                    break;
            }
        }
    }

    private void saveAs(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        File file1 = fileChooser.showSaveDialog(stage);

        if (file1 != null) {
            saveShapesToFile(file1);
        }
    }

    private void open(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        File file2 = fileChooser.showOpenDialog(stage);

        if (file2 != null) {
            loadShapesFromFile(file2);
        }
    }
}