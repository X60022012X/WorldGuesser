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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class GameController {

    // INSTANCE VARIABLES FOR THE GAME, MAP, TIMER, AND SCORE STORAGE
    private WorldGuesser game;
    private GameMap gameMap;
    private Timeline timerUpdater;
    private Timer timer;
    private ScoreStorage scoreStorage;   

   
    // PANE IN THE FXML WHERE THE MAP WILL BE DISPLAYED
    @FXML
    private Pane mapPane;


    // LABEL FOR THE TARGET COUNTRY TO FIND
    @FXML
    private Label targetCountryLabel;


    // LABEL FOR THE NUMBER OF ATTEMPTS LEFT
    @FXML
    private Label attemptsLabel;
    

    // LABEL FOR THE NUMBER OF COUNTRIES FOUND
    @FXML
    private Label countriesLabel;


    // LABEL FOR THE NUMBER OF INCORRECT CLICKS AND THE TIME PENALTY BASED ON THE NUMBER OF INCORRECT CLICKS
    @FXML
    private Label incorrectsLabel;

    @FXML
    private Label timePenaltyLabel;


    // LABELS FOR THE TIME
    @FXML
    private Label timeLabel;

    @FXML
    private Label timeSpentLabel;

    @FXML
    private Label totalTimeLabel;


    // VBox AND HBox FOR THE RESULT DISPLAY IN THE RESULT SCREEN
    @FXML
    private VBox resultBox;

    @FXML
    private HBox bottomButtonBox;


    // INITIALIZES THE CONTROLLER BY SETTING UP THE GAME, MAP, TIMER, AND UI ELEMENTS
    @FXML
    public void initialize() {

        // CREATES A NEW GAME MAP  
        gameMap = new GameMap();

        // CREATES A NEW GAME WITH THE COUNTRIES FROM THE MAP
        game = new WorldGuesser(gameMap.getCountries());

        // CREATES A NEW TIMER AND STARTS IT
        timer = new Timer();
        timer.start();

        // CREATES A NEW SCORE STORAGE TO STORE THE SCORES IN A CSV FILE
        scoreStorage = new ScoreStorage("allScores.csv");



        // CREATES A NEW TIMELINE 
        timerUpdater = new Timeline(
            // CREATES A KEY FRAME THAT UPDATES THE UI EVERY 0.1 SECONDS
            new KeyFrame(Duration.seconds(0.1),
                // CREATES A NEW GAME MAP  A NEW GAME WITH THE COUNTRIES FROM THE MAP
                e -> updateUI(game.getCurrentCountry().getName()))
        );

        // SETS THE CYCLE COUNT OF THE TIMELINE TO INDEFINITE
        timerUpdater.setCycleCount(Timeline.INDEFINITE);

        // STARTS THE TIMELINE TO UPDATE THE UI
        timerUpdater.play();



        // GETS THE GROUP CONTAINING ALL THE SHAPES OF THE MAP
        Group mapGroup = gameMap.getMapGroup();

        // ADDS THE MAP GROUP TO THE MAP PANE
        mapPane.getChildren().clear();
        mapPane.getChildren().add(mapGroup);


        
        // MAKE THE PANE CLIP OUT OVERFLOW FROM THE MAP 
        Rectangle clip = new Rectangle();

        // BINDS THE WIDTH AND HEIGHT OF THE CLIP TO THE WIDTH AND HEIGHT OF THE MAP PANE
        clip.widthProperty().bind(mapPane.widthProperty());
        clip.heightProperty().bind(mapPane.heightProperty());

        // SETS THE CLIP OF THE MAP PANE TO THE RECTANGLE CLIP
        mapPane.setClip(clip);

       
        
        // REGISTERS THE CLICK, MOUSE ENTERED, AND MOUSE EXITED EVENTS FOR EACH COUNTRY SHAPE
        registerClicks();

        // UPDATES THE UI WITH THE NAME OF THE FIRST TARGET COUNTRY
        updateUI(game.getCurrentCountry().getName());
    }


    // WARNING: The following method has to be called in MainController after the screen is loaded, scene is set to the stage and the stage is shown, otherwise the map will not be fitted to the pane correctly. 
    public void afterSceneIsSet() {
        fitMapToPane(gameMap.getMapGroup());
    }


    // REGISTERS THE CLICK, MOUSE ENTERED, AND MOUSE EXITED EVENTS FOR EACH COUNTRY SHAPE
    private void registerClicks() {

        // ITERATES THROUGH EACH COUNTRY
        for (Country country : gameMap.getCountries()) {

            // ITERATES THROUGH EACH SHAPE OF THE COUNTRY
            for (Shape shape : country.getShapes()) {

                // SETS THE ON MOUSE CLICKED EVENT TO HANDLE THE COUNTRY CLICK
                shape.setOnMouseClicked(event -> handleCountryClick(country));

                // SETS THE ON MOUSE ENTERED TO CHANGE THE COLOR OF THE SHAPE
                shape.setOnMouseEntered(event -> {

                    // IF THE COUNTRY IS STILL IN THE GAME, CHANGES THE COLOR OF THE SHAPE TO BLUE
                    if (game.getRemainingCountries().contains(country)) {
                        shape.setFill(Color.rgb(34, 63, 76));
                    }
                });

                // SETS THE ON MOUSE EXITED TO CHANGE THE COLOR OF THE SHAPE BACK TO BLACK
                shape.setOnMouseExited(event -> {

                    // IF THE COUNTRY IS STILL IN THE GAME, CHANGES THE COLOR OF THE SHAPE BACK TO BLACK
                    if (game.getRemainingCountries().contains(country)) {
                        shape.setFill(Color.BLACK);
                    }
                });
            }
        }
    }


    // HANDLES THE CLICK ON A COUNTRY SHAPE
    private void handleCountryClick(Country country) {

        // GETS THE RESULT OF THE CLICK FROM THE GAME LOGIC
        boolean finished = game.handleClick(country);

        // IF THE GAME IS FINISHED
        if (finished) {

            // STOPS THE TIMER AND THE TIMER UPDATER
            timer.stop();
            timerUpdater.stop();

            // CREATES A NEW SCORE OBJECT WITH THE TIME AND MISCLICKS, WRITES IT TO THE SCORE STORAGE
            Score score = new Score(Math.round(timer.getTimeSeconds()*10)/10.0, game.getMisclicks());
            scoreStorage.writeScoreToCsv(score);

            // UPDATES THE UI, AND SHOWS THE RESULTS
            updateUI("");
            showResults();

        // IF THE GAME IS NOT FINISHED
        } else{

            //UPDATES THE UI WITH THE NAME OF THE NEW TARGET COUNTRY
            updateUI(game.getCurrentCountry().getName());
        }
    }


    // UPDATES THE UI ELEMENTS IN THE GAME SCREEN BASED ON THE CURRENT STATE OF THE GAME
    private void updateUI(String country) {

        // UPDATES THE TARGET COUNTRY LABEL TO SHOW THE NAME OF THE CURRENT TARGET COUNTRY
        targetCountryLabel.setText("Find: " + country);

        // UPDATES THE ATTEMPTS LABEL TO SHOW THE NUMBER OF ATTEMPTS LEFT
        attemptsLabel.setText("Attempts left: " + game.getAttempts());

        // UPDATES THE INCORRECTS LABEL TO SHOW THE NUMBER OF INCORRECT CLICKS
        incorrectsLabel.setText("Incorrects: " + game.getMisclicks());

        // UPDATES THE COUNTRIES LABEL TO SHOW THE NUMBER OF COUNTRIES FOUND OUT OF 40
        countriesLabel.setText("Countries: " + (40 - game.getRemainingCountriesSize()) + "/40" );

        // UPDATES THE TIME PENALTY LABEL TO SHOW THE TIME PENALTY BASED ON THE NUMBER OF INCORRECT CLICKS
        timePenaltyLabel.setText("Time Penalty: " + game.getMisclicks()*5 + " s");

        // UPDATES THE TIME LABEL TO SHOW THE CURRENT TIME IN SECONDS
        double timeSpent = timer.getTimeSeconds();
        timeLabel.setText(String.format("Time: %.1f s", timeSpent));

        // UPDATES THE TIME SPENT LABEL AND TOTAL TIME LABEL
        double totalTime = timeSpent + game.getMisclicks() * 5;
        timeSpentLabel.setText(String.format("Time spent: %.1f s", timeSpent));
        totalTimeLabel.setText(String.format("Total time: %.1f s", totalTime));
    }

    
    // FITS THE MAP TO THE MAP PANE BY CALCULATING THE SCALE AND TRANSLATION NEEDED TO CENTER THE MAP IN THE PANE
    private void fitMapToPane(Group mapGroup) {

        // GETS THE WIDTH AND HEIGHT OF THE MAP PANE
        double paneWidth = mapPane.getWidth();
        double paneHeight = mapPane.getHeight();

        // RETURNS WITHOUT DOING ANYTHING IF THE WIDTH OR HEIGHT OF THE PANE IS ZERO OR NEGATIVE
        if (paneWidth <= 0 || paneHeight <= 0) {
            return;
        }

        // GETS THE BOUNDS OF THE MAP GROUP
        Bounds bounds = mapGroup.getBoundsInLocal();

        // CALCULATES THE SCALE NEEDED TO FIT THE MAP IN THE PANE WHILE MAINTAINING THE ASPECT RATIO
        double scale = Math.min(
            paneWidth / bounds.getWidth(),
            paneHeight / bounds.getHeight()
        );

        // MULTIPLIES THE SCALE BY 1.04 TO REMOVE THE EDGE OF THE MAP THAT THEN IS CUT OFF DUE TO THE CLIP
        scale *= 1.04;

        // CLEARS ANY EXISTING TRANSFORMS ON THE MAP GROUP AND ADDS A NEW SCALE TRANSFORM TO SCALE THE MAP TO THE CALCULATED SCALE
        mapGroup.getTransforms().clear();
        mapGroup.getTransforms().add(new Scale(scale, scale, 0, 0));

        // CALCULATES THE TRANSLATION NEEDED TO CENTER THE MAP IN THE PANE BASED ON THE BOUNDS OF THE MAP AND THE CALCULATED SCALE
        double offsetX = -bounds.getMinX() * scale;
        double offsetY = -bounds.getMinY() * scale;

        // CALCULATES THE SCALED WIDTH AND HEIGHT OF THE MAP BASED ON THE BOUNDS AND THE CALCULATED SCALE
        double scaledWidth = bounds.getWidth() * scale;
        double scaledHeight = bounds.getHeight() * scale;

        // SETS THE TRANSLATION OF THE MAP GROUP TO THE CALCULATED OFFSET PLUS HALF OF THE REMAINING SPACE IN THE PANE TO CENTER THE MAP
        mapGroup.setTranslateX(offsetX + (paneWidth - scaledWidth) / 2);
        mapGroup.setTranslateY(offsetY + (paneHeight - scaledHeight) / 2);
    }


    // SHOWS THE RESULT BOX AND THE BOTTOM BUTTON BOX IN THE RESULT SCREEN
    private void showResults() {

        // MAKES THE RESULT BOX VISIBLE AND MANAGED
        resultBox.setVisible(true);
        resultBox.setManaged(true);

        // MAKES THE RESTART BUTTON VISIBLE AND MANAGED
        bottomButtonBox.setVisible(true);
        bottomButtonBox.setManaged(true);
    }


    // NAVIGATES TO THE GAME SCREEN WHEN THE "PLAY" BUTTON IS CLICKED
    @FXML
    private void goBack(ActionEvent event) throws IOException {

        // LOADS THE HIGHSCORE SCREEN FXML AND CREATES A NEW SCENE WITH IT
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/main.fxml"));
        Scene scene = new Scene(loader.load());

        // GETS THE CURRENT STAGE AND SETS THE NEW SCENE TO IT
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    // RESETS THE GAME WHEN THE "RETRY" BUTTON IS CLICKED
    @FXML
    private void resetGame(ActionEvent event) throws IOException {

        // STOPS THE TIMER UPDATER
        if (timerUpdater != null) {
            timerUpdater.stop();
        }

        // STOPS THE TIMER
        if (timer != null) {
            timer.stop();
        }

        // LOADS THE HIGHSCORE SCREEN FXML AND CREATES A NEW SCENE WITH IT
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/game.fxml"));
        Scene scene = new Scene(loader.load());

        // GETS THE CURRENT STAGE AND SETS THE NEW SCENE TO IT
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        // FIT THE MAP TO THE PANE
        GameController controller = loader.getController();
        controller.afterSceneIsSet();
    }
}