package controller;

import javafx.scene.paint.Color;
import model.Position;

public interface Drawer {
    Color getPenColor();

    void setPenColor(Color penColor);

    double getPenSize();

    void setPenSize(double penSize);

    void clear();

    void drawLine(Position a, Position b);
}
