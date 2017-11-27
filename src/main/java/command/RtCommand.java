package command;

public class RtCommand extends SingleCommand {
    private double angle;

    public RtCommand(double angle, String commandText) throws IllegalArgumentException {
        super(commandText);
        this.angle = angle;
    }

    @Override
    public void execute() {
        turtle.rotate(angle);
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

