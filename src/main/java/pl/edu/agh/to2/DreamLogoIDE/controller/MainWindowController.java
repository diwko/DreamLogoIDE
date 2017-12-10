package pl.edu.agh.to2.DreamLogoIDE.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.edu.agh.to2.DreamLogoIDE.command.Command;
import pl.edu.agh.to2.DreamLogoIDE.command.CommandRegistry;
import pl.edu.agh.to2.DreamLogoIDE.model.Area;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;
import pl.edu.agh.to2.DreamLogoIDE.parser.CommandParser;
import pl.edu.agh.to2.DreamLogoIDE.parser.CommandParserImp;

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
    private TurtleDrawingController turtleDrawingController;
    private Turtle turtle;

    public void initialize() {
        try {
            commmandParser = new CommandParserImp("pl.edu.agh.to2.DreamLogoIDE/Commands.csv");
        } catch (IOException e) {
            setErrorMessage(e.getMessage());
        }
        commandRegistry = new CommandRegistry();

        turtle = new Turtle(new Position(canvas.getWidth() / 2, canvas.getHeight() / 2, 270),
                new Area(canvas.getWidth(), canvas.getHeight()));

        turtleDrawingController = new TurtleDrawingController(turtle, new CanvasDrawer(canvas));

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
