package parser;

import command.Command;
import command.FdCommand;
import command.RtCommand;


public class CommandParserImp implements CommandParser {
    @Override
    public Command getCommand(String command) throws IllegalArgumentException {
        String converted = command.trim().toLowerCase().replaceAll("\\s+", " ");
        String[] splitted = converted.split(" ");


        switch (splitted[0]) {
            case "fd":
                return fdCommandParse(splitted, command);
            case "rt":
                return rtCommandParse(splitted, command);
            default:
                throw new IllegalArgumentException("Incorrect command: " + command);
        }
    }

    private Command fdCommandParse(String[] splitted, String command) throws IllegalArgumentException {
        if (splitted.length != 2)
            throw new IllegalArgumentException("Incorrect argument number: " + command);

        try {
            return new FdCommand(Double.parseDouble(splitted[1]), command);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect argument number: " + command);
        }
    }

    private Command rtCommandParse(String[] splitted, String command) throws IllegalArgumentException {
        if (splitted.length != 2)
            throw new IllegalArgumentException("Incorrect argument number: " + command);

        try {
            return new RtCommand(Double.parseDouble(splitted[1]), command);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect argument number: " + command);
        }
    }

}
