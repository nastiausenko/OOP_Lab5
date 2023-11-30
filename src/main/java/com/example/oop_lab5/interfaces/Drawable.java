package com.example.oop_lab5.interfaces;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public interface Drawable {
    default void show(Double x1, Double y1, Double x2, Double y2, ObservableList<Node> children, Ellipse... ellipses){
        for (Ellipse currentEllipse: ellipses) {
            currentEllipse.setCenterX(x1);
            currentEllipse.setCenterY(y1);
            currentEllipse.setRadiusX(0);
            currentEllipse.setRadiusY(0);
            currentEllipse.setStroke(Color.BLACK);
            currentEllipse.setStrokeWidth(1.5);
            currentEllipse.setFill(Color.LIGHTGRAY);
            children.add(currentEllipse);
        }
    }

    default void show(Double x1, Double y1, Double x2, Double y2, ObservableList<Node> children, Rectangle... rectangles) {
        for (Rectangle currentRectangle: rectangles) {
            currentRectangle.setX(x1);
            currentRectangle.setY(y1);
            currentRectangle.setWidth(x2);
            currentRectangle.setHeight(y2);
            currentRectangle.setStroke(Color.BLACK);
            currentRectangle.setStrokeWidth(1.5);
            currentRectangle.setFill(null);
            children.add(currentRectangle);
        }
    }

    default void show(Double x1, Double y1, Double x2, Double y2, ObservableList<Node> children, Line... lines){
        for (Line currentLine: lines) {
            currentLine.setStartX(x1);
            currentLine.setStartY(y1);
            currentLine.setEndX(x2);
            currentLine.setEndY(y2);
            currentLine.setStroke(Color.BLACK);
            currentLine.setStrokeWidth(1.5);
            children.add(currentLine);
        }
    }
}
