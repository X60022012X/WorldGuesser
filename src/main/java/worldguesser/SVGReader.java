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

    private Map<String, Shape> idToShape = new HashMap<>();
    private Group group = new Group();

    public SVGReader() {
        try {
            InputStream is = getClass().getResourceAsStream("map.svg");
            if (is == null) {
                throw new IllegalStateException("Could not find map.svg");
            }

            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(is);

            NodeList paths = doc.getElementsByTagName("path");

            for (int i = 0; i < paths.getLength(); i++) {
                Element el = (Element) paths.item(i);

                String id = el.getAttribute("id");
                String d = el.getAttribute("d");

                if (id.isBlank() || d.isBlank()) {
                    continue;
                }

                SVGPath shape = new SVGPath();
                shape.setContent(d);
                shape.setFill(Color.GRAY);
                shape.setStroke(Color.WHITE);

                if(id.equals("large_masses_of_water")){
                    shape.setFill(Color.CORNFLOWERBLUE);
                }

                idToShape.put(id, shape);
                Bounds b = shape.getBoundsInLocal();
                if (b.getMinX() < 5000 && b.getMinY() < 5000) {
                    group.getChildren().add(shape);
                }
                
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SVG", e);
        }
    }

    public Map<String, Shape> getIdToShape() {
        return idToShape;
    }

    public Group getGroup() {
        return group;
    }
}