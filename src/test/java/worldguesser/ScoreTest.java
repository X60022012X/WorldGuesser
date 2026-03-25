package worldguesser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    
    @Test
    public void testTotalTimeCalculation() {
        Score testScore = new Score(40, 3);

        assertEquals(55, testScore.getTotalSeconds());
    }
}
