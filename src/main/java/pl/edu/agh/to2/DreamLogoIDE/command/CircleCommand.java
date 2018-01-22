package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class CircleCommand extends Command {
    private double radius;

    public CircleCommand(String[] arguments) throws ParseException {
        super(arguments);

        try {
            radius = Double.parseDouble(arguments[1]) / 2;
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect argument. Argument must be a number.", 0);
        }
    }

    @Override
    public void execute(Turtle turtle, ShapeDrawer shapeDrawer) {
        shapeDrawer.drawCircle(turtle.getPosition(), radius);
    }

    @Override
    public void undo(Turtle turtle, ShapeDrawer shapeDrawer) {
        shapeDrawer.undoDrawing();
    }
}