package pl.edu.agh.to2.DreamLogoIDE.controller;

import javafx.scene.paint.Color;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;

public interface Drawer {
    Color getPenColor();

    void setPenColor(Color penColor);

    double getPenSize();

    void setPenSize(double penSize);

    void clear();

    void drawLine(Position a, Position b);
}
