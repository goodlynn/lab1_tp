package com.example.lab1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Triangle extends Shape {

    private final double base;
    private final double height;

    public Triangle(Color fillColor, double base, double height) {
        super(fillColor);
        this.base = base;
        this.height = height;
    }

    @Override
    double calculateArea() {
        return (base * height) / 2.0;
    }

    @Override
    void render(GraphicsContext graphicsContext) {
        double[] xPoints = {x, x + base, x};
        double[] yPoints = {y, y, y - height};

        applyStyle(graphicsContext);
        graphicsContext.fillPolygon(xPoints, yPoints, 3);
        graphicsContext.strokePolygon(xPoints, yPoints, 3);
    }

    @Override
    boolean containsPoint(double mouseX, double mouseY) {
        double x1 = x;
        double y1 = y;

        double x2 = x + base;
        double y2 = y;

        double x3 = x;
        double y3 = y - height;

        double denominator = ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3));

        double a = ((y2 - y3) * (mouseX - x3) + (x3 - x2) * (mouseY - y3)) / denominator;
        double b = ((y3 - y1) * (mouseX - x3) + (x1 - x3) * (mouseY - y3)) / denominator;
        double c = 1 - a - b;

        return a >= 0 && b >= 0 && c >= 0;
    }
}