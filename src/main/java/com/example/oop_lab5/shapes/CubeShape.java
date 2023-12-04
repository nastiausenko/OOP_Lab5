package com.example.oop_lab5.shapes;

import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;



public class CubeShape extends RectangleShape {
    private Rectangle frontRectangle;
    private Rectangle backRectangle;
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private double width;
    private double height;
    private double frontX;
    private double frontY;
    private double backX;
    private double backY;

    public CubeShape(Scene scene, Pane root, MyTable myTable) {
        super(scene, root, myTable);
        this.myTable = myTable;
    }

    public CubeShape(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        super(shapeName, x1, y1, x2, y2);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(event -> {
            frontRectangle = new Rectangle();
            backRectangle = new Rectangle();
            line1 = new Line();
            line2 = new Line();
            line3 = new Line();
            line4 = new Line();

            x1 = event.getX();
            x2 = event.getX();
            y1 = event.getY();
            y2 = event.getY();

            show(x1, y1, 0.0, 0.0, root.getChildren(), frontRectangle, backRectangle);
            show(x1, y1, x2, y2, root.getChildren(), line1, line2, line3, line4);

            backRectangle.setX(event.getX() + 50);
            backRectangle.setY(event.getY() - 50);
        });

        root.setOnMouseDragged(event -> {
            if (frontRectangle != null && (event.getX() > frontRectangle.getX() && event.getY() > frontRectangle.getY())) {
                    width = frontRectangle.getWidth();
                    height = frontRectangle.getHeight();
                    frontX = frontRectangle.getX();
                    frontY = frontRectangle.getY();
                    backX = backRectangle.getX();
                    backY = backRectangle.getY();

                    dragged(event, frontRectangle);

                    dashed(backRectangle, line1, line2, line3, line4);

                    backRectangle.setX(frontX + 50);
                    backRectangle.setY(frontY - 50);
                    backRectangle.setWidth(width);
                    backRectangle.setHeight(height);

                    setCoords(line1, frontX, frontY, backX, backY);
                    setCoords(line2, frontX, frontY + height, backX, backY + height);
                    setCoords(line3, frontX + width, frontY, backX + width, backY);
                    setCoords(line4, frontX + width, frontY + height, backX + width,backY + height);
            }
        });

        root.setOnMouseReleased(event -> {
            clear(frontRectangle, backRectangle, line1, line2, line3, line4);
            myTable.addShape(new CubeShape("Cube",
                    frontRectangle.getX(),
                    frontRectangle.getY(),
                    frontRectangle.getWidth(),
                    frontRectangle.getHeight()));
            backRectangle = null;
            frontRectangle = null;
            line1 = null;
            line3 = null;
            line2 = null;
            line4 = null;
        });
    }

    private void setCoords(Line line, double startX, double startY, double endX, double endY) {
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
    }

    @Override
    public void display(Double x1, Double y1, Double x2, Double y2, Pane drawingArea) {
        frontRectangle = new Rectangle();
        backRectangle = new Rectangle();
        line1 = new Line();
        line2 = new Line();
        line3 = new Line();
        line4 = new Line();

        show(x1, y1, x2, y2, drawingArea.getChildren(), frontRectangle);
        show(x1+50, y1-50, x2, y2, drawingArea.getChildren(), backRectangle);

        show(x1, y1, x1+50, y1-50, drawingArea.getChildren(), line1);
        show(x1, y1+y2, x1+50, y1-50+y2, drawingArea.getChildren(), line2);
        show(x1+x2, y1, x1+50+x2, y1-50, drawingArea.getChildren(), line3);
        show(x1+x2, y1+y2, x1+50+x2, y1-50+y2, drawingArea.getChildren(), line4);
    }
}
