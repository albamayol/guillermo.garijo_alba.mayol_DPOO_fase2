package Business;

import java.util.ArrayList;

public class ResultadoPrueba {
    protected String tipus;
    protected boolean pasa;

    public ResultadoPrueba(String tipus) {
        this.tipus = tipus;
    }

    public ResultadoPrueba setPasa(boolean pasa) {
        this.pasa=pasa;
        return this;
    }
}
