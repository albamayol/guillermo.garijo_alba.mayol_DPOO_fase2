package Business;

import Persistance.DAOJugador;

import java.util.ArrayList;

public class JugadorManager {

    private ArrayList<Jugador> jugadores;


    public JugadorManager(ArrayList<Jugador> jugadors) {
        this.jugadores=jugadors;
    }
    public boolean hayJugadores() {
        return !jugadores.isEmpty();
    }
    public void addJugador(String name) {
        jugadores.add(Jugador.crearJugador(name, 5));
    }
    public ArrayList<Jugador> getJugadors() {
        return jugadores;
    }

    public void eliminados() {
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).pisCero()){
                jugadores.remove(i);
            }
        }
    }

    public int getNumJugadors() {
        return jugadores.size();
    }

    public void actualizaPI(int i, ResultadoPrueba resultadoPrueba) {
        Jugador j = jugadores.get(i);
        switch (resultadoPrueba.tipus){
            case "Publicacio":

            case "Master":
                if(resultadoPrueba.pasa){
                    if(j.esEnginyer()){
                        j.modificaPI(10-j.getPI());
                    }else{
                        j.modificaPI(3);
                    }
                }else {
                    j.modificaPI(-3);
                }
                break;
            case "Tesis":
                if(resultadoPrueba.pasa){
                    if(j.esMaster()){
                        j.modificaPI(10-j.getPI());
                    }else{
                        j.modificaPI(5);
                    }
                }else {
                    j.modificaPI(-5);
                }
                break;

            case "Presupost":
                if(resultadoPrueba.pasa){
                    j.modificaPI(j.getPI()/2);
                }else {
                    j.modificaPI(-2);
                }
        }
    }

    public int getPIJugador(int i) {
        return jugadores.get(i).getPI();
    }
}
