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

        if (mapGroup instanceof Group group) {
            System.out.println("group children = " + group.getChildren().size());
        }
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
                    country.setCorrect();
                });
            }
        }
    }

    public static void main(String[] args) {
        try {
            GameMap map = new GameMap();
            System.out.println("GameMap created");

            ArrayList<Country> countries = map.getCountries();
            System.out.println("Countries size: " + countries.size());

            for(int i = 0; i < countries.size(); i ++){
                System.out.println(i+1 + ". " + countries.get(i).getName());
            }
        
            System.out.println(map.getMapGroup());

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

