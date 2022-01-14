package Business.Proves;

import Business.Resultados.ResultadoPrueba;


/**
 * Super clase de las pruebas
 */
public abstract class Prova {
    protected String nomProva;
    protected boolean us;
    protected String tipus;

    /**
     * Constructor super de las pruebas
     * @param nomProva Nombre de la prueba
     * @param tipus Tipo de prueba
     */
    public Prova(String nomProva, String tipus) {
        this.nomProva=nomProva;
        this.us=false;
        this.tipus =tipus;
    }

    /**
     * Constructor utilizado por los daos csv
     * @param nomProva Nombre de la prueba
     * @param us Uso de la prueba
     * @param tipus Tipo de prueba
     */
    public Prova(String nomProva, boolean us, String tipus) {
        this.nomProva = nomProva;
        this.us = us;
        this.tipus = tipus;
    }

    /**
     * Setter del uso de una prueba
     */
    public void usada(){
        this.us=true;
    }

    /**
     *  Getter del uso de una prueba
     * @return us
     */
    public boolean isUs() {
        return us;
    }

    /**
     * Getter del tipo de prueba
     * @return El tipo de la prueba
     */
    public abstract String getTipus();

    /**
     * Setter del tipo de la prueba
     * @param tipus Tipo a definir
     */
    public void setTipus(String tipus){
        this.tipus=tipus;
    }

    /**
     * Getter del nombre de la prueba
     * @return nomProva
     */
    public String getNomProva() {
        return nomProva;
    }

    /**
     * Metodo para ejecutar una prueba
     * @return Los resultados de la prueba
     */
    public abstract ResultadoPrueba ejecutarPrueba();

    /**
     * Getter del nombre de la revista de publicacion
     * @return nomRevista
     */
    public String getNomRevista(){
        return null;
    }

    /**
     * Getter del quartil de la publicacion
     * @return quartil
     */
    public String getQuartil(){
        return null;
    }

    /**
     * Getter de la probabilidad de aceptacion de una publicacion
     * @return probabilitatAccepta
     */
    public String getProbabilitatAccepta(){
        return null;
    }

    /**
     * Getter de la probabilidad de revision de una publicacion
     * @return probabilitatRevisa
     */
    public String getProbabilitatRevisions(){
        return null;
    }

    /**
     * Getter de la probabilidad de rechazo de una publicacion
     * @return probabilitatRebutja
     */
    public String getProbabilitatRebutja(){
        return null;
    }

    /**
     * Getter de la dificultad de un estudi de master
     * @return difficulty
     */
    public String getDifficulty(){
        return null;
    }

    /**
     * Getter de la dificultad del jurado de una tesis
     * @return numCredits
     */
    public String getCredits(){
        return null;
    }

    /**
     * Getter de la probabilidad de aprobar un credito del master
     * @return probAprobaCredit
     */
    public String getProbabilitatMaster(){
        return null;
    }

    /**
     * Getter de la entidad de una solicitud de presupuesto
     * @return entity
     */
    public String getEntity(){
        return null;
    }

    /**
     * Getter de la cantidad de presupuesto solicitada
     * @return budget
     */
    public String getBudget(){
        return null;
    }

    /**
     * Getter del nombre del master
     * @return nomMaster
     */
    public String getNomMaster(){
        return null;
    }

    /**
     * Getter del campo de estudios de una tesis
     * @return campEstudis
     */
    public  String getCampEstudis(){
        return null;
    }
}
