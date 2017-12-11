package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class GbCommand extends Command {
    private Position beforeReturning;
    public GbCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);
    }

    @Override
    public void execute() {
        beforeReturning = turtle.getPosition();
        turtle.setPosition(turtle.getInitialPosition());
    }

    @Override
    public void undo() {
        turtle.setPosition(beforeReturning);
    }
}
