package worldguesser;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Country {

    private String name;
    private List<Shape> shapes = new ArrayList<>();
    private List<String> ids = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

    public void addShape(String id, Shape shape) {
        ids.add(id);
        shapes.add(shape);
    }

    public String getName() {
        return name;
    }

    public boolean checkClick(String clickedId) {

        if (ids.contains(clickedId)) {
            setCorrect();
            return true;
        } else {
            setWrong();
            return false;
        }
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