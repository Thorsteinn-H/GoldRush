package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import vinnsla.Klukka;
import vinnsla.Leikur;

import java.util.HashMap;

public class GoldController {

    // Býr til beinan aðgang frá KeyCode og í stefnu (enum). Hægt að nota til að fletta upp
    // stefnu (og gráðum) fyrir KeyCode
    private static final HashMap<KeyCode, Stefna> map = new HashMap();

    public Klukka klukka = new Klukka(30);

    public Label fxTimiEftir;
    public Label fxStig;

    Timeline t;
    Leikur leikur;
    @FXML
    private MenuController menuStyringController;
    private boolean iGangi;
    private int erfidLeikaStig;
    private static int[] timinn = {30, 20, 10};

    ////////////////
    @FXML
    Leikbord fxLeikbord;


    //Þetta er notað 2. en ég veit ekki hvernig ég myndi breyta svo ég ætla bara ekki snerta
    private void setStefna(KeyEvent event) {

        if (iGangi) {
        }
    }



    public void orvatakkar(){
        map.put(KeyCode.UP, Stefna.UPP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);

        fxLeikbord.addEventFilter(KeyEvent.ANY,
                this::stefna);

        System.out.println("orvatakkar fundið");
    }

    /**
     * A description of the entire Java function.
     *
     * @param  keyEvent	description of parameter
     * @return         	description of return value
     */
    private void stefna(KeyEvent keyEvent) {
        if (!iGangi) {
            return;
        }
        KeyCode keyCode = keyEvent.getCode();
        if (map.containsKey(keyCode)) {
            fxLeikbord.afram(keyCode);
        }
    }

    public GoldController() {
        // Búum til KeyFrame sem vaknar á sek fresti og kallar á tic á klukku
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                klukka.tic();
            }
        });

        // Búum til Timeline með KeyFrame og stillum cycleCount
        t = new Timeline(keyFrame);

        // Setjum handler á setOnFinished á tímalínu hlutnum sem lýkur leiknum
        t.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stopGame();
            }
        });
    }

    /**
     * Start the game, reset score and time, generate and place gold, and set event filter.
     */
    public void startGame() {
        System.out.println("startGame()");
        if (iGangi) {
            stopGame();
        }

        leikur.nulstillaStig(); // Reset score
        klukka.setTime(timinn[erfidLeikaStig]); // Reset time


        iGangi = true;

        // Generate and place gold
        fxLeikbord.framleidaGull();
        fxLeikbord.framleidaGull();
        fxLeikbord.framleidaGull();


        fxLeikbord.addEventFilter(KeyEvent.ANY, this::setStefna);

        // Raesum klukkuna
        raesaKlukku();
    }

    // Aðferð til að stöðva tímalínu
    public void stopGame() {
        System.out.println("stopGame()");
        t.stop();
        iGangi = false;
        fxLeikbord.removeEventFilter(KeyEvent.ANY, this::setStefna);
    }


    public void initialize(){
        leikur = new Leikur();
        fxLeikbord.setLeikur(leikur);
        fxLeikbord.requestFocus();
        menuStyringController.setGoldController(this);
        fxStig.textProperty().bind(leikur.getStigProperty().asString());
        fxTimiEftir.textProperty().bind(klukka.timeProperty().asString());
        orvatakkar();

        klukka.timeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0) {
                stopGame();
            }
        });

    }

    //Byrja klukku
    private void raesaKlukku() {

        klukka.setTime(timinn[erfidLeikaStig]);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e -> klukka.tic());

        // Búa til KeyFrame sem vaknar á sekúndu fresti og kallar á tic á klukkunni
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(klukka.getStartTime());

        // Ræsa tímalínu með play
        timeline.play();
    }


    // Bætt við updateDifficulty() aðferðinni
    public void updateDifficulty(String difficulty) {
        switch (difficulty) {
            case "Létt":
                this.erfidLeikaStig = 0;
                break;
            case "Miðlungs":
                this.erfidLeikaStig = 1;
                break;
            case "Erfitt":
                this.erfidLeikaStig = 2;
                break;
            default:
                // Fallback ef ekki er valið neitt
                this.erfidLeikaStig = 0;
                break;
        }
    }
}