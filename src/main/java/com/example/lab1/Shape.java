package com.example.lab1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract class Shape {

    protected double x;
    protected double y;
    protected Color fillColor;

    private Color borderColor = Color.BLACK;
    private double borderWidth = 1.0;

    protected Shape(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void moveTo(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public double getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(double borderWidth) {
        this.borderWidth = borderWidth;
    }

    protected void applyStyle(GraphicsContext graphicsContext) {
        graphicsContext.setFill(fillColor);
        graphicsContext.setStroke(borderColor);
        graphicsContext.setLineWidth(borderWidth);
    }

    abstract double calculateArea();

    abstract void render(GraphicsContext graphicsContext);

    abstract boolean containsPoint(double mouseX, double mouseY);
}