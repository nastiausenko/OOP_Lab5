package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleShape extends Shapes implements Drawable {
    private Rectangle currentRectangle;
    private MyTable myTable;

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

        root.setOnMouseDragged(event -> dragged(event, currentRectangle));

        root.setOnMouseReleased(event -> {
                clear(currentRectangle);
                myTable.addShape(new RectangleShape("Rectangle",
                        currentRectangle.getX(),
                        currentRectangle.getY(),
                        //need to calculate endX, endY
                        currentRectangle.getWidth(),
                        currentRectangle.getHeight()));
                currentRectangle = null;
        });
    }
    protected void dragged(MouseEvent event, Rectangle currentRectangle) {
        if (currentRectangle !=null) {
            dashed(currentRectangle);
            currentRectangle.setWidth(event.getX() - currentRectangle.getX());
            currentRectangle.setHeight(event.getY() - currentRectangle.getY());
        }
    }

    private void handle(MouseEvent event) {
        currentRectangle = new Rectangle();
        show(event, root.getChildren(), currentRectangle);
    }
}
