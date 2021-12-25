package Business;

import java.util.ArrayList;
import java.util.Random;

public class Prova {
    private String nomProva;
    private String nomRevista;
    private String quartil;
    private int probabilitatAccepta;
    private int probabilitatRevisions;
    private int probabilitatRebutja;
    private boolean us;
    private String tipus;


    public Prova(String nomProva, String nomRevista, String quartil, int probabilitatAccepta, int probabilitatRevisions, int probabilitatRebutja, String tipus, boolean us) {
        this.nomProva = nomProva;
        this.nomRevista = nomRevista;
        this.quartil = quartil;
        this.probabilitatAccepta = probabilitatAccepta;
        this.probabilitatRevisions = probabilitatRevisions;
        this.probabilitatRebutja = probabilitatRebutja;
        this.tipus=tipus;
        this.us = us;
    }
    public void usada(){
        us=true;
    }

    public ArrayList<Integer> ejecutarPrueba(){
        boolean roa=false;
        Random r = new Random();
        ArrayList<Integer> resultados = new ArrayList<>();
        while (!roa){
            int num=r.nextInt(100);
            //se acepta
            if(num<=probabilitatAccepta){
                resultados.add(1);
                roa=true;
            }
            //se revisa
            if(num>probabilitatAccepta && num<=(probabilitatRevisions+probabilitatAccepta)){
                resultados.add(3);
            }
            //se rechaza
            if(num>(probabilitatRevisions+probabilitatAccepta) && num<=((probabilitatRevisions+probabilitatAccepta + probabilitatRebutja))){
                resultados.add(2);
                roa=true;
            }
        }
        return resultados;
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

    public boolean isUs() {
        return us;
    }

}
