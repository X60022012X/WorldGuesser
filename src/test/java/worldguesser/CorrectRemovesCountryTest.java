package worldguesser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import javafx.scene.shape.Shape;

public class CorrectRemovesCountryTest {

    @Test
    public void testCorrectGuessRemovesCountry() {
        Shape[] shapes = new Shape[]{};
        Country norway = new Country("Norway", shapes);
        Country sweden = new Country("Sweden", shapes);

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(norway);
        countries.add(sweden);

        WorldGuesser testGame = new WorldGuesser(countries);
        Country currentCountry = testGame.getCurrentCountry();
        testGame.handleClick(currentCountry);

        assertEquals(1, testGame.getRemainingCountries().size());
    }
}
