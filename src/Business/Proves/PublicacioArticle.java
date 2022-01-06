package Business.Proves;

import Business.Resultados.ResultadoPrueba;
import Business.Resultados.ResultadoPublicacion;

import java.util.ArrayList;
import java.util.Random;

public class PublicacioArticle extends Prova{
    private String nomRevista;
    private String quartil;
    private int probabilitatAccepta;
    private int probabilitatRevisions;
    private int probabilitatRebutja;


    public PublicacioArticle(String nomProva, String nomRevista, String quartil, int probabilitatAccepta, int probabilitatRevisions, int probabilitatRebutja, String tipus, boolean us) {
        super(nomProva, tipus);
        this.nomRevista = nomRevista;
        this.quartil = quartil;
        this.probabilitatAccepta = probabilitatAccepta;
        this.probabilitatRevisions = probabilitatRevisions;
        this.probabilitatRebutja = probabilitatRebutja;
    }

    @Override
    public ResultadoPrueba ejecutarPrueba(){
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
        return new ResultadoPublicacion(resultados, this.quartil);
    }

    public String getProbabilitatAccepta() {
        return String.valueOf(probabilitatAccepta);
    }

    public String getProbabilitatRebutja() {
        return String.valueOf(probabilitatRebutja);
    }


    public String getProbabilitatRevisions() {
        return String.valueOf(probabilitatRevisions);
    }


    public String getNomRevista() {
        return nomRevista;
    }

    public String getQuartil() {
        return quartil;
    }

    @Override
    public String getTipus(){
        return "Publication";
    }



}
