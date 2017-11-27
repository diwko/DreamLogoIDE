package model;

import javafx.scene.paint.Color;

public class Turtle {
    private Position position;
    private boolean isPenDown = true;
    private Drawer drawer;

    public Turtle(Position position, Drawer drawer) {
        this.position = position;
        this.drawer = drawer;
    }

    public Turtle(Position position, boolean isPenDown, Drawer drawer) {
        this.position = position;
        this.isPenDown = isPenDown;
        this.drawer = drawer;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isPenDown() {
        return isPenDown;
    }

    public void setPenDown(boolean penDown) {
        isPenDown = penDown;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    public void move(double distance) throws IllegalArgumentException {
        Position newPosition = position.addDistance(distance);
        if (!drawer.isCorrectPosition(newPosition))
            throw new IllegalArgumentException("New position is outside the area");

        if (isPenDown)
            drawer.drawLine(position, newPosition);

        position = newPosition;
    }

    public void rotate(double angle) {
        position = position.addRotation(angle);
    }

    public void penDown() {
        isPenDown = true;
    }

    public void penUp() {
        isPenDown = false;
    }

    public void setPenColor(Color color) {
        drawer.changeColor(color);
    }


}
