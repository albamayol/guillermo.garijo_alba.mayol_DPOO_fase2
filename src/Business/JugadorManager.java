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
}
