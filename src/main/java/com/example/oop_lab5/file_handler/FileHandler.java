package com.example.oop_lab5.file_handler;

import com.example.oop_lab5.shapes.*;
import com.example.oop_lab5.table.MyTable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import static com.example.oop_lab5.Lab5.*;

public class FileHandler {
    private File lastSavedFile;

    public void saveAs(Stage stage, MyTable myTable) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        File file1 = fileChooser.showSaveDialog(stage);

        if (file1 != null) {
            saveShapesToFile(file1, myTable);
        }
    }

    public void save(Stage stage, MyTable myTable) {
        if (lastSavedFile != null) {
            saveShapesToFile(lastSavedFile, myTable);
        } else {
            saveAs(stage, myTable);
        }
    }

    public void open(Stage stage, Pane drawingArea, MyTable myTable) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        File file2 = fileChooser.showOpenDialog(stage);

        if (file2 != null) {
            loadShapesFromFile(file2, drawingArea, myTable);
        }
    }

    private void saveShapesToFile(File file, MyTable myTable) {
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
            lastSavedFile = file;
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private void loadShapesFromFile(File file, Pane drawingArea, MyTable myTable) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            drawingArea.getChildren().clear();
            myTable.clearTable();
            while ((line = reader.readLine()) != null) {
                createShapeFromLine(line, myTable, drawingArea);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private void createShapeFromLine(String line, MyTable myTable, Pane drawingArea) {
        String[] tokens = line.trim().split("\\s+");
        if (tokens.length <= 6) {
            String shapeName = tokens[0];
            double x1 = Double.parseDouble(tokens[1].replace(",", "."));
            double y1 = Double.parseDouble(tokens[2].replace(",", "."));
            double x2 = Double.parseDouble(tokens[3].replace(",", "."));
            double y2 = Double.parseDouble(tokens[4].replace(",", "."));

            Shapes shape = createShape(shapeName, x1, y1, x2, y2);
            if (shape != null) {
                myTable.addShape(shape);
                shape.display(x1, y1, x2, y2, drawingArea);
            }
        }
    }

    private Shapes createShape(String shapeName, double x1, double y1, double x2, double y2) {
        switch (shapeName.toLowerCase()) {
            case "point":
                return new PointShape(POINT, x1, y1, x2, y2);
            case "line":
                return new LineShape(LINE, x1, y1, x2, y2);
            case "ellipse":
                return new EllipseShape(ELLIPSE, x1, y1, x2, y2);
            case "rectangle":
                return new RectangleShape(RECTANGLE, x1, y1, x2, y2);
            case "cube":
                return new CubeShape(CUBE, x1, y1, x2, y2);
            case "lineoo":
                return new LineOOShape(LINEOO, x1, y1, x2, y2);
            default:
                showErrorAlert();
                return null;
        }
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Undefined shape");
        alert.setHeaderText(null);
        alert.setContentText("The specified shape is not recognized.");
        alert.showAndWait();
    }
}
