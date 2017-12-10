package pl.edu.agh.to2.DreamLogoIDE.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandRegistry {
    private ObservableList<Command> commandStack = FXCollections.observableArrayList();
    private ObservableList<Command> undoCommandStack = FXCollections.observableArrayList();

    public void executeCommand(Command command) throws IllegalStateException {
        command.execute();
        commandStack.add(command);
        undoCommandStack.clear();
    }

    public void redo() {
        if (undoCommandStack.isEmpty())
            return;

        Command lastUndoCommand = undoCommandStack.remove(undoCommandStack.size() - 1);
        lastUndoCommand.redo();
        commandStack.add(lastUndoCommand);
    }

    public void undo() {
        if (commandStack.isEmpty())
            return;

        Command lastCommand = commandStack.remove(commandStack.size() - 1);
        lastCommand.undo();
        undoCommandStack.add(lastCommand);
    }

    public ObservableList<Command> getCommandStack() {
        return commandStack;
    }
}
