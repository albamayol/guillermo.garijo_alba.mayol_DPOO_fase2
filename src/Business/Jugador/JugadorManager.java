package Business.Jugador;

import Business.Resultados.ResultadoPrueba;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase para la gestion de jugadores
 */
public class JugadorManager {

    private ArrayList<Jugador> jugadores;


    /**
     * Constructor para el manager de jugadores
     * @param jugadors Array de jugadores que se quiere gestionar
     */
    public JugadorManager(ArrayList<Jugador> jugadors) {
        this.jugadores=jugadors;
    }

    /**
     * Metodo para comprobar si el manager de jugadores esta vacio
     * @return True si todavia quedan jugadores, false en caso contrario
     */
    public boolean hayJugadores() {
        return !jugadores.isEmpty();
    }

    /**
     * Metodo para añadir un nuevo jugador al manager
     * @param name Nombre del jugador que se quiere añadir
     */
    public void addJugador(String name) {
        jugadores.add(new Enginyer(name, 5));
    }

    /**
     * Getter del array de jugadores que gestiona el manager
     * @return
     */
    public ArrayList<Jugador> getJugadors() {
        return jugadores;
    }

    /**
     * Elimina del array de jugadores aquellos jugadores con PIs iguales o inferiores a 0
     */
    public void eliminados() {
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).pisCero()){
                jugadores.remove(i);
                i--;
            }
        }
    }

    /**
     * Getter del numero de jugadores que gestiona el manager
     * @return
     */
    public int getNumJugadors() {
        return jugadores.size();
    }

    /**
     * Metodo para actualizar los PIs de los jugadores en funcion de los resultados obtenidos en una prueba
     * @param i Posicion del jugador a modificar
     * @param resultadoPrueba Resultados del jugador i
     */
    public void actualizaPI(int i, ResultadoPrueba resultadoPrueba) {
        Jugador j = jugadores.get(i);
        switch (resultadoPrueba.getTipus()){
            case "Publicacio":
                if(resultadoPrueba.getPasa()){
                    if(j.esDoctor()){
                        j.modificaPI(resultadoPrueba.getPIPublicacio()*2);
                    }else {
                        j.modificaPI(resultadoPrueba.getPIPublicacio());
                    }
                }else {
                    if(j.esMaster()||j.esDoctor()){
                        j.modificaPI(-((resultadoPrueba.getPIPublicacio()+1)/2));
                    }else {
                        j.modificaPI(-(resultadoPrueba.getPIPublicacio()+1));
                    }
                }
                break;
            case "EstudiMaster":
                if(resultadoPrueba.getPasa()){
                    if(j.esEnginyer()){
                        j.modificaPI(10-j.getPI());
                    }else{
                        if(j.esDoctor()){
                            j.modificaPI(6);
                        }else {
                            j.modificaPI(3);
                        }
                    }
                }else {
                    if(j.esMaster()||j.esDoctor()){
                        j.modificaPI(-1);
                    }else {
                        j.modificaPI(-3);
                    }

                }
                break;
            case "Tesis":
                if(resultadoPrueba.getPasa()){
                    if(j.esMaster()){
                        j.modificaPI(10-j.getPI());
                    }else{
                        if(j.esDoctor()){
                            j.modificaPI(10);
                        }else {
                            j.modificaPI(5);
                        }
                    }
                }else {
                    if(j.esMaster()||j.esDoctor()){
                        j.modificaPI(-2);
                    }else {
                        j.modificaPI(-5);
                    }

                }
                break;

            case "Presupost":
                if(resultadoPrueba.getPasa()){
                    if(j.esDoctor()){
                        j.modificaPI((j.getPI()/2)*2);
                    }else {
                        j.modificaPI(j.getPI()/2);
                    }
                }else {
                    if(j.esDoctor()||j.esMaster()){
                        j.modificaPI(-1);
                    }else{
                        j.modificaPI(-2);
                    }
                }
        }
    }

    /**
     * Getter de los PIs del jugador en la posicion i del array de jugadores
     * @param i Posicion del jugador en el array de jugadores
     * @return PIs del jugador i
     */
    public int getPIJugador(int i) {
        return jugadores.get(i).getPI();
    }

    /**
     * Metodo para aumentar la categoria de los jugadores en funcion de sus PIs
     * @return Iterador del array de jugadores actualizado
     */
    public Iterator<Jugador> subirLvl() {
        ArrayList<Jugador> r=new ArrayList<>();
        for (int i=0;i<jugadores.size();i++) {
            Jugador j = jugadores.get(i);
            if(j.getPI()>=10){
                if(j.esMaster()){
                    Jugador n = new Doctor(j.getNom(), 5);
                    //jugadores.add(n);
                    r.add(n);
                }else if(j.esEnginyer()){
                    Jugador n = new Master(j.getNom(), 5);
                    //jugadores.add(n);
                    r.add(n);
                }
                jugadores.remove(i);
                i--;
            }
        }
        jugadores.addAll(r);
        return r.iterator();
    }
}
