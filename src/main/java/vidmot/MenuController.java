package vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class MenuController {

                //Nýr leikur og hætta
    @FXML
    private MenuItem menuItemNyrLeikur;
    @FXML
    private MenuItem menuItemHaetta;
    @FXML
    private MenuItem umForrit;

                //Erfiðileikastig
    @FXML
    private RadioMenuItem radioLett;
    @FXML
    private RadioMenuItem radioMidlungs;
    @FXML
    private RadioMenuItem radioErfitt;
    private ToggleGroup difficultyGroup;
    @FXML
    private GoldController goldController;


    public MenuController() {

    }


    @FXML
    public void initialize() {

        difficultyGroup = new ToggleGroup();
        radioLett.setToggleGroup(difficultyGroup);
        radioMidlungs.setToggleGroup(difficultyGroup);
        radioErfitt.setToggleGroup(difficultyGroup);

        if (menuItemNyrLeikur == null) {
            menuItemNyrLeikur = new MenuItem();
            menuItemNyrLeikur.setText("Nýr leikur");
        }

        if (menuItemHaetta == null) {
            menuItemHaetta = new MenuItem();
            menuItemHaetta.setText("Hætta");
        }


        menuItemNyrLeikur.setOnAction(event -> onNyrLeikur());
        menuItemHaetta.setOnAction(event -> onHaetta());
    }


    @FXML
    private void onErfidleikastig() {
        RadioMenuItem selectedRadio = (RadioMenuItem) difficultyGroup.getSelectedToggle();
        String selectedDifficulty = selectedRadio.getText();
        System.out.println("Valið erfiðleikastig: " + selectedDifficulty);

        // Kalla á aðferð í GoldController til að uppfæra upplýsingar um erfiðleikastig
        if (goldController != null) {
            goldController.updateDifficulty(selectedDifficulty);
        }

    }

    @FXML
    private void onNyrLeikur() {
        // Aðferð til að byrja nýjan leik
        System.out.println("Nýr leikur hefst!");
        goldController.startGame();
    }



    @FXML
    private void onHaetta() {
        // Birta Alert dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Staðfesta hættuna");
        alert.setHeaderText("Ertu viss um að þú viljir hætta við leikinn?");
        alert.setContentText("Viltu hætta við leikinn?");

        // Aðferð til að hætta í forritinu
        // Bæta viðhnöfnun á atburði
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // Hætta við forritið
            System.exit(0);
        } else {
            // Ef notandi ýtir á cancel, látum ekkert gerast
        }
    }
    @FXML
    private void onUmForritid() {
        // Birta Alert dialog með upplýsingum um forritið
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um forritið");
        alert.setHeaderText(null);
        alert.setContentText("Forritið er búið til af Þorsteinn Hilmir Erlendsson.\nÁrtal: 2024.");
                                                                        //Mun þurfa uppfæra í 2025
        alert.showAndWait();
    }


    public void setGoldController(GoldController aThis) {
        goldController = aThis;
    }
}
