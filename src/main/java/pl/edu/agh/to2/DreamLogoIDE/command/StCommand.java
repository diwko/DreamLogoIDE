package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class StCommand extends Command {
    private boolean wasHidden;

    public StCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);
        wasHidden = turtle.isHidden();
    }

    @Override
    public void execute() {
        turtle.setHidden(false);
    }

    @Override
    public void undo() {
        if (wasHidden)
            turtle.setHidden(true);
    }
}
