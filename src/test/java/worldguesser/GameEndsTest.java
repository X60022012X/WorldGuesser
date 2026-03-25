package worldguesser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class GameEndsTest {
    @Test
    public void testGameFinishesWhenLastCountryClicked() {
        Shape[] shapes = new Shape[]{};
        Country norway = new Country("Norway", shapes);
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(norway);
        WorldGuesser game = new WorldGuesser(countries);
        boolean finished = game.handleClick(norway);

        assertTrue(finished);
    }
}