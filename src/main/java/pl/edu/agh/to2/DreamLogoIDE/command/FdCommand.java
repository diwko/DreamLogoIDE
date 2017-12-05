package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class FdCommand extends Command {
    private double distance;

    public FdCommand(String[] arguments, Turtle turtle) throws ParseException {
        super(arguments, turtle);

        try {
            distance = Double.parseDouble(arguments[1]);
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect argument. Argument must be a number.", 0);
        }
    }

    @Override
    public void execute() throws IllegalStateException {
        turtle.move(distance);
    }

    @Override
    public void undo() {
//      TODO
    }

    @Override
    public void redo() {
//      TODO
    }
}
