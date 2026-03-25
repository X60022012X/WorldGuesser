package worldguesser;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HighscoreController {

    @FXML
    private TextArea highscoreArea;

    @FXML
    public void initialize() {

        ScoreStorage storage = new ScoreStorage("allScores.csv");

        ArrayList<Double> scores = storage.topFiveScores();

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

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/main.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}