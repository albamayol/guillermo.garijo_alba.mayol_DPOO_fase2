package Business.Proves;

import Business.Resultados.ResultadoPrueba;
import Business.Resultados.ResultadoPublicacion;

import java.util.ArrayList;
import java.util.Random;

/**
 * Subclase de prueba tipo PublicacioArticle
 */
public class PublicacioArticle extends Prova{
    private String nomRevista;
    private String quartil;
    private int probabilitatAccepta;
    private int probabilitatRevisions;
    private int probabilitatRebutja;

    /**
     * Constructor general para una PublicacioArticle
     * @param nomProva Nombre de la prueba
     * @param nomRevista Nombre de la revista
     * @param quartil Quartil del articulo
     * @param probabilitatAccepta Probabilidad de ser aceptado
     * @param probabilitatRevisions Probabilidad de ser revisado
     * @param probabilitatRebutja Probabilidad de ser rechazado
     */
    public PublicacioArticle(String nomProva, String nomRevista, String quartil, int probabilitatAccepta, int probabilitatRevisions, int probabilitatRebutja) {
        super(nomProva, "Publication");
        this.nomRevista = nomRevista;
        this.quartil = quartil;
        this.probabilitatAccepta = probabilitatAccepta;
        this.probabilitatRevisions = probabilitatRevisions;
        this.probabilitatRebutja = probabilitatRebutja;
    }

    /**
     * Constructor para el dao
     * @param nomProva Nombre de la prueba
     * @param tipus Tipo de prueba
     * @param us Uso de la prueba
     * @param nomRevista Nombre de la revista
     * @param quartil Quartil del articulo
     * @param probabilitatAccepta Probabilidad de ser aceptado
     * @param probabilitatRevisions Probabilidad de ser revisado
     * @param probabilitatRebutja Probabilidad de ser rechazado
     */
    public PublicacioArticle(String nomProva, String tipus, boolean us, String nomRevista, String quartil, int probabilitatAccepta, int probabilitatRevisions, int probabilitatRebutja) {
        super(nomProva, us, tipus);
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

    @Override
    public String getProbabilitatAccepta() {
        return String.valueOf(probabilitatAccepta);
    }

    @Override
    public String getProbabilitatRebutja() {
        return String.valueOf(probabilitatRebutja);
    }

    @Override
    public String getProbabilitatRevisions() {
        return String.valueOf(probabilitatRevisions);
    }

    @Override
    public String getNomRevista() {
        return nomRevista;
    }
    @Override
    public String getQuartil() {
        return quartil;
    }

    @Override
    public String getTipus(){
        return "Publication";
    }



}
