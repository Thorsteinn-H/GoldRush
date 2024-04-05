package vinnsla;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {
    private static IntegerProperty stig = new SimpleIntegerProperty(0);

    public static IntegerProperty getStigProperty() {
        return stig;
    }

    public static int getStig() {
        return stig.get();
    }

    public static void setStig(int nyrStig) {
        stig.set(nyrStig);
    }

    public static void haekkaStig() {
        stig.set(stig.get() + 1);
    }

    public static void nulstillaStig() {
        stig.set(0);
    }
}
