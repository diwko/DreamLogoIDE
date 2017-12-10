package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public abstract class Command {
    protected final String[] arguments;
    protected Turtle turtle;
    protected ShapeDrawer shapeDrawer;

    public Command(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        this.arguments = arguments;
        this.turtle = turtle;
        this.shapeDrawer = shapeDrawer;
    }

    public String getText() {
        StringBuilder builder = new StringBuilder();
        for (String arg : arguments)
            builder.append(arg.toUpperCase()).append(" ");
        return builder.toString();
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public ShapeDrawer getShapeDrawer() {
        return shapeDrawer;
    }

    public abstract void execute();

    public abstract void undo();

    public void redo() {
        execute();
    }
}
