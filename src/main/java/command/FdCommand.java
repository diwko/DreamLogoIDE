package command;

public class FdCommand extends SingleCommand {
    private double distance;

    public FdCommand(double distance, String commandText) throws IllegalArgumentException {
        super(commandText);
        this.distance = distance;
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
        return commandText;
    }
}
