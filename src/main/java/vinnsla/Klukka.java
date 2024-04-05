package vinnsla;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Klukka {

    private final IntegerProperty time;
    private final int startTime;

    public Klukka(int startTime) {
        this.startTime = startTime;
        this.time = new SimpleIntegerProperty(startTime);
    }

    public void tic() {
        setTime(getTime() - 1);
    }

    public int getTime() {
        return time.get();
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    public IntegerProperty timeProperty() {
        return time;
    }

    public int getStartTime() {
        return startTime;
    }
}
