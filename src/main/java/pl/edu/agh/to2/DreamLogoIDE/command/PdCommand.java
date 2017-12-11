package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class PdCommand extends Command {
    private boolean penDown;

    public PdCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);
        penDown = turtle.isPenDown();
    }

    @Override
    public void execute() {
        turtle.setPenDown(true);
    }

    @Override
    public void undo() {
        if (penDown)
            turtle.setPenDown(false);
    }
}
