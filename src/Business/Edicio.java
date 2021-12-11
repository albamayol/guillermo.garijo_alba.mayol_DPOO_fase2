package Business;

import java.util.ArrayList;

public class Edicio implements Cloneable {
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

    @Override
    public Edicio clone(){
        return new Edicio(this.any, this.numInicialJugadors, this.numProves, (ArrayList<Jugador>) this.jugadors.clone(), (ArrayList<Prova>) this.proves.clone(), 0);
    }

    public String edicionToCSV(){
        String tmp =  this.any + "," + this.numInicialJugadors + "," + this.numProves + ",[";
        for (Prova p: proves) {
            tmp+= p.getNomProva()+";";
        }
        tmp+="],[";
        for (Jugador j: jugadors) {
            tmp+=String.valueOf(j.getId()) + ";";
        }
        tmp+="]" + String.valueOf(this.ultimaProva);
        return tmp;
    }

}
