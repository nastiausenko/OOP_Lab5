package com.example.oop_lab5.shape_editor;

import com.example.oop_lab5.shapes.Shapes;

public class MyEditor {
    private static final MyEditor myEditor = new MyEditor();

    public static MyEditor getInstance() {
        return myEditor;
    }

    public void startEditor(Shapes shape) {
        shape.draw();
    }
}
