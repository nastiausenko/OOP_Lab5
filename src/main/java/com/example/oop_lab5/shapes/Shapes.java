package com.example.oop_lab5.shapes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;


public abstract class Shapes {
    protected Scene scene;
    protected Pane root;
    public abstract void draw();

    public Shapes(Scene scene, Pane root) {
        this.scene = scene;
        this.root = root;
    }

    protected void dashed(Shape... shapes) {
        for (Shape currentShape: shapes) {
            currentShape.setStrokeLineCap(StrokeLineCap.BUTT);
            currentShape.setStrokeLineJoin(StrokeLineJoin.MITER);
            currentShape.getStrokeDashArray().addAll(5.0, 5.0);
        }
    }

    protected void clear(Shape... shapes) {
        for (Shape currentShape: shapes) {
            currentShape.getStrokeDashArray().clear();
        }
    }
}