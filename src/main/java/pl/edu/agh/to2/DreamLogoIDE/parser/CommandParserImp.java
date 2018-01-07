package pl.edu.agh.to2.DreamLogoIDE.parser;

import pl.edu.agh.to2.DreamLogoIDE.command.Command;
import pl.edu.agh.to2.DreamLogoIDE.command.RepeatCommand;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class CommandParserImp implements CommandParser {
    private CommandProvider commandProvider;

    public CommandParserImp(CommandProvider commandProvider) throws IOException {
        this.commandProvider = commandProvider;
    }

    @Override
    public Command getCommand(String textCommand) throws IllegalArgumentException, ParseException {
        Queue<String> args = new LinkedList<>(Arrays.asList(textCommand.toLowerCase().split("\\s+")));
        return getCommand(args);
    }

    private Command getCommand(Queue<String> args) throws ParseException {
        Command command = commandProvider.getCommand(getCommandArgs(args));

        if (command instanceof RepeatCommand)
            command = getRepeatCommand((RepeatCommand) command, args);

        return command;
    }

    private String[] getCommandArgs(Queue<String> args) throws ParseException {
        String keyword = args.peek();
        if (!commandProvider.isSupported(keyword))
            throw new ParseException("Illegal command", 0);

        int argsNumber = commandProvider.getCommandArgumentsNumber(keyword);
        String[] commandArgs = new String[argsNumber + 1];
        for (int i = 0; i < argsNumber + 1; i++)
            commandArgs[i] = args.remove();

        return commandArgs;
    }

    private Command getRepeatCommand(RepeatCommand command, Queue<String> args) throws ParseException {
        if (!args.remove().equals("["))
            throw new ParseException("Not found '['", 0);

        while (!args.peek().equals("]"))
            command.addCommand(getCommand(args));

        args.remove();
        return command;
    }
}
