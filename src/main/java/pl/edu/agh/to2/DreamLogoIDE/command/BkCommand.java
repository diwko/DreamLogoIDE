package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class BkCommand extends Command {
    private double distance;
    private Position oldPosition;

    public BkCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);

        try {
            distance = Double.parseDouble(arguments[1]);
            oldPosition = turtle.getPosition();
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect argument. Argument must be a number.", 0);
        }
    }

    @Override
    public void execute() throws IllegalStateException {
        turtle.move(-distance);
        shapeDrawer.drawLine(oldPosition, turtle.getPosition());
    }

    @Override
    public void undo() {
        turtle.setPosition(oldPosition);
        shapeDrawer.undoDrawing();
    }
}
