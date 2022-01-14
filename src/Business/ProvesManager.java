package Business;

import Business.Proves.*;
import Persistance.DAOProva;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase para la gestion de pruebas
 */
public class ProvesManager {
    private ArrayList<Prova> proves;
    private DAOProva daoProva;

    /**
     * Constructor del manager de pruebas que utiliza el compositor
     * @param daoProva Dao que se utilizara para la persistencia de las pruebas que gestiona este manager
     */
    public ProvesManager(DAOProva daoProva) {
        this.daoProva = daoProva;
        this.proves= daoProva.llegeixProves();
    }
    //constructor para el manager de pruebas de la edicion (sin dao)

    /**
     * Construcor para el manager de pruebas de las ediciones
     * @param proves Array con las pruebas que gestionara el manager
     */
    public ProvesManager(ArrayList<Prova> proves) {
        this.proves = proves;
        daoProva =null;
    }

    /**
     * Metodo para indicar que una prueba esta siendo usada
     * @param i Posicion de la prueba a modificar
     */
    public void setToUse(int i){
        proves.get(i).usada();
    }

    /**
     * Metodo para comprobar si el manager esta vacio
     * @return True si esta vacio, false en caso contrario
     */
    public boolean noHayPruebas(){
        return proves.isEmpty();
    }

    /**
     * Getter de una prueba que se encuentra en la posicion deseada
     * @param i Posicion en el array de pruebas
     * @return Prueba en la posicion i
     */
    public Prova getProva(int i){
        return proves.get(i);
    }

    /**
     * Metodo para comprobar si una prueba existe
     * @param nom Nombre de la prueba que se quiere comprobar
     * @return True si existe, false en caso contrario
     */
    public boolean existeixProva(String nom){
        Iterator<Prova> it = proves.iterator();
        while (it.hasNext()){
            if(it.next().equals(nom)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para comprobar si un quartil es valido
     * @param quartil Quartil a comprobar
     * @return True si es valido, false en caso contrario
     */
    public boolean comprobaQuartil(String quartil){
        return quartil.equals("Q1") || quartil.equals("Q2") || quartil.equals("Q3") || quartil.equals("Q4");
    }

    /**
     * Metodo para comprobar si una probabilidad es valida
     * @param prob Probabilidad a comprobar
     * @return True si es valida, false en caso contrario
     */
    public boolean comprobaProb(int prob){
        return prob<=100 && prob>=0;
    }

    /**
     * Metodo para guardar guardar las pruebas en persistencia
     */
    public void guardarPruebas(){
        daoProva.guardaProves(proves);
    }

    /**
     * Metodo para crear una prueba del tipo Publicacion
     * @param trialName Nombre de la prueba
     * @param journalName Nombre de la revista a la que pertenece
     * @param journalQuartile Tipo de quartil
     * @param acceptanceProb Probabilidad de ser aceptada
     * @param revisionProb Probabilidad de ser revisada
     * @param rejectionProb Probabilidad de ser rechazada
     */
    public void creaPaperPubli(String trialName, String journalName, String journalQuartile, int acceptanceProb, int revisionProb, int rejectionProb) {
        proves.add(new PublicacioArticle(trialName, journalName, journalQuartile, acceptanceProb, rejectionProb, rejectionProb));
    }

    /**
     * Metodo para crear una prueba del tipo estudio de master
     * @param trialName Nombre de la prueba
     * @param masterName Nombre del master
     * @param numCred Numero de creditos
     * @param probAprova Probabilidad de aprobar un credito
     */
    public void creaMaster(String trialName, String masterName, int numCred, int probAprova){
        proves.add(new EstudiMaster(trialName, "EstudiMaster", masterName, numCred, probAprova));
    }

    /**
     * Getter de las pruebas del manager
     * @return
     */
    public ArrayList<Prova> llistaProves() {
        return proves;
    }

    /**
     * Metodo para eliminar una prueba del array de pruebas del manager en funcion de su posicion
     * @param i Posicion de la prueba que se quiere eliminar
     */
    public void eliminaProva(int i) {
        if(!proves.get(i).isUs()){
            proves.remove(i);
        }
    }

    /**
     * Getter de los nombres de las pruebas del manager
     * @return
     */
    public ArrayList<String> nomsProves(){
        ArrayList<String> r=new ArrayList<>();
        for (Prova p:proves) {
            r.add(p.getNomProva());
        }
        return r;
    }

    /**
     * Metodo para copiar el manager de pruebas
     * @return Manager copiado
     */
    public ProvesManager copia() {
        return new ProvesManager(this.proves);
    }

    /**
     * Metodo para añadir una prueba al manager
     * @param p Prueba a añadir
     */
    public void addProva(Prova p) {
        p.usada();
        proves.add(p);
    }

    /**
     * Getter de las pruebas del manager
     * @return Array con todas las pruebas del manager
     */
    public ArrayList<Prova> getProves() {
        return proves;
    }

    /**
     * Getter del nombre de una prueba en funcion de su posicion
     * @param ultimaProva Posicion de la prueba a retornar
     * @return Nombre de la prueba en la posicion ultimaProva
     */
    public String getNomProvaActual(int ultimaProva) {
        return proves.get(ultimaProva).getNomProva();
    }

    /**
     * Metodo para comprobar si una prueba esta en uso
     * @param i Posicion de la prueba que se quiere comprobar
     * @return True si esta en uso, fale en cso contrario
     */
    public boolean isInUse(int i) {
        return proves.get(i).isUs();
    }

    /**
     * Getter del nombre de una prueba en funcion de su posicion en el array de pruebas
     * @param i Posicion de la prueba de la que se quiere saber el nombre
     * @return Nombre de la prueba en la posicion i
     */
    public String getNomProva(int i) {
        return proves.get(i).getNomProva();
    }

    /**
     * Getter del tipo de prueba en funcion de su posicion en el array de pruebas
     * @param i Posicion de la prueba
     * @return Tipo de la prueba i
     */
    public String getTipusProva(int i) {
        return proves.get(i).getTipus();
    }

    /**
     * Metodo para crear una prueba del tipo Tesis
     * @param trialName Nombre de la prueba
     * @param field Campo de la tesis
     * @param diff Dificultad del jurado
     */
    public void creaTesis(String trialName, String field, int diff) {
        proves.add(new TesiDoctoral(trialName, "Tesis", field, diff));
    }

    /**
     * Metodo para crear una prueba del tipo solocitur de presupuesto
     * @param trialName Nombre de la prueb
     * @param entity Entidad a la que se pide
     * @param budget Cantidad a pedir
     */
    public void creaPressupost(String trialName, String entity, int budget) {
        proves.add(new SolicitudPressupost(trialName, entity, budget));
    }
}
