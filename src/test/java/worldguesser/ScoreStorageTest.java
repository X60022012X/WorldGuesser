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

        storage.writeScoreToCsv(new Score(20,0));
        storage.writeScoreToCsv(new Score(30,0));
        storage.writeScoreToCsv(new Score(10,0));

        ArrayList<Double> scores = storage.getTopFiveScores();

        assertEquals(10.0, scores.get(0));

        new File(testFile).delete();
    }
}