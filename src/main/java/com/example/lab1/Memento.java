package com.example.lab1;

import javafx.scene.paint.Color;

class Memento {

    private final Shape targetShape;
    private final Color originalBorderColor;
    private final double originalBorderWidth;

    public Memento(Shape targetShape) {
        this.targetShape = targetShape;
        this.originalBorderColor = targetShape.getBorderColor();
        this.originalBorderWidth = targetShape.getBorderWidth();
    }

    public void applySelectionStyle() {
        targetShape.setBorderColor(Color.CRIMSON);
        targetShape.setBorderWidth(3.0);
    }

    public void restoreOriginalStyle() {
        targetShape.setBorderColor(originalBorderColor);
        targetShape.setBorderWidth(originalBorderWidth);
    }

    public Shape getTargetShape() {
        return targetShape;
    }
}