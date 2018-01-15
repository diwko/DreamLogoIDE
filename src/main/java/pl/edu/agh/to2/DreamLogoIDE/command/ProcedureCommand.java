package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureCommand extends Command {
    private List<Command> commands = new ArrayList<>();

    public ProcedureCommand(String[] arguments) throws ParseException {
        super(arguments);
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public void execute(Turtle turtle, ShapeDrawer shapeDrawer) {
        commands.forEach(c -> c.execute(turtle, shapeDrawer));
    }

    @Override
    public void undo(Turtle turtle, ShapeDrawer shapeDrawer) {
        for (int i = commands.size() - 1; i >= 0; i--)
            commands.get(i).undo(turtle, shapeDrawer);
    }
}
