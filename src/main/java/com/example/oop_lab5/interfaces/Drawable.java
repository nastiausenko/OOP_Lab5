package com.example.oop_lab5.interfaces;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public interface Drawable {
    default void show(MouseEvent event, ObservableList<Node> children, Ellipse... ellipses){
        for (Ellipse currentEllipse: ellipses) {
            currentEllipse.setCenterX(event.getX());
            currentEllipse.setCenterY(event.getY());
            currentEllipse.setRadiusX(0);
            currentEllipse.setRadiusY(0);
            currentEllipse.setStroke(Color.BLACK);
            currentEllipse.setStrokeWidth(1.5);
            currentEllipse.setFill(null);
            children.add(currentEllipse);
        }
    }

    default void show(MouseEvent event, ObservableList<Node> children, Rectangle... rectangles) {
        for (Rectangle currentRectangle: rectangles) {
            currentRectangle.setX(event.getX());
            currentRectangle.setY(event.getY());
            currentRectangle.setWidth(0);
            currentRectangle.setHeight(0);
            currentRectangle.setStroke(Color.BLACK);
            currentRectangle.setStrokeWidth(1.5);
            currentRectangle.setFill(null);
            children.add(currentRectangle);
        }
    }

    default void show(MouseEvent event, ObservableList<Node> children, Line... lines){
        for (Line currentLine: lines) {
            currentLine.setStartX(event.getX());
            currentLine.setStartY(event.getY());
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
            currentLine.setStroke(Color.BLACK);
            currentLine.setStrokeWidth(1.5);
            children.add(currentLine);
        }
    }
}
