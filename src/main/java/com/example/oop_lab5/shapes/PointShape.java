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
    private MyTable myTable;

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
