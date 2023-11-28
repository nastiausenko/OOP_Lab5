package com.example.oop_lab5.shapes;

import com.example.oop_lab5.interfaces.Drawable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleShape extends Shapes implements Drawable {
    private Rectangle currentRectangle = new Rectangle();

    public RectangleShape(Scene scene, Pane root) {
        super(scene, root);
    }

    @Override
    public void draw() {
        root.setOnMousePressed(this::handle);

        root.setOnMouseDragged(event -> dragged(event, currentRectangle));

        root.setOnMouseReleased(event -> {
                clear(currentRectangle);
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
