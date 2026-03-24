package worldguesser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorldGuesserApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("World Guesser");

        stage.setScene(
            new Scene(
                FXMLLoader.load(getClass().getResource("/worldguesser/main.fxml"))
            )
        );

        stage.show();
    }
}