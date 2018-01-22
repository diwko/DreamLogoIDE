package pl.agh.edu.to2.DreamLogoIDE.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.edu.agh.to2.DreamLogoIDE.model.Area;
import pl.edu.agh.to2.DreamLogoIDE.model.Position;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AreaTest {
    private Area area;

    public static Stream<Position> getPositions() {
        return IntStream.rangeClosed(1, 20).asDoubleStream().map(x -> x * 10.0).boxed()
                .map(x -> new Position(x, x, 0.0));
    }

    @BeforeEach
    public void initialize() {
        area = new Area(100, 100);
    }

    @ParameterizedTest
    @MethodSource(value = "getPositions")
    public void isCorrectPositionTest(Position position) {
        if(position.getX()<0||position.getX()>area.getWidth()||position.getY()<0||position.getY()>area.getHeight()){
            assertFalse(area.isCorrectPosition(position));
        }
        else {
            assertTrue(area.isCorrectPosition(position));
        }
    }
}
