package pl.edu.agh.to2.DreamLogoIDE.command;

import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;
import java.util.*;

public class ProcedureDefinitionCommand extends Command {
    private List<String> commands = new ArrayList<>();
    private String name;
    private List<String> args = new ArrayList<>();
    private boolean isDefined = false;
    private Set<String> localVariablesKeywords = new HashSet<>(Arrays.asList("make", "niech"));
    private Map<String, String> localVariables = new HashMap<>();
    private Map<String, Integer> localVariablesStartLine = new HashMap<>();

    public ProcedureDefinitionCommand(String[] arguments) throws ParseException {
        super(arguments);
        name = arguments[1];
    }

    @Override
    public void execute(Turtle turtle, ShapeDrawer shapeDrawer) {
        isDefined = true;
        replaceLocalVariables();
    }

    @Override
    public void undo(Turtle turtle, ShapeDrawer shapeDrawer) {
        isDefined = false;
    }

    public void addTextLine(String line) throws ParseException {
        for (String var : localVariablesKeywords)
            if (line.startsWith(var)) {
                addLocalVariable(line);
                return;
            }

        commands.add(line);
    }

    public String getName() {
        return name;
    }

    public int getCommandArgumentsNumber() {
        return args.size();
    }

    public void setCommandArgs(String[] arguments) throws ParseException {
        for (String arg : arguments) {
            if (!arg.startsWith(":"))
                throw new ParseException("Procedure arguments must start with ':'", 0);
            args.add(arg);
        }
    }

    public boolean isDefined() {
        return isDefined;
    }

    public List<String> getProcedureDefinitionBody(String[] params) throws ParseException {
        List<String> commandsCopy = new ArrayList<>(commands);

        for (int lineNum = 0; lineNum < commandsCopy.size(); lineNum++)
            commandsCopy.set(lineNum, replaceArgsWithParams(commandsCopy.get(lineNum), params));

        return commandsCopy;
    }

    private void addLocalVariable(String line) throws ParseException {
        String[] splitted = line.split("\\s+");
        if (splitted.length != 3 || !splitted[1].startsWith("\""))
            throw new ParseException("Incorrect local varible definition, correct: KEYWORD \"NAME VALUE", 0);

        String localVarName = splitted[1].replace("\"", ":").toLowerCase();
        localVariables.put(localVarName, splitted[2]);
        localVariablesStartLine.put(localVarName, commands.size());
    }

    private void replaceLocalVariables() {
        for (int lineNum = 0; lineNum < commands.size(); lineNum++) {
            for (String var : localVariables.keySet()) {
                if (localVariablesStartLine.get(var) >= lineNum)
                    commands.set(lineNum, commands.get(lineNum).replace(var, localVariables.get(var)));
            }
        }
    }

    private String replaceArgsWithParams(String line, String[] params) throws ParseException {
        if (params.length != args.size() + 1)
            throw new ParseException("Invalid number of arguments", 0);

        if (line.contains(":"))
            for (int argNum = 0; argNum < args.size(); argNum++)
                line = line.replace(args.get(argNum), params[argNum + 1]);

        return line;
    }
}
