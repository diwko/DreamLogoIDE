package controller;

import command.Command;
import command.CommandRegistry;
import command.SingleCommand;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Drawer;
import model.DrawerImp;
import model.Position;
import model.Turtle;
import parser.CommandParser;
import parser.CommandParserImp;


public class MainWindowController {
    @FXML
    private ListView<Command> commandHistoryView;

    @FXML
    private TextField commandInputField;

    @FXML
    private Canvas canvas;

    private CommandParser commmandParser;
    private CommandRegistry commandRegistry;
    private Drawer drawer;
    private Turtle turtle;

    public void initialize() {
        commmandParser = new CommandParserImp();
        commandRegistry = new CommandRegistry();

        drawer = new DrawerImp(canvas);
        turtle = new Turtle(new Position(canvas.getWidth() / 2, canvas.getHeight() / 2, 270), drawer);

        canvas.setStyle("");

        setCommandHistoryView();
    }

    private void setCommandHistoryView() {
        commandHistoryView.setItems(commandRegistry.getCommandStack());
        commandHistoryView.setCellFactory(lv -> new ListCell<Command>() {
            protected void updateItem(Command item, boolean empty) {
                super.updateItem(item, empty);
                setText((item != null && !empty) ? item.getText() : null);
            }
        });
    }

    @FXML
    public void executeCommand() {
        SingleCommand command = (SingleCommand) commmandParser.getCommand(commandInputField.getText());
        command.setTurtle(turtle);
        commandRegistry.executeCommand(command);
        commandInputField.clear();
    }

}
