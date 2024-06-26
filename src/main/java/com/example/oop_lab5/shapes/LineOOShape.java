package com.example.oop_lab5.shapes;

import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;


public class LineOOShape extends LineShape {
    private Line currentLine;
    private Ellipse ellipse1;
    private Ellipse ellipse2;
    private static final Double RADIUS = 10.0;
    public LineOOShape(Scene scene, Pane root, MyTable myTable) {
        super(scene, root, myTable);
        this.myTable = myTable;
    }

    public LineOOShape(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        super(shapeName, x1, y1, x2, y2);
    }
    @Override
    public void draw() {
        root.setOnMousePressed(event -> {
            currentLine = new Line();

            x1 = event.getX();
            x2 = event.getX();
            y1 = event.getY();
            y2 = event.getY();

            show(x1, y1, x2, y2, root.getChildren(), currentLine);

            ellipse1 = new Ellipse();
            ellipse2 = new Ellipse();
            show(x1, y1, RADIUS, RADIUS, root.getChildren(), ellipse1, ellipse2);

            ellipse1.setRadiusX(RADIUS);
            ellipse1.setRadiusY(RADIUS);
            ellipse1.setFill(Color.WHITESMOKE);
        });

        root.setOnMouseDragged(event -> {
            if (currentLine != null) {
                dashed(ellipse1, ellipse2, currentLine);
                dragged(event, currentLine);

                ellipse2.setCenterX(currentLine.getEndX());
                ellipse2.setCenterY(currentLine.getEndY());
                ellipse2.setRadiusX(RADIUS);
                ellipse2.setRadiusY(RADIUS);
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

    @Override
    public void display(Double x1, Double y1, Double x2, Double y2, Pane drawingPane) {
        currentLine = new Line();
        ellipse1 = new Ellipse();
        ellipse2 = new Ellipse();

        show(x1, y1, x2, y2, drawingPane.getChildren(), currentLine);
        show(x1, y1, RADIUS, RADIUS, drawingPane.getChildren(), ellipse1);
        ellipse1.setFill(Color.WHITESMOKE);
        show(x2, y2, RADIUS, RADIUS, drawingPane.getChildren(), ellipse2);
        ellipse2.setFill(Color.WHITESMOKE);
    }
}
