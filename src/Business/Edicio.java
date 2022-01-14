package Business;

import Business.Proves.Prova;
import Business.Resultados.ResultadoPrueba;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase para gestionar las ediciones
 */
public class Edicio {

    private int any;
    private int numJugadors;
    private int numProves;
    private ProvesManager pm;
    private JugadorManager jm;
    private int ultimaProva;

    /**
     * Constructor general para Edicio
      * @param any Año de la edicion
     * @param numInicialJugadors Numero inicial de jugadores
     * @param numProves Numero de pruevas que contiene
     */
    public Edicio(int any, int numInicialJugadors, int numProves) {
        this.any = any;
        this.numJugadors = numInicialJugadors;
        this.numProves = numProves;
        this.pm=new ProvesManager(new ArrayList<>());
        this.jm=new JugadorManager(new ArrayList<>());
        this.ultimaProva=0;
    }

    /**
     * Constructor para instanciar despues de leer del CSV
     * @param any Año de la edicion
     * @param numInicialJugadors Numero inicial de jugadores
     * @param numProves Numero de pruebas que contiene
     * @param jm Manager de jugadores de la edicion
     * @param pm Manager de pruebas de la edicion
     * @param ultimaProva Ultima prueba ejecutada en la edicion
     */
    public Edicio(int any, int numInicialJugadors, int numProves, JugadorManager jm, ProvesManager pm, int ultimaProva) {
        this.any = any;
        this.numJugadors = numInicialJugadors;
        this.numProves = numProves;
        this.jm=jm;
        this.pm=pm;
        this.ultimaProva = ultimaProva;
    }

    /**
     * Constructor para copiar Ediciones
     * @param any Año de la edicion copiada
     * @param numJugadors Numero de jugadores de la edicion copiada
     * @param numProves Numero de pruebas de la edicion copiada
     * @param pm Nuevo manager de pruebas
     * @param jm Nuevo manager de jugadores
     */
    public Edicio(int any, int numJugadors, int numProves, ProvesManager pm, JugadorManager jm) {
        this.any = any;
        this.numJugadors = numJugadors;
        this.numProves = numProves;
        this.pm = pm;
        this.jm = jm;
        this.ultimaProva=0;
    }

    /**
     * Metodo para copiar ediciones
     * @param any Año de la nueva edicion
     * @param numJug Numero de jugadores de la nueva edicion
     * @return
     */
    public Edicio copiaEdicio(int any, int numJug){
        return new Edicio(any, numJug, this.numProves, pm.copia(), new JugadorManager(new ArrayList<>()));
    }

    /**
     * Metodo para comprobar si una edicion esta finalizada
     * @return True si ha acabado, false en caso contrario
     */
    public boolean acabada(){
        return ultimaProva==numProves || numJugadors==0;
    }

    /**
     * Metodo para comprobar si quedan jugadores en la edicion
     * @return True si aun quedan, false en caso contrario
     */
    public boolean hayJugadores(){
        return jm.hayJugadores();
    }

    /**
     * Metodo para ejecutar una prueba de la edicion
     * @return
     */
    public ArrayList<ResultadoPrueba> ejecutarPrueba(){
        int c=0;
        ArrayList<ResultadoPrueba> resultados = new ArrayList<>();
        //ArrayList<Jugador> jugadors=jm.getJugadors();
        Prova pActual = pm.getProva(ultimaProva);
        switch (pActual.getTipus()){
            case "Publication":
            case "EstudiMaster":
                for(int i=0;i<this.numJugadors;i++){
                    resultados.add(pActual.ejecutarPrueba());
                    jm.actualizaPI(i, resultados.get(i));
                }
                break;
            case "Tesis":
                for (int i = 0; i < numJugadors; i++) {
                    ResultadoPrueba r = pActual.ejecutarPrueba();
                    r.setPasa(r.getExpresio()<jm.getPIJugador(i));
                    resultados.add(pActual.ejecutarPrueba());
                    jm.actualizaPI(i, r);
                }
                break;
            case "Pressupost":
                int sumPI=0;
                for (int i = 0; i < numJugadors; i++) {
                    sumPI+=jm.getPIJugador(i);
                }
                ResultadoPrueba r= pActual.ejecutarPrueba();
                r.setPasa(sumPI>r.getLog());
                resultados.add(r);
                for (int i = 0; i < numJugadors; i++) {
                    jm.actualizaPI(i, r);
                }
                break;

        }
        ultimaProva++;
        return resultados;
    }

    /**
     * Metodo para comprobar si el año introducido coincide con el de la edicion
     * @param any Año a comparar
     * @return True si los años son iguales, false en caso contrario.
     */
    public boolean esEdicion(int any){
        return this.any==any;
    }

    /**
     * Metodo para añadir una prueba a la edicion
     * @param p Prueba a añadir
     */
    public void addProba(Prova p){
        pm.addProva(p);
    }

    /**
     * Metodo para añadir un jugador a la edicion
     * @param name Nombre del jugador que se desea añadir
     */
    public void addJugador(String name){
        jm.addJugador(name);
    }

    /**
     * Getter del numero de pruebas de la edicion
     * @return numProves
     */
    public int getNumProves() {
        return numProves;
    }

    /**
     * Getter del año de la edicion
     * @return any
     */
    public int getAny() {
        return any;
    }

    /**
     * Getter del numero de jugadores de la edicion
     * @return numJugadors
     */
    public int getNumJugadors() {
        return numJugadors;
    }

    /**
     * Getter de las pruebas de la edicion
     * @return Array con las pruebas de esta edicion
     */
    public ArrayList<Prova> getProves() {
        return pm.getProves();
    }

    /**
     * Getter de los jugadores de la edicion
     * @return Array con los jugadores de esta edicion
     */
    public ArrayList<Jugador> getJugadors() {
        return jm.getJugadors();
    }

    /**
     * Getter del indice de la ultima prueba ejecutada en la edicion
     * @return ultimaProva
     */
    public int getUltimaProva() {
        return ultimaProva;
    }

    /**
     * Getter del nombre de la prueba actual
     * @return Nombre de la prueba actual
     */
    public String getNomProvaActual() {
        return pm.getNomProvaActual(ultimaProva);
    }

    /**
     * Getter del nombre de una prueba en funcion de su posicion en el array de pruebas del manager de pruebas
     * @param i Posicion de la prueba
     * @return Prueba en la posicion i
     */
    public String getNomProva(int i) {
        return pm.getNomProva(i);
    }

    /**
     * Getter del tipo de prueba en una posicion concreta del array de pruebas de pruebas manager
     * @param i Posicion de la prueba
     * @return Nombre de la prueba en la posicion i
     */
    public String getTipusProva(int i) {
        return pm.getTipusProva(i);
    }

    /**
     * Metodo para eliminar de la edicion a los jugadores con PIs iguales o inferiores a 0
     */
    public void eliminados() {
        jm.eliminados();
        numJugadors=jm.getNumJugadors();
    }

    /**
     * Metodo para actualizar el tipo de jugador en funcion de sus PIs
     * @return Iterador con los jugadores que han sido actualizados.
     */
    public Iterator<Jugador> subirLvl() {
        return jm.subirLvl();
    }
}
