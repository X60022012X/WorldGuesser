package worldguesser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class AttemptsResetTest {
    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }
    
    @Test
    public void testAttemptsResetForNewCountry() {
        Shape[] shapes = new Shape[]{};
        Country norway = new Country("Norway", shapes);
        Country sweden = new Country("Sweden", shapes);
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(norway);
        countries.add(sweden);
        WorldGuesser game = new WorldGuesser(countries);

        if (game.getCurrentCountry() == norway) {
            game.handleClick(sweden);
            game.handleClick(sweden);
            game.handleClick(sweden);
        } else {
            game.handleClick(norway);
            game.handleClick(norway);
            game.handleClick(norway);
        }

        assertEquals(3, game.getAttempts());
    }
}