package worldguesser;

import java.util.Map;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class CreateCountries {

    // INITIALIZES AN ARRAYLIST TO HOLD THE COUNTRY OBJECTS
    private ArrayList<Country> countries = new ArrayList<>();
    

    // CREATES COUNTRY OBJECTS BASED ON THE MAP OF COUNTRY NAMES TO SHAPE IDS AND THE MAP OF SHAPE IDS TO SHAPE OBJECTS
    public ArrayList<Country> createCountries(Map<String, String[]> idsByCountry, Map<String, Shape> idToShape){
        
        // ITERATES THROUGH THE MAP OF COUNTRY NAMES TO SHAPE IDS
        for (Map.Entry<String, String[]> entry : idsByCountry.entrySet()) {
            // GETS THE NAME OF THE COUNTRY AND THE ARRAY OF SHAPE IDS
            String name = entry.getKey();
            String[] ids = entry.getValue();

            // CREATES AN ARRAYLIST TO HOLD THE SHAPE OBJECTS FOR THE COUNTRY
            ArrayList<Shape> shapes = new ArrayList<>();

            // ITERATES THROUGH THE ARRAY OF SHAPE IDS AND ADDS THE CORRESPONDING SHAPE OBJECTS TO THE ARRAYLIST
            for(String id : ids){
                shapes.add(idToShape.get(id));
            }

            // CREATES A NEW COUNTRY OBJECT WITH THE NAME AND SHAPES AND ADDS IT TO THE ARRAYLIST OF COUNTRIES
            countries.add(new Country(name, shapes.toArray(new Shape[0])));
        }
        // RETURNS THE ARRAYLIST OF COUNTRY OBJECTS
        return countries;
    }
}
