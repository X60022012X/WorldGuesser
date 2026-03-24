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
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class GameController {

    private WorldGuesser game;
    private GameMap gameMap;
    private Timeline timerUpdater;
    private Timer timer;
    private ScoreStorage scoreStorage;   

    @FXML
    private Pane mapPane;

    @FXML
    private Label targetCountryLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label attemptsLabel;

    @FXML
    private Label countriesLabel;

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
        timer = new Timer();
        timer.start();
        scoreStorage = new ScoreStorage("allScores.csv");

        timerUpdater = new Timeline(
            new KeyFrame(Duration.seconds(0.1), e -> updateUI(game.getCurrentCountry().getName()))
        );

        timerUpdater.setCycleCount(Timeline.INDEFINITE);
        timerUpdater.play();

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
            timer.stop();
            timerUpdater.stop();
            Score score = new Score(Math.round(timer.getTimeSeconds()*10)/10.0, game.getMisclicks());
            scoreStorage.saveScore(score);
            updateUI("");
        } else{
            updateUI(game.getCurrentCountry().getName());
        }
    }

    private void updateUI(String country) {
        targetCountryLabel.setText("Find: " + country);
        attemptsLabel.setText("Attempts left: " + game.getAttempts());
        incorrectsLabel.setText("Incorrects: " + game.getMisclicks());
        countriesLabel.setText("Countries: " + (40 - game.getRemainingCountries()) + "/40" );

        double timeSpent = timer.getTimeSeconds();
        timeLabel.setText(String.format("Time: %.1f s", timeSpent));

        double totalTime = timeSpent + game.getMisclicks() * 5;
        timeSpentLabel.setText(String.format("Time spent: %.1f s", timeSpent));
        totalTimeLabel.setText(String.format("Total time: %.1f s", totalTime));
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
        timer = new Timer();
        timer.start();
        updateUI(game.getCurrentCountry().getName());
    }

    @FXML
    private void tryAgain(ActionEvent event) {
        System.out.println("Try Again pressed");
    }
}