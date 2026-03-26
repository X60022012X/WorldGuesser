package worldguesser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighscoreController {

    private ArrayList<Double> times = new ArrayList<>();

    @FXML
    private VBox scoreBox;

    @FXML
    public void initialize() {
        ScoreStorage storage = new ScoreStorage("allScores.csv");
 
        ArrayList<Double> scores = storage.getTopFiveScores();
        updateScores(scores);
    }

    public void setTimes(ArrayList<Double> times) {
        this.times = new ArrayList<>(times);
        updateScores(this.times);
    }

    private void updateScores(ArrayList<Double> times) {
        scoreBox.getChildren().clear();

        if (times == null || times.isEmpty()) {
            Label noScoresLabel = new Label("No scores yet");
            noScoresLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
            scoreBox.getChildren().add(noScoresLabel);
            return;
        }

        ArrayList<Double> sortedTimes = new ArrayList<>(times);
        Collections.sort(sortedTimes);

        for (int i = 0; i < sortedTimes.size(); i++) {
            double time = sortedTimes.get(i);

            Label scoreLabel = new Label(
                String.format("%d.    %.1f seconds", i + 1, time)
            );
            scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");

            scoreBox.getChildren().add(scoreLabel);
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/main.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}