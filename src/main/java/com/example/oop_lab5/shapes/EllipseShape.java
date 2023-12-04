package com.example.oop_lab5.shapes;

import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class EllipseShape extends Shapes {
    private Ellipse currentEllipse;
    public EllipseShape(Scene scene, Pane root, MyTable myTable) {
        super(scene, root);
        this.myTable = myTable;
    }

    public EllipseShape(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        super(shapeName, x1, y1, x2, y2);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(this::handle);

        root.setOnMouseDragged(event -> dragged(event, currentEllipse));

        root.setOnMouseReleased(event -> {
            clear(currentEllipse);
            currentEllipse.setFill(Color.LIGHTGRAY);
            myTable.addShape(new EllipseShape("Ellipse",
                    currentEllipse.getCenterX(),
                    currentEllipse.getCenterY(),
                    currentEllipse.getRadiusX(),
                    currentEllipse.getRadiusY()));
            currentEllipse = null;
        });
    }

    @Override
    public void display(Double x1, Double y1, Double x2, Double y2, Pane drawingArea) {
        currentEllipse = new Ellipse(x1, y1, x2, y2);
        currentEllipse.setFill(Color.LIGHTGRAY);
        currentEllipse.setStrokeWidth(1.5);
        currentEllipse.setStroke(Color.BLACK);
        drawingArea.getChildren().add(currentEllipse);
    }


    private void handle(MouseEvent event) {
        currentEllipse = new Ellipse();
        x1 = event.getX();
        x2 = event.getX();
        y1 = event.getY();
        y2 = event.getY();
        show(x1, y1, 0.0, 0.0, root.getChildren(), currentEllipse);
    }

    private void dragged(MouseEvent event, Ellipse currentEllipse) {
        if (currentEllipse != null) {
            dashed(currentEllipse);
            currentEllipse.setRadiusX(event.getX() - currentEllipse.getCenterX());
            currentEllipse.setRadiusY(event.getY() - currentEllipse.getCenterY());
        }
    }
}

