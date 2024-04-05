package vidmot;

import javafx.scene.input.KeyCode;
import static org.junit.Assert.*;


import org.junit.Test;


public class LeikbordTest {

    @Test
    public void testFramleidaGull() {
        Leikbord leikbord = new Leikbord();
        leikbord.framleidaGull();
        assertEquals(1, leikbord.getChildren().size());
        assertFalse(leikbord.getGullListi().isEmpty());
    }

    @Test
    public void testAfram() {
        Leikbord leikbord = new Leikbord();
        leikbord.framleidaGull();
        double initialLayoutX = leikbord.getFxGrafari().getLayoutX();
        double initialLayoutY = leikbord.getFxGrafari().getLayoutY();
        leikbord.afram(KeyCode.RIGHT);
        assertNotEquals(initialLayoutX, leikbord.getFxGrafari().getLayoutX());
        assertEquals(initialLayoutY, leikbord.getFxGrafari().getLayoutY(), 0);
    }

    @Test
    public void testErGrefurGull() {
        Leikbord leikbord = new Leikbord();
        leikbord.framleidaGull();
        double grafariX = leikbord.getFxGrafari().getLayoutX();
        double grafariY = leikbord.getFxGrafari().getLayoutY();
        Gull gull = leikbord.getGullListi().get(0);
        gull.setLayoutX(grafariX);
        gull.setLayoutY(grafariY);

        assertEquals(0, leikbord.getChildren().size());
        assertEquals(0, leikbord.getGullListi().size());
    }
}
