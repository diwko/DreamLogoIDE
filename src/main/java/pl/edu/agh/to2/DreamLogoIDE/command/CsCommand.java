package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class CsCommand extends Command {
    private Position beforeCleaning;
    public CsCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);
    }

    @Override
    public void execute() {
        beforeCleaning = turtle.getPosition();
        turtle.setPosition(turtle.getInitialPosition());
        shapeDrawer.clearCanvas();
    }

    @Override
    public void undo() {
        shapeDrawer.undoDrawing();
        turtle.setPosition(beforeCleaning);
    }
}
