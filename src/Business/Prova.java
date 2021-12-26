package Business;

import java.util.ArrayList;
import java.util.Random;

public abstract class Prova {
    protected String nomProva;
    protected boolean us;
    protected String tipus;

    public Prova(String nomProva, String tipus) {
        this.nomProva=nomProva;
        this.us=false;
        this.tipus=tipus;
    }

    public void usada(){
        this.us=true;
    }
    public boolean isUs() {
        return us;
    }
    public String getTipus() {
        return tipus;
    }
    public String getNomProva() {
        return nomProva;
    }
    public abstract ResultadoPrueba ejecutarPrueba();

}
