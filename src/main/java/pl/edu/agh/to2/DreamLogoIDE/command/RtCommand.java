package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public class RtCommand extends Command {
    private double angle;

    public RtCommand(String[] arguments, Turtle turtle) throws ParseException {
        super(arguments, turtle);

        try {
            angle = Double.parseDouble(arguments[1]);
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
//      TODO
    }

    @Override
    public void redo() {
//      TODO
    }
}
