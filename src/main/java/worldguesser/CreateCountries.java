package worldguesser;

import java.util.Map;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class CreateCountries {
    private ArrayList<Country> countries = new ArrayList<>();
    
    public ArrayList<Country> createCountries(Map<String, String[]> idsByCountry, Map<String, Shape> idToShape){
        
        for (Map.Entry<String, String[]> entry : idsByCountry.entrySet()) {
            String name = entry.getKey();
            String[] ids = entry.getValue();

            ArrayList<Shape> shapes = new ArrayList<>();

            for(String id : ids){
                shapes.add(idToShape.get(id));
            }

            countries.add(new Country(name, shapes.toArray(new Shape[0])));
        }
        return countries;
    }
}
