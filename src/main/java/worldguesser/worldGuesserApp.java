package worldguesser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorldGuesserApp extends Application {

    // MAIN METHOD THAT LAUNCHES THE APP / GAME
    public static void main(String[] args) {
        launch(args);
    }


    // STARTS THE APPLICATION BY LOADING THE MAIN MENU FXML AND SETTING IT AS THE CURRENT SCENE
    @Override
    public void start(Stage stage) throws Exception {

        // LOADS THE HIGHSCORE SCREEN FXML AND CREATES A NEW SCENE WITH IT
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/main.fxml"));
        Scene scene = new Scene(loader.load());

        // SETS THE TITLE OF THE STAGE AND SHOWS IT
        stage.setTitle("World Guesser");
        
        // SETS THE SCENE TO THE STAGE, SIZES IT TO THE SCENE, AND MAKES IT NON-RESIZABLE
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);

        // SHOWS THE STAGE
        stage.show();
    }
}