package com.example.oop_lab5.shapes;

import com.example.oop_lab5.table.MyTable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;


public abstract class Shapes {
    protected Scene scene;
    protected Pane root;
    protected String shapeName;
    protected MyTable myTable;
    protected Double x1;
    protected Double y1;
    protected Double x2;
    protected Double y2;
    public abstract void draw();
    public abstract void show(Double x1, Double y1, Double x2, Double y2, Pane drawingArea);

    protected Shapes(Scene scene, Pane root) {
        this.scene = scene;
        this.root = root;
    }

    protected Shapes(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        this.shapeName = shapeName;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public String getShapeName() {
        return shapeName;
    }
    public Double getX1() {
        return x1;
    }
    public Double getY1() {
        return y1;
    }
    public Double getX2() {
        return x2;
    }
    public Double getY2() {
        return y2;
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