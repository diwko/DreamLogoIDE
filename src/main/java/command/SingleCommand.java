package command;

import model.Turtle;

public abstract class SingleCommand implements Command {
    protected String commandText;
    protected Turtle turtle;

    public SingleCommand(String commandText) {
        this.commandText = commandText;
    }

    public SingleCommand(String commandText, Turtle turtle) {
        this.commandText = commandText;
        this.turtle = turtle;
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }
}
