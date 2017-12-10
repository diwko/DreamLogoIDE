package pl.edu.agh.to2.DreamLogoIDE.parser;

import pl.edu.agh.to2.DreamLogoIDE.command.Command;
import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;

public interface CommandParser {
    Command getCommand(String command, Turtle turtle, ShapeDrawer shapeDrawer) throws IllegalArgumentException, ParseException;
}
