package worldguesser;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HighscoreController {

    @FXML
    private TextArea highscoreArea;

    @FXML
    public void initialize() {

        ScoreStorage storage = new ScoreStorage("allScores.csv");

        ArrayList<Integer> scores = storage.topFiveScores();

        StringBuilder text = new StringBuilder();
        if(scores.isEmpty()) {
            text.append("No scores yet.");
        } else {
            text.append("Best times\n\n");

            for (int i = 0; i < scores.size(); i++) {
                text.append((i + 1) + ". " + scores.get(i) + " s\n");
            }
        }
        highscoreArea.setText(text.toString());
    }
}