package worldguesser;

public class Country {

    private String name;
    private SVGPath shape;

    public Country(String name, SVGPath shape) {
        this.name = name;
        this.shape = shape;
    }

    public void setCorrect() {
        shape.setFill(Color.GREEN);
    }

    public void setWrong() {
        shape.setFill(Color.RED);
    }

    public void reset() {
        shape.setFill(Color.GRAY);
    }

    public String getName() {
        return name;
    }
}
