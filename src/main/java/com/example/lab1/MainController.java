package com.example.lab1;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private ColorPicker colorPicker;

    private final List<Shape> shapeList = new ArrayList<>();
    private final ShapeFactory shapeFactory = new ShapeFactory();

    private Memento selectedShapeSnapshot;
    private double dragOffsetX;
    private double dragOffsetY;

    @FXML
    public void initialize() {
        drawingCanvas.setOnMousePressed(this::handleMousePressed);
        drawingCanvas.setOnMouseDragged(this::handleMouseDragged);
        drawingCanvas.setOnMouseReleased(this::handleMouseReleased);
    }

    @FXML
    private void handleAddCircle() {
        createAndDisplayShape(0, 90, 90);
    }

    @FXML
    private void handleAddRectangle() {
        createAndDisplayShape(4, 60, 80);
    }

    @FXML
    private void handleAddTriangle() {
        createAndDisplayShape(3, 90, 180);
    }

    private void createAndDisplayShape(int shapeType, double startX, double startY) {
        Shape shape = shapeFactory.buildShape(shapeType, colorPicker.getValue());
        shape.moveTo(startX, startY);
        shapeList.add(shape);
        redrawCanvas();
    }

    private void redrawCanvas() {
        GraphicsContext graphicsContext = drawingCanvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        for (Shape shape : shapeList) {
            shape.render(graphicsContext);
        }
    }

    private Shape findTopShape(double mouseX, double mouseY) {
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            Shape currentShape = shapeList.get(i);
            if (currentShape.containsPoint(mouseX, mouseY)) {
                return currentShape;
            }
        }
        return null;
    }

    private void handleMousePressed(MouseEvent event) {
        Shape clickedShape = findTopShape(event.getX(), event.getY());

        if (clickedShape == null) {
            selectedShapeSnapshot = null;
            return;
        }

        shapeList.remove(clickedShape);
        shapeList.add(clickedShape);

        selectedShapeSnapshot = new Memento(clickedShape);
        selectedShapeSnapshot.applySelectionStyle();

        dragOffsetX = event.getX() - clickedShape.x;
        dragOffsetY = event.getY() - clickedShape.y;

        redrawCanvas();
    }

    private void handleMouseDragged(MouseEvent event) {
        if (selectedShapeSnapshot == null) {
            return;
        }

        Shape activeShape = selectedShapeSnapshot.getTargetShape();
        activeShape.moveTo(event.getX() - dragOffsetX, event.getY() - dragOffsetY);

        redrawCanvas();
    }

    private void handleMouseReleased(MouseEvent event) {
        if (selectedShapeSnapshot == null) {
            return;
        }

        Shape activeShape = selectedShapeSnapshot.getTargetShape();
        activeShape.moveTo(event.getX() - dragOffsetX, event.getY() - dragOffsetY);

        selectedShapeSnapshot.restoreOriginalStyle();
        selectedShapeSnapshot = null;

        redrawCanvas();
    }
}