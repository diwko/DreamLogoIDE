package parser;

import command.Command;
import model.Turtle;

import java.text.ParseException;

public interface CommandParser {
    Command getCommand(String command, Turtle turtle) throws IllegalArgumentException, ParseException;
}
