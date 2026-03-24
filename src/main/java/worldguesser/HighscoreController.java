package worldguesser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HighscoreController {

    @FXML
    private TextArea highscoreArea;

    @FXML
    public void initialize() {
        loadHighscores();
    }

    private void loadHighscores() {
        StringBuilder content = new StringBuilder();

        try (InputStream is = getClass().getResourceAsStream("/worldguesser/highscores.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            highscoreArea.setText(content.toString());

        } catch (IOException | NullPointerException e) {
            highscoreArea.setText("Could not load highscores.");
        }
    }
}