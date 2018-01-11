package pl.edu.agh.to2.DreamLogoIDE.parser;

import pl.edu.agh.to2.DreamLogoIDE.command.Command;
import pl.edu.agh.to2.DreamLogoIDE.command.ProcedureDefinitionCommand;
import pl.edu.agh.to2.DreamLogoIDE.command.RepeatCommand;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class CommandParserImp implements CommandParser {
    private Environment environment;
    private ProcedureDefinitionCommand notEndedDefinition = null;

    public CommandParserImp(CommandProvider commandProvider) throws IOException {
        this.environment = new Environment(commandProvider, this);
    }

    @Override
    public Optional<Command> getCommand(String textCommand) throws IllegalArgumentException, ParseException {
        textCommand = textCommand.toLowerCase().trim();

        if (notEndedDefinition != null)
            return continueDefinition(textCommand);

        Queue<String> args = new LinkedList<>(Arrays.asList(textCommand.split("\\s+")));

        return getCommand(args);
    }

    private Optional<Command> getCommand(Queue<String> args) throws IllegalArgumentException, ParseException {
        Command command = environment.getCommand(getCommandArgs(args));

        if (command instanceof RepeatCommand)
            return Optional.of(getRepeatCommand((RepeatCommand) command, args));
        else if (command instanceof ProcedureDefinitionCommand)
            return getProcedureDefinitionCommand((ProcedureDefinitionCommand) command, args);

        return Optional.of(command);
    }

    private String[] getCommandArgs(Queue<String> args) throws ParseException {
        int argsNumber = environment.getCommandArgsNumber(args.peek());

        String[] commandArgs = new String[argsNumber + 1];
        for (int i = 0; i < argsNumber + 1; i++) {
            if (args.isEmpty())
                throw new ParseException("Illegal arguments number", 0);
            commandArgs[i] = args.remove();
        }
        return commandArgs;
    }

    private Optional<String[]> getProcedureDefinitionArgs(Queue<String> args) {
        List<String> arguments = new LinkedList<>();

        if (args.isEmpty() || !args.peek().startsWith(":"))
            return Optional.empty();

        while (args.peek().startsWith(":")) {
            arguments.add(args.poll());
            if (args.isEmpty())
                break;
        }

        String[] arr = new String[arguments.size()];
        return Optional.of(arguments.toArray(arr));
    }

    private Optional<Command> getProcedureDefinitionCommand(ProcedureDefinitionCommand definition, Queue<String> args) throws ParseException {
        Optional<String[]> arguments = getProcedureDefinitionArgs(args);
        if (arguments.isPresent())
            definition.setCommandArgs(arguments.get());

        if (!args.isEmpty())
            throw new ParseException("Incorrect procedure definition, wrong arguments", 0);

        notEndedDefinition = definition;
        return Optional.empty();
    }

    private Command getRepeatCommand(RepeatCommand command, Queue<String> args) throws ParseException {
        if (!args.remove().equals("["))
            throw new ParseException("Not found '['", 0);

        while (!args.peek().equals("]"))
            getCommand(args).ifPresent(command::addCommand);

        args.remove();
        return command;
    }

    private Optional<Command> continueDefinition(String procedureLine) throws ParseException {
        if (procedureLine.equals("end") || procedureLine.equals("już")) {
            ProcedureDefinitionCommand def = notEndedDefinition;
            notEndedDefinition = null;
            environment.addDefinition(def);
            return Optional.of(def);
        }

        notEndedDefinition.addTextLine(procedureLine);
        return Optional.empty();
    }
}
