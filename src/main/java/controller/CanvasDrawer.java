package controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Position;

public class CanvasDrawer implements Drawer {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Color penColor;
    private double penSize;

    public CanvasDrawer(Canvas canvas) {
        this.canvas = canvas;
        graphicsContext = canvas.getGraphicsContext2D();
    }

    @Override
    public Color getPenColor() {
        return penColor;
    }

    @Override
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
        graphicsContext.setStroke(penColor);
    }

    @Override
    public double getPenSize() {
        return penSize;
    }

    @Override
    public void setPenSize(double penSize) {
        this.penSize = penSize;
        graphicsContext.setLineWidth(penSize);
    }

    @Override
    public void clear() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void drawLine(Position a, Position b) {
        graphicsContext.setStroke(penColor);

        graphicsContext.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }
}
