package worldguesser;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Country {

    // FIELDS FOR THE NAME OF THE COUNTRY AND THE SHAPES THAT REPRESENT IT
    private String name;
    private Shape[] shapes;

    // CONSTRUCTOR THAT INITIALIZES THE NAME AND SHAPES OF THE COUNTRY
    public Country(String name, Shape[] shapes) {
        this.name = name;
        this.shapes = shapes;
    }

    // SETS THE COLOR OF THE COUNTRY TO GREEN
    public void setCorrect() {
        for (Shape s : shapes) {
            s.setFill(Color.GREEN);
        }
    }

    // SETS THE COLOR OF THE COUNTRY TO RED FOR 0.5 SECONDS AND THEN RESETS IT BACK TO BLACK
    public void setWrong() {
        for (Shape s : shapes) {
            s.setFill(Color.RED);
        }

        // AFTER 0.5 SECONDS DELAY RESETS THE COLOR
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> {
            this.reset();
        });

        // START THE PAUSE TRANSITION
        pause.play();
    }

    // RESETS THE COLOR OF THE COUNTRY TO BLACK
    public void reset() {
        for (Shape s : shapes) {
            s.setFill(Color.BLACK);
        }
    }

    // RETURNS THE NAME OF THE COUNTRY
    public String getName() {
        return name;
    }

    // RETURNS ARRAY OF SHAPES THAT REPRESENT THE COUNTRY
    public Shape[] getShapes() {
        return shapes;
    }
}