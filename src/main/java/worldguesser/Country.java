package worldguesser;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Country {

    private String name;
    private List<Shape> shapes = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public String getName() {
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
    }

    public void reset() {
        for (Shape s : shapes) {
            s.setFill(Color.LIGHTGRAY);
        }
    }
}