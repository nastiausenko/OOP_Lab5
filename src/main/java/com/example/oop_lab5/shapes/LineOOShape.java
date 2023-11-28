package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;


public class LineOOShape extends LineShape implements Drawable {
    private Line currentLine;
    private Ellipse ellipse1, ellipse2;
    private final int radius = 10;
    private MyTable myTable;
    public LineOOShape(Scene scene, Pane root, MyTable myTable) {
        super(scene, root, myTable);
        this.myTable = myTable;
    }
    @Override
    public void draw() {
        root.setOnMousePressed(event -> {
            currentLine = new Line();
            show(event, root.getChildren(), currentLine);

            ellipse1 = new Ellipse();
            ellipse2 = new Ellipse();
            show(event, root.getChildren(), ellipse1, ellipse2);

            ellipse1.setRadiusX(radius);
            ellipse1.setRadiusY(radius);
            ellipse1.setFill(Color.WHITESMOKE);
        });

        root.setOnMouseDragged(event -> {
            if (currentLine != null) {
                dashed(ellipse1, ellipse2, currentLine);
                dragged(event, currentLine);

                ellipse2.setCenterX(currentLine.getEndX());
                ellipse2.setCenterY(currentLine.getEndY());
                ellipse2.setRadiusX(radius);
                ellipse2.setRadiusY(radius);
                ellipse2.setFill(Color.WHITESMOKE);
            }
        });

        root.setOnMouseReleased(event -> {
            clear(currentLine, ellipse1, ellipse2);
            myTable.addShape(new LineShape("LineOO",
                    currentLine.getStartX(),
                    currentLine.getStartY(),
                    currentLine.getEndX(),
                    currentLine.getEndY()));
            ellipse1 = null;
            ellipse2 = null;
            currentLine = null;
        });
    }
}
