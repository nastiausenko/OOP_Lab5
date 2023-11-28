package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class LineShape extends Shapes implements Drawable {

    private Line currentLine;
    public LineShape(Scene scene, Pane root) {
        super(scene, root);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(this::handle);

        root.setOnMouseDragged(event -> dragged(event, currentLine));

        root.setOnMouseReleased(event -> currentLine = null);
    }

    private void handle(MouseEvent event) {
        currentLine = new Line();
        show(event, root.getChildren(), currentLine);
    }

    protected void dragged(MouseEvent event, Line currentLine) {
        if (currentLine != null) {
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
        }
    }
}
