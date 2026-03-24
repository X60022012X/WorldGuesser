package worldguesser;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WorldGuesserController {

    @FXML
    private Pane mapPane;
    private GameMap gameMap;


    @FXML
    public void initialize() {
        mapPane.setStyle("-fx-background-color: LIGHTSKYBLUE;");

        gameMap = new GameMap();

        Group mapGroup = (Group) gameMap.getMapGroup();
        mapPane.getChildren().add(mapGroup);

        Bounds bounds = mapGroup.getBoundsInLocal();
        System.out.println("bounds = " + bounds);

        double paneWidth = 500;
        double paneHeight = 700;

        double scale = Math.min(
            paneWidth / bounds.getWidth(),
            paneHeight / bounds.getHeight()
        );

        // Move map to (0,0)
        mapGroup.setTranslateX(-bounds.getMinX());
        mapGroup.setTranslateY(-bounds.getMinY());

        // Scale from top-left
        mapGroup.getTransforms().clear();
        mapGroup.getTransforms().add(
            new javafx.scene.transform.Scale(scale, scale, 0, 0)
        );

        // 🔥 Center the map (new)
        double scaledWidth = bounds.getWidth() * scale;
        double scaledHeight = bounds.getHeight() * scale;

        mapGroup.setTranslateX(
            mapGroup.getTranslateX() + (paneWidth - scaledWidth) / 2
        );

        mapGroup.setTranslateY(
            mapGroup.getTranslateY() + (paneHeight - scaledHeight) / 2
        );
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/worldguesser/main.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}