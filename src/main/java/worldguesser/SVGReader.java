package worldguesser;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.SVGPath;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SVGReader {
    
    // INITIALIZES A MAP OF SHAPE IDS TO SHAPE OBJECTS AND A GROUP FOR ALL SHAPES
    private Map<String, Shape> idToShape = new HashMap<>();
    private Group group = new Group();


    // CONSTRUCTOR THAT LOADS THE SVG FILE, CREATES SHAPE OBJECTS, AND ADDS THEM TO THE MAP AND GROUP
    public SVGReader() {

        // TRY TO LOAD THE SVG FILE, AND CATCH ANY EXCEPTIONS THAT OCCUR
        try {

            // LOADS THE SVG FILE AS AN INPUT STREAM AND CHECKS IF IT WAS SUCCESSFULLY LOADED
            InputStream is = getClass().getResourceAsStream("map.svg");
            if (is == null) {
                throw new IllegalStateException("Could not find map.svg");
            }

            // PARSES THE SVG FILE INTO A DOCUMENT OBJECT
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(is);

            // GETS ALL THE PATH ELEMENTS 
            NodeList paths = doc.getElementsByTagName("path");

            // ITERATES THROUGH ALL THE PATH ELEMENTS, CREATES SHAPE OBJECTS, AND ADDS THEM TO THE MAP AND GROUP
            for (int i = 0; i < paths.getLength(); i++) {

                // GETS THE ID AND PATH DATA FROM THE PATH ELEMENT
                Element el = (Element) paths.item(i);
                String id = el.getAttribute("id");
                String d = el.getAttribute("d");

                // IF THE ID OR PATH DATA IS BLANK, SKIP THIS ELEMENT
                if (id.isBlank() || d.isBlank()) {
                    continue;
                }

                // CREATES A SHAPE OBJECT FROM THE PATH DATA, SETS ITS COLOR AND STROKE
                SVGPath shape = new SVGPath();
                shape.setContent(d);
                shape.setFill(Color.GRAY);
                shape.setStroke(Color.WHITE);

                // IF THE ID OF THE SHAPE IS "large_masses_of_water", SETS ITS COLOR TO CORNFLOWERBLUE
                if(id.equals("large_masses_of_water")){
                    shape.setFill(Color.CORNFLOWERBLUE);
                }
                
                // ADDS THE SHAPE TO THE MAP OF SHAPE IDS TO SHAPE OBJECTS
                idToShape.put(id, shape);

                // IF THE SHAPE'S BOUNDS ARE WITHIN A CERTAIN RANGE, ADDS IT TO THE GROUP
                Bounds b = shape.getBoundsInLocal();
                if (b.getMinX() < 5000 && b.getMinY() < 5000) {
                    group.getChildren().add(shape);
                }
                
            }

        // CATCH ANY EXCEPTIONS THAT OCCUR AND THROW A RUNTIME EXCEPTION WITH A MESSAGE
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SVG", e);
        }
    }


    // GETTER FOR THE MAP OF SHAPE IDS TO SHAPE OBJECTS
    public Map<String, Shape> getIdToShape() {
        return idToShape;
    }


    // GETTER FOR THE GROUP CONTAINING ALL THE SHAPES OF THE MAP
    public Group getGroup() {
        return group;
    }
}