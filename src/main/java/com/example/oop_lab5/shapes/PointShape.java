package com.example.oop_lab5.shapes;

import com.example.oop_lab5.table.MyTable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PointShape extends Shapes {
    private Ellipse currentPoint;

    public PointShape(Scene scene, Pane root, MyTable myTable) {
        super(scene, root);
        this.myTable = myTable;
    }

    public PointShape(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        super(shapeName, x1, y1, x2, y2);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(this::handle);

        root.setOnMouseReleased(event -> {
            myTable.addShape(new PointShape("Point",
                    event.getX(),
                    event.getY(),
                    currentPoint.getRadiusX(),
                    currentPoint.getRadiusY()));
            currentPoint = null;
        });
    }

    @Override
    public void drawing(Double x1, Double y1, Double x2, Double y2, Pane drawingArea) {
        currentPoint = new Ellipse(x1, y1, x2, y2);
        currentPoint.setFill(Color.BLACK);
        drawingArea.getChildren().add(currentPoint);
    }

    private void show(MouseEvent event, ObservableList<Node> children, Ellipse... ellipses) {
        for (Ellipse point: ellipses) {
            point.setCenterX(event.getX());
            point.setCenterY(event.getY());
            point.setRadiusX(5);
            point.setRadiusY(5);
            point.setFill(Color.BLACK);
            children.add(point);
        }
    }
    private void handle(MouseEvent event) {
        currentPoint = new Ellipse();
        show(event, root.getChildren(), currentPoint);
    }
}
