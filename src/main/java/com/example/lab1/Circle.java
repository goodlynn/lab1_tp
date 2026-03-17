package com.example.lab1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Circle extends Shape {

    private final double radius;

    public Circle(Color fillColor, double radius) {
        super(fillColor);
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    void render(GraphicsContext graphicsContext) {
        double diameter = radius * 2;

        applyStyle(graphicsContext);
        graphicsContext.fillOval(x, y, diameter, diameter);
        graphicsContext.strokeOval(x, y, diameter, diameter);
    }

    @Override
    boolean containsPoint(double mouseX, double mouseY) {
        double centerX = x + radius;
        double centerY = y + radius;

        double deltaX = mouseX - centerX;
        double deltaY = mouseY - centerY;

        return deltaX * deltaX + deltaY * deltaY <= radius * radius;
    }
}