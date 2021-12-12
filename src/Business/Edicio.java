package Business;

import java.util.ArrayList;

public class Edicio {
    private int any;
    private int numInicialJugadors;
    private int numProves;
    private ArrayList<Jugador> jugadors;
    private ArrayList<Prova> proves;
    private int ultimaProva;

    public Edicio(int any, int numInicialJugadors, int numProves, ArrayList<Jugador> jugadors, ArrayList<Prova> proves) {
        this.any = any;
        this.numInicialJugadors = numInicialJugadors;
        this.numProves = numProves;
        this.jugadors = jugadors;
        this.proves = proves;
        this.ultimaProva = 0;
    }
    public Edicio(int any, int numInicialJugadors, int numProves, ArrayList<Jugador> jugadors, ArrayList<Prova> proves, int ultimaProva) {
        this.any = any;
        this.numInicialJugadors = numInicialJugadors;
        this.numProves = numProves;
        this.jugadors = jugadors;
        this.proves = proves;
        this.ultimaProva = ultimaProva;
    }

    public boolean esEdicion(int año){
        return this.any==año;
    }


    public void addProba(Prova p){
        proves.add(p);
    }

    public void addJugador(Jugador j){
        jugadors.add(j);
    }

    public Edicio clone(int año, int numJug){
        return new Edicio(año, numJug, this.numProves, (ArrayList<Jugador>) this.jugadors.clone(), (ArrayList<Prova>) this.proves.clone(), 0);
    }

    public String edicionToCSV(){
        StringBuilder tmp = new StringBuilder(this.any + "," + this.numInicialJugadors + "," + this.numProves + ",[");
        for (Prova p: proves) {
            tmp.append(p.getNomProva()).append(";");
        }
        tmp.append("],[");
        for (Jugador j: jugadors) {
            tmp.append((j.getId())).append(";");
        }
        tmp.append("]").append((this.ultimaProva));
        return tmp.toString();
    }

    public int getNumProves() {
        return numProves;
    }

    public int getAny() {
        return any;
    }

    public int getNumInicialJugadors() {
        return numInicialJugadors;
    }

    public ArrayList<Prova> getProves() {
        return proves;
    }
}
