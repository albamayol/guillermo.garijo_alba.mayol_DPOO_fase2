package Business;

import java.util.ArrayList;
import java.util.Iterator;

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

    public Iterator<Jugador> subirLvl() {
        ArrayList<Jugador> r=new ArrayList<>();
        for (Jugador j:jugadores) {
            if(j.getPI()>=10){
                if(j.esMaster()){
                    Jugador n = new Doctor(j.getNom(), 5, j.getId());
                    jugadores.add(n);
                    r.add(n);
                }else if(j.esEnginyer()){
                    Jugador n = new Master(j.getNom(), 5, j.getId());
                    jugadores.add(n);
                    r.add(n);
                }
                jugadores.remove(j);
            }
        }
        return r.iterator();
    }
}
