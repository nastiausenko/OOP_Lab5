package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;



public class CubeShape extends RectangleShape implements Drawable {
    private Rectangle frontRectangle, backRectangle;
    private Line line1, line2, line3, line4;
    private double width, height;
    private double frontX, frontY;
    private double backX, backY;

    public CubeShape(Scene scene, Pane root) {
        super(scene, root);
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

            show(event, root.getChildren(), frontRectangle, backRectangle);
            show(event, root.getChildren(), line1, line2, line3, line4);

            backRectangle.setX(event.getX() + 50);
            backRectangle.setY(event.getY() - 50);
        });

        root.setOnMouseDragged(event -> {
            if (frontRectangle != null) {
                if (event.getX() > frontRectangle.getX() && event.getY() > frontRectangle.getY()) {
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
            }
        });

        root.setOnMouseReleased(event -> {
            clear(frontRectangle, backRectangle, line1, line2, line3, line4);
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
}
