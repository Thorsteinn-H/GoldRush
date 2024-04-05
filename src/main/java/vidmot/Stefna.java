package vidmot;

public enum Stefna {

    UPP (90),
    NIDUR(270),
    VINSTRI(180),
    HAEGRI(360);
    private final int gradur;

    Stefna(int s) {
        gradur=s;
    }
    public int getGradur() {
        return gradur;
    }

}
