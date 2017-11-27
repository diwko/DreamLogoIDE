package model;

import javafx.scene.paint.Color;

public interface Drawer {
    boolean isCorrectPosition(Position position);

    void drawLine(Position a, Position b);

    void changeColor(Color color);
}
