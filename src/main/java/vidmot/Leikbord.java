package vidmot;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import vinnsla.Leikur;

import java.io.IOException;
import java.util.Random;

public class Leikbord extends Pane {

    @FXML
    private Grafari fxGrafari;
    private ObservableList<Gull> gullListi = FXCollections.observableArrayList();
    private double goldX;
    private double goldY;
    private Leikur leikur;

    private static final Random rand = new Random();


    public void framleidaGull(){

        Gull nyttGull = new Gull();

        // Create a new instance of Random
        Random random = new Random();

        // Calculate random coordinates within the entire area of the Leikbord
        double randomX = random.nextDouble() * (getWidth() - nyttGull.getWidth());
        double randomY = random.nextDouble() * (getHeight() - nyttGull.getHeight());

        nyttGull.setLayoutX(randomX);
        nyttGull.setLayoutY(randomY);

        getChildren().add(nyttGull);
        gullListi.add(nyttGull);

    }

    public Leikbord() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leikbord-view.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void afram(KeyCode keyCode) {
        double moveDistance = 10;

        switch (keyCode) {
            case UP:
                if (fxGrafari.getLayoutY() - moveDistance >= 0)
                    fxGrafari.setLayoutY(fxGrafari.getLayoutY() - moveDistance);
                break;
            case DOWN:
                if (fxGrafari.getLayoutY() + moveDistance <= getHeight() - fxGrafari.getHeight())
                    fxGrafari.setLayoutY(fxGrafari.getLayoutY() + moveDistance);
                break;
            case LEFT:
                if (fxGrafari.getLayoutX() - moveDistance >= 0)
                    fxGrafari.setLayoutX(fxGrafari.getLayoutX() - moveDistance);
                break;
            case RIGHT:
                if (fxGrafari.getLayoutX() + moveDistance <= getWidth() - fxGrafari.getWidth())
                    fxGrafari.setLayoutX(fxGrafari.getLayoutX() + moveDistance);
                break;
        }

        if (erGrefurGull()){
            leikur.haekkaStig();
        }
    }
    private boolean erGrefurGull(){
        for (Gull g : gullListi) {
            if (fxGrafari.getBoundsInParent().intersects(g.getBoundsInParent())) {
                // Ef grafarinn er í árekstri við gullið, fjarlægjum það bæði af borðinu og af listanum
                getChildren().remove(g);
                gullListi.remove(g);
                framleidaGull();
                return true;
            }
        }
        return false;
    }



    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
    }


}
