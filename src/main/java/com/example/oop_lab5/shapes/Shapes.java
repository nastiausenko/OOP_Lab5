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
    protected Double x1, y1, x2, y2;
    public abstract void draw();

    public Shapes(String shapeName, Double x1, Double y1, Double x2, Double y2) {
        this.shapeName = shapeName;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public Double getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }

    public Double getY1() {
        return y1;
    }

    public void setY1(Double y1) {
        this.y1 = y1;
    }

    public Double getX2() {
        return x2;
    }

    public void setX2(Double x2) {
        this.x2 = x2;
    }

    public Double getY2() {
        return y2;
    }

    public void setY2(Double y2) {
        this.y2 = y2;
    }

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