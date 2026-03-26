package worldguesser;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


// CONTROLLER FOR THE MAIN MENU, HANDLES NAVIGATION TO THE GAME AND HIGHSCORE SCREENS
public class MainController {


    // NAVIGATES TO THE GAME SCREEN WHEN THE "PLAY" BUTTON IS CLICKED
    @FXML
    private void showGame(ActionEvent event) throws IOException {

        // LOADS THE HIGHSCORE SCREEN FXML AND CREATES A NEW SCENE WITH IT
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/game.fxml"));
        Scene scene = new Scene(loader.load());

        // GETS THE CURRENT STAGE AND SETS THE NEW SCENE TO IT
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    // NAVIGATES TO THE HIGHSCORE SCREEN WHEN THE "HIGHSCORE" BUTTON IS CLICKED
    @FXML
    private void showHighscore(ActionEvent event) throws IOException {

        // LOADS THE HIGHSCORE SCREEN FXML AND CREATES A NEW SCENE WITH IT
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/highscore.fxml"));
        Scene scene = new Scene(loader.load());

        // GETS THE CURRENT STAGE AND SETS THE NEW SCENE TO IT
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}