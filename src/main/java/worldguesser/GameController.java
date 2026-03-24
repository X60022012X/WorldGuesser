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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class GameController {

    private WorldGuesser game;
    private GameMap gameMap;

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
        updateUI(game.getCurrentCountry().getName());
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

        boolean finished = game.handleClick(country);

        if (finished) {
            System.out.println("Game finished!");
            updateUI("");
        } else{
            updateUI(game.getCurrentCountry().getName());
        }
    }

    private void updateUI(String target) {
        targetCountryLabel.setText("Find: " + target);
        attemptsLabel.setText("Attempts left: " + game.getAttempts());
        incorrectsLabel.setText("Incorrects: " + game.getMisclicks());

        timeLabel.setText("Time: " + String.format("%.1f s", elapsedSeconds));
        timeSpentLabel.setText("Time spent: " + String.format("%.1f s", elapsedSeconds));
        totalTimeLabel.setText("Total time: " + String.format("%.1f s", elapsedSeconds));
    }

    private void fitMapToPane(Group mapGroup) {
        double paneWidth = mapPane.getWidth();
        double paneHeight = mapPane.getHeight();

        if (paneWidth <= 0 || paneHeight <= 0) {
            return;
        }

        Bounds bounds = mapGroup.getBoundsInLocal();

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
        game = new WorldGuesser(gameMap.getCountries());
        updateUI(game.getCurrentCountry().getName());
    }

    @FXML
    private void tryAgain(ActionEvent event) {
        System.out.println("Try Again pressed");
    }
}