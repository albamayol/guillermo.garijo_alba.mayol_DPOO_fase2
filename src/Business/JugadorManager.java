package Business;

import Persistance.DAOJugador;

import java.util.ArrayList;

public class JugadorManager {

    private ArrayList<Jugador> jugadores;
    private DAOJugador daoJugador;

    public JugadorManager() {
        this.daoJugador = new DAOJugador("AÑADIR EL PATH");
        jugadores= daoJugador.leerJugadores();
    }

    public void guardarJugadores(){
        ArrayList<String> jugadoresCSV =new ArrayList<>();
        for (Jugador j:jugadores) {
            jugadoresCSV.add(j.toCSV());
        }
        daoJugador.guardarJugadores(jugadoresCSV);
    }

    public DAOJugador getDaoJugador() {
        return daoJugador;
    }

    public Jugador getJugador(String name) {
        for (Jugador j:jugadores) {
            if(j.esJugador(name)){
                return j;
            }
        }
        return null;
    }


}
