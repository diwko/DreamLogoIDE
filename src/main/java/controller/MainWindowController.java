package controller;

import command.Command;
import command.CommandRegistry;
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

import java.io.IOException;
import java.text.ParseException;


public class MainWindowController {
    @FXML
    private Canvas canvas;

    @FXML
    private ListView<Command> commandHistoryView;

    @FXML
    private TextField commandInputField;

    @FXML
    private TextField errorMessageField;

    private CommandParser commmandParser;
    private CommandRegistry commandRegistry;
    private Drawer drawer;
    private Turtle turtle;

    public void initialize() {
        try {
            commmandParser = new CommandParserImp("Commands.csv");
        } catch (IOException e) {
            setErrorMessage(e.getMessage());
        }
        commandRegistry = new CommandRegistry();
        drawer = new DrawerImp(canvas);
        turtle = new Turtle(new Position(canvas.getWidth() / 2, canvas.getHeight() / 2, 270), drawer);

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
        errorMessageField.clear();
        try {
            Command command = commmandParser.getCommand(commandInputField.getText(), turtle);
            commandRegistry.executeCommand(command);
        } catch (ParseException | IllegalStateException e) {
            setErrorMessage(e.getMessage());
        } finally {
            commandInputField.clear();
        }
    }

    private void setErrorMessage(String text) {
        errorMessageField.setText(text);
    }

}
