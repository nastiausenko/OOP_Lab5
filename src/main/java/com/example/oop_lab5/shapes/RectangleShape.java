package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleShape extends Shapes implements Drawable {
    private Rectangle frontRectangle;

    public RectangleShape(Scene scene, Pane root, MyTable myTable) {
        super(scene, root);
        this.myTable = myTable;
    }

    public RectangleShape(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        super(shapeName, x1, y1, x2, y2);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(this::handle);

        root.setOnMouseDragged(event -> dragged(event, frontRectangle));

        root.setOnMouseReleased(event -> {
                clear(frontRectangle);
                myTable.addShape(new RectangleShape("Rectangle",
                        frontRectangle.getX(),
                        frontRectangle.getY(),
                        frontRectangle.getWidth(),
                        frontRectangle.getHeight()));
                frontRectangle = null;
        });
    }

    @Override
    public void display(Double x1, Double y1, Double x2, Double y2, Pane drawingArea) {
        frontRectangle = new Rectangle(x1, y1, x2, y2);
        frontRectangle.setFill(null);
        frontRectangle.setStrokeWidth(1.5);
        frontRectangle.setStroke(Color.BLACK);
        drawingArea.getChildren().add(frontRectangle);
    }

    protected void dragged(MouseEvent event, Rectangle currentRectangle) {
        if (currentRectangle !=null) {
            dashed(currentRectangle);
            currentRectangle.setWidth(event.getX() - currentRectangle.getX());
            currentRectangle.setHeight(event.getY() - currentRectangle.getY());
        }
    }

    private void handle(MouseEvent event) {
        frontRectangle = new Rectangle();
        x1 = event.getX();
        x2 = 0.0;
        y1 = event.getY();
        y2 = 0.0;
        show(x1, y1, 0.0, 0.0, root.getChildren(), frontRectangle);
    }
}
