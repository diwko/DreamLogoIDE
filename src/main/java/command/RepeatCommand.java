package command;

import java.util.LinkedList;
import java.util.List;

public class RepeatCommand extends SingleCommand {
    private int n;
    private List<Command> commands = new LinkedList<>();

    public RepeatCommand(int n, String commandText) {
        super(commandText);
        this.n = n;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public void execute() {
        for(int i = 0; i < n; i++) {
            commands.forEach(Command::execute);
        }
    }

    @Override
    public void undo() {
        //TODO
    }

    @Override
    public void redo() {
        //TODO
    }

    @Override
    public String getText() {
        return commandText;
    }
}
