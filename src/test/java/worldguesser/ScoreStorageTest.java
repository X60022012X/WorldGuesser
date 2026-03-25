package worldguesser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.io.File;

public class ScoreStorageTest {

    @Test
    public void testSaveScoreAndReadTopScores() {

        String testFile = "testScores.csv";

        ScoreStorage storage = new ScoreStorage(testFile);

        storage.saveScore(new Score(20,0));
        storage.saveScore(new Score(30,0));
        storage.saveScore(new Score(10,0));

        ArrayList<Double> scores = storage.topFiveScores();

        assertEquals(10.0, scores.get(0));

        new File(testFile).delete();
    }
}