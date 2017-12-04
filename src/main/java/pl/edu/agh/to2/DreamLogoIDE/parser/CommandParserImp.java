package pl.edu.agh.to2.DreamLogoIDE.parser;

import pl.edu.agh.to2.DreamLogoIDE.command.Command;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class CommandParserImp implements CommandParser {
    private Map<String, String> commands = new HashMap<>();
    private Map<String, Integer> argumentsNumber = new HashMap<>();

    public CommandParserImp(String commandsFile) throws IOException {
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

    private void loadCommandsFromFile(String path) throws IOException {
        try (InputStream in = getClass().getResourceAsStream("/" + path)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            Stream<String> lines = br.lines();
            lines.forEach(line -> {
                try {
                    parseLine(line);
                } catch (ParseException e) {
                    //Skip line
                }
            });
        } catch (IOException e) {
            throw new IOException("Commands File not found");
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
