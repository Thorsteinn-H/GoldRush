package vidmot;

import javafx.scene.input.KeyCode;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class LeikbordTest {

    private Leikbord leikbord;

    @Before
    public void setUp() {
        leikbord = new Leikbord();
    }


    //Býr til gull og kíkir hvort það er á borðinu
    @Test
    public void testFramleidaGull() {
        leikbord.framleidaGull();
        assertEquals(1, leikbord.getChildren().size()-1); //-1 þarf að vera því grafarinn er í leikborði
        assertFalse(leikbord.getGullListi().isEmpty()); // Og kíkir hvort listinn er EKKI tómur
    }


    //býr til gull og færir grafaran á eitt gullið og kíkir hvort það hverfur þar sem
    // þegar grafarinn fer á gull þá hverfur það og nýtt er búið til
    @Test
    public void testErGrefurGull() {
        leikbord.framleidaGull();

        // nær í staðsetnigu gullsins
        double initialGullX = leikbord.getGullListi().get(0).getLayoutX();
        double initialGullY = leikbord.getGullListi().get(0).getLayoutY();

        // Færir grafaran á gullið
        leikbord.getFxGrafari().setLayoutX(initialGullX);
        leikbord.getFxGrafari().setLayoutY(initialGullY);
        //Þarf að hreyfa til hægri til að virka grefurgull method
        leikbord.afram(KeyCode.DOWN);

        // nær í staðsetnigu nýja gullsins
        double newGullX = leikbord.getGullListi().get(0).getLayoutX();
        double newGullY = leikbord.getGullListi().get(0).getLayoutY();

        // kíkir hvort það hvort nýtt gull var búið til
        assertNotEquals(initialGullX, newGullX);
        assertNotEquals(initialGullY, newGullY);
    }
}
