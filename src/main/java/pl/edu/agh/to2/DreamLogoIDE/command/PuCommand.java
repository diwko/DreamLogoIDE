package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class PuCommand extends Command {
    private boolean penDown;
    public PuCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);
        penDown = turtle.isPenDown();
    }

    @Override
    public void execute() {
        turtle.setPenDown(false);
    }

    @Override
    public void undo() {
        if (!penDown)
            turtle.setPenDown(true);
    }
}
