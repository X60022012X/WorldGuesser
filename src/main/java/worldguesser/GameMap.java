package worldguesser;

import java.util.ArrayList;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class GameMap {

    private ArrayList<Country> countries;
    private SVGReader reader;
    private Map<String, Shape> idToShape;
    private Group mapGroup;

    private WorldGuesser worldGuesser;

    public GameMap(WorldGuesser worldGuesser){

        this.worldGuesser = worldGuesser;

        reader = new SVGReader();

        CreateCountries creater = new CreateCountries();

        idToShape = reader.getIdToShape();

        countries = creater.createCountries(CountryData.idsByCountry, idToShape);

        mapGroup = reader.getGroup();

        registerClicks();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public Group getMapGroup() {
        return mapGroup;
    }

    private void registerClicks() {

        for (Country country : countries) {

            for (Shape shape : country.getShapes()) {

                shape.setOnMouseClicked(event -> {

                    boolean finished =
                        worldGuesser.handleClick(country);

                    if(finished) {
                        System.out.println("Game finished!");
                    }

                });
            }
        }
    }
}