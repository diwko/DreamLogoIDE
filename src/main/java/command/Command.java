package command;

import model.Turtle;

import java.text.ParseException;

public abstract class Command {
    protected final String[] arguments;
    protected Turtle turtle;

    public Command(String[] arguments, Turtle turtle) throws ParseException {
        this.arguments = arguments;
        this.turtle = turtle;
    }

    public String getText() {
        StringBuilder builder = new StringBuilder();
        for (String arg : arguments)
            builder.append(arg.toUpperCase() + " ");
        return builder.toString();
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public abstract void execute();

    public abstract void undo();

    public abstract void redo();
}
