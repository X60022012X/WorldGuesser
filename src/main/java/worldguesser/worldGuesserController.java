package worldguesser;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class WorldGuesserController {

    @FXML
    private Pane mapPane;

    @FXML
    public void initialize() {
        GameMap map = new GameMap();
        mapPane.getChildren().add(map.getMapGroup());
    }
}