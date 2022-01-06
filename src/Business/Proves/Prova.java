package Business.Proves;

import Business.Resultados.ResultadoPrueba;

public abstract class Prova {
    protected String nomProva;
    protected boolean us;
    protected String tipus;

    public Prova(String nomProva, String tipus) {
        this.nomProva=nomProva;
        this.us=false;
        this.tipus =tipus;
    }

    public void usada(){
        this.us=true;
    }
    public boolean isUs() {
        return us;
    }
    public abstract String getTipus();
    public void setTipus(String tipus){
        this.tipus=tipus;
    }
    public String getNomProva() {
        return nomProva;
    }
    public abstract ResultadoPrueba ejecutarPrueba();

    public String getNomRevista(){
        return null;
    }

    public String getQuartil(){
        return null;
    }

    public String getProbabilitatAccepta(){
        return null;
    }

    public String getProbabilitatRevisions(){
        return null;
    }

    public String getProbabilitatRebutja(){
        return null;
    }

    public String getDifficulty(){
        return null;
    }

    public String getCredits(){
        return null;
    }

    public String getProbabilitatMaster(){
        return null;
    }

    public String getEntity(){
        return null;
    }

    public String getBudget(){
        return null;
    }

    public String getNomMaster(){
        return null;
    }

    public  String getCampEstudis(){
        return null;
    }
}
