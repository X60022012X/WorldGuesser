package worldguesser.storage;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Controller {
    @FXML
    private Pane mapPane;

    private GameMap gameMap;

    @FXML
    public void initialize() {

        gameMap = new GameMap();

        for (String countryName : CountryData.idsByCountry.keySet()) {

            String[] ids = CountryData.idsByCountry.get(countryName);
            Shape[] shapes = new Shape[ids.length];

            for (int i = 0; i < ids.length; i++) {
                shapes[i] = (Shape) mapPane.lookup("#" + ids[i]);
            }

            gameMap.addCountry(new Country(countryName, shapes));
        }
    }
}
