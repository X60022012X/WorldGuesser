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
    
    public GameMap(){
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
                    System.out.println("Clicked: " + country.getName());
                });
            }
        }
    }
}

