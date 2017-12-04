package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import model.Position;
import model.Turtle;

public class TurtleDrawingController {
    private Turtle turtle;
    private Drawer drawer;

    public TurtleDrawingController(Turtle turtle, Drawer drawer) {
        this.turtle = turtle;
        this.drawer = drawer;

        drawer.setPenColor(turtle.getPenColor());
        drawer.setPenSize(turtle.getPenSize());
        turtle.positionProperty().addListener(positionChangeListener());
        turtle.penColorProperty().addListener(penColorChangeListener());
        turtle.penSizeProperty().addListener(penSizeChangeListener());
    }

    private ChangeListener<Position> positionChangeListener() {
        return new ChangeListener<Position>() {
            @Override
            public void changed(ObservableValue<? extends Position> observable, Position oldValue, Position newValue) {
                if (turtle.isPenDown())
                    drawer.drawLine(oldValue, newValue);
            }
        };
    }

    private ChangeListener<Color> penColorChangeListener() {
        return new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                drawer.setPenColor(newValue);
            }
        };
    }

    private ChangeListener<Number> penSizeChangeListener() {
        return new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                drawer.setPenSize((double) newValue);
            }
        };
    }
}
