package Business;

import java.util.ArrayList;

public class JugadorManager {

    private ArrayList<Jugador> jugadores;

    public Jugador getJugador(String name) {
        for (Jugador j:jugadores) {
            if(j.esJugador(name)){
                return j;
            }
        }
        return null;
    }
}
