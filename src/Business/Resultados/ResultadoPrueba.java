package Business.Resultados;

import java.util.ArrayList;

public class ResultadoPrueba {
    protected String tipus;
    protected boolean pasa;

    public ResultadoPrueba(String tipus) {
        this.tipus = tipus;
    }

    public String getTipus() {
        return tipus;
    }

    public ResultadoPrueba setPasa(boolean pasa) {
        this.pasa=pasa;
        return this;
    }

    public double getExpresio() {
        return 0;
    }

    public double getLog() {
        return 0;
    }

    public ArrayList<Integer> getResultats() {
        return null;
    }

    public String getAprobados() {
        return null;
    }

    public boolean getPasa() {
        return pasa;
    }

    public String getTotales() {
        return null;
    }
}
