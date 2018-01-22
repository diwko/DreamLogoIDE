package pl.edu.agh.to2.DreamLogoIDE.command;

import javafx.scene.paint.Color;
import pl.edu.agh.to2.DreamLogoIDE.drawer.ShapeDrawer;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.text.ParseException;
import java.util.Stack;

public class SetPenColorCommand extends Command {
    private Color color;
    private Stack<Color> prevColorsStack = new Stack<>();

    public SetPenColorCommand(String[] arguments) throws ParseException {
        super(arguments);
        try {
            int red = Integer.parseInt(arguments[1]);
            int green = Integer.parseInt(arguments[2]);
            int blue = Integer.parseInt(arguments[3]);

            if (red > 255 || green > 255 || blue > 255 || red < 0 || green < 0 || blue < 0)
                throw new ParseException("Incorrect argument. color must be r g b (where r,g,b = [0-255]).", 0);

            color = Color.rgb(red, green, blue, 1.0);
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect argument. Argument must be a number.", 0);
        }
    }

    @Override
    public void execute(Turtle turtle, ShapeDrawer shapeDrawer) {
        prevColorsStack.push(turtle.getPenColor());
        turtle.setPenColor(color);
    }

    @Override
    public void undo(Turtle turtle, ShapeDrawer shapeDrawer) {
        turtle.setPenColor(prevColorsStack.pop());
    }
}