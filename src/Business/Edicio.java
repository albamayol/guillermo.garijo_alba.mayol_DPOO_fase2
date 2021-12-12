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

    //retorna true si aun quedan jugadores
    public boolean todosLosJugadoresEliminados(){
        if(jugadors.size()==0){
            return true;
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> ejecutarPrueba(){
        int c=0;
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<>();
        for (Jugador j:jugadors) {
            resultados.add(proves.get(ultimaProva).ejecutarPrueba());
        }
        for (ArrayList<Integer> rj:resultados) {

            for (Integer valor : rj) {
                if (valor.intValue() == 1) {
                    switch (proves.get(ultimaProva).getQuartil()){
                        case "Q1":
                            jugadors.get(c).modificaPI(4);
                            break;
                        case "Q2":
                            jugadors.get(c).modificaPI(3);
                            break;
                        case "Q3":
                            jugadors.get(c).modificaPI(2);
                            break;
                        case "Q4":
                            jugadors.get(c).modificaPI(1);
                            break;
                    }
                } else if (valor.intValue() == 2) {
                    switch (proves.get(ultimaProva).getQuartil()){
                        case "Q1":
                            jugadors.get(c).modificaPI(-5);
                            break;
                        case "Q2":
                            jugadors.get(c).modificaPI(-4);
                            break;
                        case "Q3":
                            jugadors.get(c).modificaPI(-3);
                            break;
                        case "Q4":
                            jugadors.get(c).modificaPI(-2);
                            break;
                    }
                }
            }
            c++;
        }
        for(int i=0;i<jugadors.size();i++){
            if(jugadors.get(i).pisCero()){
                jugadors.remove(i);
                resultados.get(i).add(4);
            }
        }
        ultimaProva++;
        return resultados;
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
        tmp.append("],").append((this.ultimaProva));
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

    public ArrayList<Jugador> getJugadors() {
        return jugadors;
    }

    public int getUltimaProva() {
        return ultimaProva;
    }
}
