package command;

import model.Turtle;

public abstract class SingleCommand implements Command {
    protected Turtle turtle;

    public SingleCommand(Turtle turtle) {
        this.turtle = turtle;
    }



}
