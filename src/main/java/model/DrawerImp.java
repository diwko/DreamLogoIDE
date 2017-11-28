package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawerImp implements Drawer {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Color color = Color.BLACK;

    public DrawerImp(Canvas canvas) {
        this.canvas = canvas;
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isCorrectPosition(Position position) {
        return (position.getX() <= canvas.getWidth()) && (position.getY() <= canvas.getHeight() &&
                (position.getX()) >= 0 && (position.getY() >= 0));
    }

    @Override
    public void drawLine(Position a, Position b) throws IllegalArgumentException {
        if (!isCorrectPosition(a) || !isCorrectPosition(b))
            throw new IllegalArgumentException();

        graphicsContext.beginPath();
        graphicsContext.moveTo(a.getX(), a.getY());
        graphicsContext.lineTo(b.getX(), b.getY());
        graphicsContext.stroke();
    }

    @Override
    public void changeColor(Color color) {
        graphicsContext.setStroke(color);
    }


}
