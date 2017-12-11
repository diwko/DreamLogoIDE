package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class HtCommand extends Command {
    private boolean wasHidden;

    public HtCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);
        wasHidden = turtle.isHidden();
    }

    @Override
    public void execute() {
        turtle.setHidden(true);
    }

    @Override
    public void undo() {
        if (!wasHidden)
            turtle.setHidden(false);
    }
}
