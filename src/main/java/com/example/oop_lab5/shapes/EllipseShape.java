package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class EllipseShape extends Shapes implements Drawable {
    private Ellipse currentEllipse;

    public EllipseShape(Scene scene, Pane root) {
        super(scene, root);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(this::handle);

        root.setOnMouseDragged(event -> dragged(event, currentEllipse));

        root.setOnMouseReleased(event -> {
            clear(currentEllipse);
            currentEllipse.setFill(Color.LIGHTGRAY);
            currentEllipse = null;
        });
    }

    private void handle(MouseEvent event) {
        currentEllipse = new Ellipse();
        show(event, root.getChildren(), currentEllipse);
    }

    private void dragged(MouseEvent event, Ellipse currentEllipse) {
        if (currentEllipse != null) {
            dashed(currentEllipse);
            currentEllipse.setRadiusX(event.getX() - currentEllipse.getCenterX());
            currentEllipse.setRadiusY(event.getY() - currentEllipse.getCenterY());
        }
    }
}

