package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;
import java.util.Stack;

public class SetPenSizeCommand extends Command {
    private double penSize;
    private Stack<Double> prevSizesStack = new Stack<>();

    public SetPenSizeCommand(String[] arguments) throws ParseException {
        super(arguments);
        try {
            penSize = Double.parseDouble(arguments[1]);
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect argument. Argument must be a number.", 0);
        }
    }

    @Override
    public void execute(Turtle turtle, ShapeDrawer shapeDrawer) {
        prevSizesStack.push(turtle.getPenSize());
        turtle.setPenSize(penSize);
    }

    @Override
    public void undo(Turtle turtle, ShapeDrawer shapeDrawer) {
        turtle.setPenSize(prevSizesStack.pop());
    }
}
