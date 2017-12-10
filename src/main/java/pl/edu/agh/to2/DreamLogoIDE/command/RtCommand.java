package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class RtCommand extends Command {
    private double angle;
    private Position oldPosition;

    public RtCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);

        try {
            angle = Double.parseDouble(arguments[1]);
            oldPosition = turtle.getPosition();
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect argument. Argument must be a number.", 0);
        }
    }

    @Override
    public void execute() {
        turtle.rotate(angle);
    }

    @Override
    public void undo() {
        turtle.setPosition(oldPosition);
    }
}
