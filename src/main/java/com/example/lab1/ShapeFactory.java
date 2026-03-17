package com.example.lab1;

import javafx.scene.paint.Color;

class ShapeFactory {

    public Shape buildShape(int shapeType, Color selectedColor) {
        return switch (shapeType) {
            case 0 -> new Circle(selectedColor, 40);
            case 3 -> new Triangle(selectedColor, 110, 80);
            case 4 -> new Rectangle(selectedColor, 140, 90);
            default -> throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        };
    }
}