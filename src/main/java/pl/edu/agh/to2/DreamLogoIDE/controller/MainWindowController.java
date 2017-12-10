package pl.edu.agh.to2.DreamLogoIDE.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import pl.edu.agh.to2.DreamLogoIDE.command.Command;
import pl.edu.agh.to2.DreamLogoIDE.command.CommandRegistry;
import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeCanvasDrawer;
import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.drawer.TurtleDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Area;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;
import pl.edu.agh.to2.DreamLogoIDE.parser.CommandParser;
import pl.edu.agh.to2.DreamLogoIDE.parser.CommandParserImp;

import java.io.IOException;
import java.text.ParseException;


public class MainWindowController {
    @FXML
    private Canvas shapesCanvas;

    @FXML
    private Canvas turtleCanvas;

    @FXML
    private ListView<Command> commandHistoryView;

    @FXML
    private TextField commandInputField;

    @FXML
    private TextField errorMessageField;

    private CommandParser commmandParser;
    private CommandRegistry commandRegistry;
    private Turtle turtle;
    private ShapeDrawer shapeDrawer;
    private TurtleDrawer turtleDrawer;

    public void initialize() {
        try {
            commmandParser = new CommandParserImp("pl.edu.agh.to2.DreamLogoIDE/Commands.csv");
        } catch (IOException e) {
            setErrorMessage(e.getMessage());
        }

        commandRegistry = new CommandRegistry();

        turtle = new Turtle(
                new Position(shapesCanvas.getWidth() / 2, shapesCanvas.getHeight() / 2, 270),
                new Area(shapesCanvas.getWidth(), shapesCanvas.getHeight()),
                new Image(getClass().getResource("/pl.edu.agh.to2.DreamLogoIDE/img/turtle.png").toString()));

        shapeDrawer = new ShapeCanvasDrawer(turtle, shapesCanvas);
        turtleDrawer = new TurtleDrawer(turtle, turtleCanvas);

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
            Command command = commmandParser.getCommand(commandInputField.getText(), turtle, shapeDrawer);
            commandRegistry.executeCommand(command);
        } catch (ParseException | IllegalStateException e) {
            setErrorMessage(e.getMessage());
        } finally {
            commandInputField.clear();
        }
    }

    public void undoCommand() {
        commandRegistry.undo();
    }

    public void redoCommand() {
        commandRegistry.redo();
    }

    private void setErrorMessage(String text) {
        errorMessageField.setText(text);
    }
}
