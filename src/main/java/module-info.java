module com.example.goldrush {
    requires javafx.controls;
    requires javafx.fxml;


    opens vidmot to javafx.fxml;
    exports vidmot;
    opens vinnsla to javafx.fxml;
    exports vinnsla;

}