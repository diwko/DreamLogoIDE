package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class RepeatCommand extends Command {
    private int n;
    private List<Command> commands = new LinkedList<>();

    public RepeatCommand(String[] arguments, Turtle turtle, ShapeDrawer shapeDrawer) throws ParseException {
        super(arguments, turtle, shapeDrawer);

//        TODO
    }

    @Override
    public void execute() {
        for(int i = 0; i < n; i++) {
            commands.forEach(Command::execute);
        }
    }

    @Override
    public void undo() {
//        TODO
    }

    @Override
    public void redo() {
//        TODO
    }
}
