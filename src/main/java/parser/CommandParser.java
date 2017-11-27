package parser;

import command.Command;

public interface CommandParser {
    Command getCommand(String command) throws IllegalArgumentException;
}
