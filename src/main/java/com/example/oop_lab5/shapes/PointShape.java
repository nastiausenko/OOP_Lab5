package com.example.oop_lab5.shapes;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PointShape extends Shapes {
    private Ellipse currentPoint;

    public PointShape(Scene scene, Pane root) {
        super(scene, root);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(event -> root.setOnMousePressed(this::handle));

        root.setOnMouseReleased(event -> currentPoint = null);
    }

    private void show(MouseEvent event, ObservableList<Node> children, Ellipse... ellipses) {
        for (Ellipse currentPoint: ellipses) {
            currentPoint.setCenterX(event.getX());
            currentPoint.setCenterY(event.getY());
            currentPoint.setRadiusX(5);
            currentPoint.setRadiusY(5);
            currentPoint.setFill(Color.BLACK);
            children.add(currentPoint);
        }
    }
    private void handle(MouseEvent event) {
        currentPoint = new Ellipse();
        show(event, root.getChildren(), currentPoint);
    }
}
