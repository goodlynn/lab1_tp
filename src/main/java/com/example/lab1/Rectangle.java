package com.example.lab1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Rectangle extends Shape {

    private final double width;
    private final double height;

    public Rectangle(Color fillColor, double width, double height) {
        super(fillColor);
        this.width = width;
        this.height = height;
    }

    @Override
    double calculateArea() {
        return width * height;
    }

    @Override
    void render(GraphicsContext graphicsContext) {
        applyStyle(graphicsContext);
        graphicsContext.fillRect(x, y, width, height);
        graphicsContext.strokeRect(x, y, width, height);
    }

    @Override
    boolean containsPoint(double mouseX, double mouseY) {
        return mouseX >= x
                && mouseX <= x + width
                && mouseY >= y
                && mouseY <= y + height;
    }
}