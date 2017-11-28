package parser;

import command.Command;
import model.Turtle;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class CommandParserImp implements CommandParser {
    private Map<String, String> commands = new HashMap<>();
    private Map<String, Integer> argumentsNumber = new HashMap<>();

    public CommandParserImp(String commandsFile) {
        loadCommandsFromFile(commandsFile);
    }

    @Override
    public Command getCommand(String command, Turtle turtle) throws ParseException {
        String converted = command.trim().toLowerCase().replaceAll("\\s+", " ");
        String[] splitted = converted.split(" ");

        if (!commands.containsKey(splitted[0]))
            throw new ParseException("Command not found: " + splitted[0], 0);

        int argsNumber = argumentsNumber.get(splitted[0]);
        if ((splitted.length - 1) != argsNumber)
            throw new ParseException("Incorrect number of arguments. Correct number: " + argsNumber, 0);

        String className = commands.get(splitted[0]);
        return getCommandInstance(className, splitted, turtle);
    }

    private Command getCommandInstance(String className, String[] arguments, Turtle turtle) throws ParseException {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(String[].class, Turtle.class);
            return (Command) constructor.newInstance(arguments, turtle);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException | ClassNotFoundException e) {
            throw new ParseException("Not implemented", 0);
        }
    }

    private void loadCommandsFromFile(String path) {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(line -> {
                try {
                    parseLine(line);
                } catch (ParseException e) {
                    //Skip line
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) throws ParseException {
        String[] splitted = line.split(",");
        if (splitted.length != 3)
            throw new ParseException("Command line have to many arguments: " + line, 0);

        commands.put(splitted[0], splitted[1]);
        try {
            argumentsNumber.put(splitted[0], Integer.parseInt(splitted[2]));
        } catch (NumberFormatException e) {
            throw new ParseException("Command line third value must be a integer", 0);
        }
    }

}
