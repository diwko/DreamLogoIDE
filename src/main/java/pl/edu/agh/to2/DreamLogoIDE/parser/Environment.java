package pl.edu.agh.to2.DreamLogoIDE.parser;

import pl.edu.agh.to2.DreamLogoIDE.command.Command;
import pl.edu.agh.to2.DreamLogoIDE.command.ProcedureCommand;
import pl.edu.agh.to2.DreamLogoIDE.command.ProcedureDefinitionCommand;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    private CommandProvider commandProvider;
    private Map<String, ProcedureDefinitionCommand> procedureDefinitions = new HashMap<>();
    private CommandParser parser;

    public Environment(CommandProvider commandProvider, CommandParser parser) {
        this.commandProvider = commandProvider;
        this.parser = parser;
    }

    public boolean isDefined(String name) {
        return commandProvider.isSupported(name) || procedureDefinitions.containsKey(name);
    }

    public int getCommandArgsNumber(String name) throws ParseException {
        if (commandProvider.isSupported(name))
            return commandProvider.getCommandArgumentsNumber(name);

        ProcedureDefinitionCommand procedureDef = procedureDefinitions.get(name);
        if (procedureDef != null)
            return procedureDef.getCommandArgumentsNumber();

        throw new ParseException("Command not defined", 0);
    }

    public Command getCommand(String[] args) throws ParseException {
        if (commandProvider.isSupported(args[0]))
            return commandProvider.getCommand(args);

        ProcedureDefinitionCommand procedureDef = procedureDefinitions.get(args[0]);
        if (procedureDef != null)
            return getProcedureCommand(procedureDef, args);

        throw new ParseException("Command not defined", 0);
    }

    public void addDefinition(ProcedureDefinitionCommand definition) {
        procedureDefinitions.put(definition.getName(), definition);
    }

    private Command getProcedureCommand(ProcedureDefinitionCommand definition, String[] args) throws ParseException {
        ProcedureCommand procedure = new ProcedureCommand(args);

        List<String> body = definition.getProcedureDefinitionBody(args);

        for (String line : body)
            parser.getCommand(line).ifPresent(procedure::addCommand);

        return procedure;
    }
}
