package worldguesser;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GameController {
    private WorldGuesser game;

    @FXML
    private Pane mapPane;

    @FXML
    private Label targetCountryLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label attemptsLabel;

    @FXML
    private Label timeSpentLabel;

    @FXML
    private Label incorrectsLabel;

    @FXML
    private Label totalTimeLabel;

    private GameMap gameMap;

    @FXML
    public void initialize() {
        gameMap = new GameMap();
        game = new WorldGuesser(gameMap.getCountries());
        
        Group mapGroup = gameMap.getMapGroup();

        mapPane.getChildren().clear();
        mapPane.getChildren().add(mapGroup);

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(mapPane.widthProperty());
        clip.heightProperty().bind(mapPane.heightProperty());
        mapPane.setClip(clip);

        mapPane.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            fitMapToPane(mapGroup);
        });

        fitMapToPane(mapGroup);

        registerClicks();
        updateUI();
    }

    private void registerClicks() {
        for (Country country : gameMap.getCountries()) {
            for (Shape shape : country.getShapes()) {
                shape.setOnMouseClicked(event -> handleCountryClick(country));
            }
        }
    }

    private void handleCountryClick(Country country) {
        System.out.println("Clicked: " + country.getName());

        boolean correct = game.guess(country);
        updateUI();
    }

    private void updateUI() {
        targetCountryLabel.setText("Find: " + game.getCurrentTarget().getName());
        timeLabel.setText("Time: " + game.getCurrentRoundTime() + "s");
        attemptsLabel.setText("Attempts left: " + game.getAttemptsLeft());
        timeSpentLabel.setText("Time spent: " + game.getTimeSpent() + "s");
        incorrectsLabel.setText("Incorrects: " + game.getIncorrectCount());
        totalTimeLabel.setText("Total time: " + game.getTotalTime() + "s");
    }


    private void fitMapToPane(Group mapGroup) {
        double paneWidth = mapPane.getWidth();
        double paneHeight = mapPane.getHeight();

        if (paneWidth <= 0 || paneHeight <= 0) {
            return;
        }

        Bounds bounds = mapGroup.getBoundsInLocal();
        System.out.println("bounds = " + bounds);

        double scale = Math.min(
            paneWidth / bounds.getWidth(),
            paneHeight / bounds.getHeight()
        );

        scale *= 1.02;

        mapGroup.getTransforms().clear();
        mapGroup.getTransforms().add(new Scale(scale, scale, 0, 0));

        double offsetX = -bounds.getMinX() * scale;
        double offsetY = -bounds.getMinY() * scale;

        double scaledWidth = bounds.getWidth() * scale;
        double scaledHeight = bounds.getHeight() * scale;

        mapGroup.setTranslateX(offsetX + (paneWidth - scaledWidth) / 2);
        mapGroup.setTranslateY(offsetY + (paneHeight - scaledHeight) / 2);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/main.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void resetGame(ActionEvent event) {
        System.out.println("Reset pressed");
    }

    @FXML
    private void tryAgain(ActionEvent event) {
        System.out.println("Try Again pressed");
    }
}
