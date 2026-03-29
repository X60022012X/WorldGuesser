package worldguesser;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighscoreController {

    // ARRAYLIST TO STORE THE TIMES OF THE SCORES
    private ArrayList<Double> sortedScores = new ArrayList<>();


    // GETS THE VBox IN THE FXML WHERE THE SCORES WILL BE DISPLAYED
    @FXML
    private VBox scoreBox;


    // INITIALIZES THE CONTROLLER BY LOADING THE TOP FIVE SCORES FROM THE SCORE STORAGE AND UPDATING THE DISPLAY
    @FXML
    public void initialize() {

        // CREATES A NEW INSTANCE OF THE SCORE STORAGE
        ScoreStorage storage = new ScoreStorage("allScores.csv");
        
        // GETS THE TOP FIVE SCORES FROM THE STORAGE
        this.sortedScores = storage.getTopFiveScores();

        // SETS THE TIMES TO THE SCORES AND UPDATES THE DISPLAY
        updateScores();
    }


    // UPDATES THE DISPLAY OF THE SCORES IN THE HIGH SCORE SCREEN
    private void updateScores() {

        // CLEARS THE CURRENT CONTENT OF THE SCORE BOX
        scoreBox.getChildren().clear();

        // IF THERE ARE NO SCORES, DISPLAYS A MESSAGE INDICATING THAT THERE ARE NO SCORES YET
        if (sortedScores == null || sortedScores.isEmpty()) {

            // CREATES A NEW LABEL WITH THE MESSAGE
            Label noScoresLabel = new Label("No scores yet");

            // SETS THE STYLE OF THE LABEL
            noScoresLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");

            // ADDS THE LABEL TO THE SCORE BOX
            scoreBox.getChildren().add(noScoresLabel);
            return;
        }

        // IF THERE ARE SCORES, ITERATES THROUGH THE SORTED SCORES
        for (int i = 0; i < this.sortedScores.size(); i++) {

            // GETS THE TIME FOR THE CURRENT SCORE
            double time = sortedScores.get(i);

            // CREATES A NEW LABEL FOR THE CURRENT SCORE WITH ITS RANK AND TIME
            Label scoreLabel = new Label(
                String.format("%d.    %.1f seconds", i + 1, time)
            ); 

            // SETS THE STYLE OF THE SCORE LABEL
            scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");

            // ADDS THE SCORE LABEL TO THE SCORE BOX
            scoreBox.getChildren().add(scoreLabel);
        }
    }


    // NAVIGATES TO THE MAIN SCREEN WHEN THE HOME BUTTON IS CLICKED
    @FXML
    private void goBack(ActionEvent event) throws IOException {

        // LOADS THE HIGHSCORE SCREEN FXML AND CREATES A NEW SCENE WITH IT
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/main.fxml"));
        Scene scene = new Scene(loader.load());

        // GETS THE CURRENT STAGE AND SETS THE NEW SCENE TO IT
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}