package worldguesser;

import java.util.Map;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class CreateCountries {
    private ArrayList<Country> countries;
    
    public ArrayList<Country> countries(Map<String, String[]> idsByCountry){
        
        for (Map.Entry<String, String[]> entry : idsByCountry.entrySet()) {
            String name = entry.getKey();
            String[] ids = entry.getValue();

            ArrayList<Shape> shapes;

            for(String id : ids){
                shapes.add(shape);
            }

            countries.add(new Country(name, shapes));
        }

        return countries;
    }
}
