package worldguesser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class WorldGuesserTest {
    
    @Test
    public void testGameStartsWithThreeAttempts() {
        ArrayList<Country> testCountries = new ArrayList<>();
        testCountries.add(new Country("Norway", new javafx.scene.shape.Shape[]{}));

        WorldGuesser testGame = new WorldGuesser(testCountries);

        assertEquals(3, testGame.getAttempts());
    }
    
}
