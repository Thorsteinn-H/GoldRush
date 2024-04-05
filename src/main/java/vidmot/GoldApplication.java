package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;

public class GoldApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gold-rush-view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Gold Rush");
        Scene s = new Scene(root, 650, 500);
        primaryStage.setScene(s);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}