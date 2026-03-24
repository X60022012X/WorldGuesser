package worldguesser;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Country {

    private String name;
    private Shape[] shapes;

    public Country(String name, Shape[] shapes) {
        this.name = name;
        this.shapes = shapes;
    }

    @Override
    public String toString(){
        return name;
    }

    public void setCorrect() {
        for (Shape s : shapes) {
            s.setFill(Color.GREEN);
        }
    }

    public void setWrong() {
        for (Shape s : shapes) {
            s.setFill(Color.RED);
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));

        pause.setOnFinished(e -> {
            reset();
        });

        pause.play();
    }

    public void reset() {
        for (Shape s : shapes) {
            s.setFill(Color.BLACK);
        }
    }

    public String getName() {
        return name;
    }

    public Shape[] getShapes() {
        return shapes;
    }
}