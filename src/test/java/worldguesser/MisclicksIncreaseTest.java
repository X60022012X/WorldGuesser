package worldguesser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.shape.Shape;
import java.util.ArrayList;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

public class MisclicksIncreaseTest {
    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @Test
    public void testWrongGuessIncreasesMisclicks() {
        ArrayList<Country> testCountries = new ArrayList<>();
        Shape[] emptyShapes = new Shape[]{};
        Country testNorway = new Country("Norway", emptyShapes);
        Country testSweden = new Country("Sweden", emptyShapes);

        testCountries.add(testNorway);
        testCountries.add(testSweden);

        WorldGuesser testGame = new WorldGuesser(testCountries);

        if (testGame.getCurrentCountry() == testNorway) {
            testGame.handleClick(testSweden);
        } else if (testGame.getCurrentCountry() == testSweden) {
            testGame.handleClick(testNorway);
        }

        assertEquals(1, testGame.getMisclicks());
    }
}
