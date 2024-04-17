package vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Gull extends Rectangle {

    public Gull() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gull-view.fxml"));

         fxmlLoader.setRoot(this);
         fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
