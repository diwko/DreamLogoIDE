package command;

import model.Turtle;

public class FDCommand extends SingleCommand {
    private final String KEYWORD = "fd";
    private double distance;

    public FDCommand(Turtle turtle, String args) throws IllegalArgumentException {
        super(turtle);

    }

    @Override
    public void execute() {
        turtle.move(distance);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public String getText() {
        return null;
    }
}
