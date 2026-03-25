package worldguesser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import javafx.scene.shape.Shape;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

public class DecreasingAttemptsTest {
    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }
    
    @Test
    public void testAttemptsDecreaseAfterWrongGuess() {
        Shape[] shapes = new Shape[]{};
        Country norway = new Country("Norway", shapes);
        Country sweden = new Country("Sweden", shapes);
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(norway);
        countries.add(sweden);

        WorldGuesser game = new WorldGuesser(countries);
        int attemptsBefore = game.getAttempts();
        if (game.getCurrentCountry() == norway) {
            game.handleClick(sweden);
        } else {
            game.handleClick(norway);
        }

        assertEquals(attemptsBefore - 1, game.getAttempts());
    }
}
