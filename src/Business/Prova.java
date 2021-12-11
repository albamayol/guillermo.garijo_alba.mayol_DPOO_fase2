package Business;

public class Prova {
    private String nomProva;
    private String nomRevista;
    private String quartil;
    private int probabilitatAccepta;
    private int probabilitatRevisions;
    private int probabilitatRebutja;
    private boolean us;
    private String tipus;

    public Prova(){
        this.nomProva = "";
        this.nomRevista = "";
        this.quartil = "";
        this.probabilitatAccepta = 0;
        this.probabilitatRevisions = 0;
        this.probabilitatRebutja = 0;
        this.us = false;
        this.tipus = "";
    }

    public int getProbabilitatAccepta() {
        return probabilitatAccepta;
    }

    public int getProbabilitatRebutja() {
        return probabilitatRebutja;
    }

    public int getProbabilitatRevisions() {
        return probabilitatRevisions;
    }

    public String getNomProva() {
        return nomProva;
    }

    public String getNomRevista() {
        return nomRevista;
    }

    public String getQuartil() {
        return quartil;
    }

    public String getTipus() {
        return tipus;
    }

    public void setUs(boolean us) {
        this.us = us;
    }

    public void setNomProva(String nomProva) {
        this.nomProva = nomProva;
    }

    public void setNomRevista(String nomRevista) {
        this.nomRevista = nomRevista;
    }

    public void setProbabilitatAccepta(int probabilitatAccepta) {
        this.probabilitatAccepta = probabilitatAccepta;
    }

    public void setProbabilitatRebutja(int probabilitatRebutja) {
        this.probabilitatRebutja = probabilitatRebutja;
    }

    public void setProbabilitatRevisions(int probabilitatRevisions) {
        this.probabilitatRevisions = probabilitatRevisions;
    }

    public void setQuartil(String quartil) {
        this.quartil = quartil;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
