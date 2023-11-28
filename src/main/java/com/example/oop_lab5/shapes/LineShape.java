package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class LineShape extends Shapes implements Drawable {

    private Line currentLine;
    MyTable myTable;

    public LineShape(Scene scene, Pane root, MyTable myTable) {
        super(scene, root);
        this.myTable = myTable;
    }
    public LineShape(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        super(shapeName, x1, y1, x2, y2);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(this::handle);

        root.setOnMouseDragged(event -> dragged(event, currentLine));

        root.setOnMouseReleased(event -> {
            myTable.addShape(new LineShape("Line",
                    currentLine.getStartX(),
                    currentLine.getStartY(),
                    currentLine.getEndX(),
                    currentLine.getEndY()));
            currentLine = null;
        });
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
