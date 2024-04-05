package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Grafari extends Rectangle {

    @FXML
    private Rectangle grafari;

    public Rectangle getGrafari() {
        return grafari;
    }


    public Grafari() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("grafari-view.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
