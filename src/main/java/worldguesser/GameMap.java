package worldguesser;

import java.util.ArrayList;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class GameMap {

    // INITIALIZES: ARRAYLIST OF COUNTRY OBJECTS, SVG READER, MAP OF SHAPE IDS TO SHAPE OBJECTS, GROUP FOR ALL SHAPES 
    private ArrayList<Country> countries;
    private SVGReader reader;
    private Map<String, Shape> idToShape;
    private Group mapGroup;
    

    // CONSTRUCTOR THAT CREATES AN ARRAYLIST OF COUNTRY OBJECTS AND A GROUP CONTAINING ALL THE SHAPES OF THE MAP
    public GameMap(){
        
        // INITIALIZES THE SVG READER, GETS MAP OF SHAPE IDS TO SHAPE OBJECTS, AND GETS THE GROUP CONTAINING ALL THE SHAPES OF THE MAP
        reader = new SVGReader();
        idToShape = reader.getIdToShape();
        mapGroup = reader.getGroup();

        // CREATES THE COUNTRY OBJECTS BASED ON: MAP OF COUNTRY NAMES TO SHAPE IDS AND THE MAP OF SHAPE IDS TO SHAPE OBJECTS
        CreateCountries creator = new CreateCountries(CountryData.idsByCountry, idToShape);
        countries = creator.getCountries();
        
        // SETS THE COLOR OF ALL THE COUNTRIES TO BLACK
        for(Country country : countries){
            for(Shape shape : country.getShapes()){
                shape.setFill(Color.BLACK);
            }      
        }
    }


    // GETTER FOR THE ARRAYLIST OF COUNTRY OBJECTS
    public ArrayList<Country> getCountries() {
        return countries;
    }


    // GETTER FOR THE GROUP CONTAINING ALL THE SHAPES OF THE MAP
    public Group getMapGroup() {
        return mapGroup;
    }
}