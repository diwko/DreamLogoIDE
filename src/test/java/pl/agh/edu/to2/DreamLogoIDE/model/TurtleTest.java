package pl.agh.edu.to2.DreamLogoIDE.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.edu.agh.to2.DreamLogoIDE.model.Area;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;
import pl.edu.agh.to2.DreamLogoIDE.model.Turtle;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TurtleTest {
    private Turtle turtle;

    public static Stream<Double> getValues() {
        return IntStream.rangeClosed(1, 20).asDoubleStream().map(x -> x * 10.0).boxed();
    }

    @BeforeEach
    public void initialize() {
        turtle = new Turtle(new Position(10, 10, 45), new Area(1000, 1000), null);
    }

    @ParameterizedTest
    @MethodSource(value = "getValues")
    public void moveTest(double distance) {
        double initialX = turtle.getPosition().getX();
        double initialY = turtle.getPosition().getY();
        turtle.move(distance);
        assertEquals(turtle.getPosition().getX(), initialX + distance * cos(toRadians(45.0)));
        assertEquals(turtle.getPosition().getY(), initialY + distance * sin(toRadians(45.0)));
    }

    @ParameterizedTest
    @MethodSource(value = "getValues")
    public void rotateTest(double angle) {
        double initialAngle = turtle.getPosition().getRotation();
        turtle.rotate(angle);
        assertEquals(turtle.getPosition().getRotation(), angle + initialAngle);
    }
}
