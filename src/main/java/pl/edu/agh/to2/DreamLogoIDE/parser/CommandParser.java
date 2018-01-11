package pl.edu.agh.to2.DreamLogoIDE.parser;

import pl.edu.agh.to2.DreamLogoIDE.command.Command;

import java.text.ParseException;
import java.util.Optional;

public interface CommandParser {
    Optional<Command> getCommand(String command) throws IllegalArgumentException, ParseException;
}
