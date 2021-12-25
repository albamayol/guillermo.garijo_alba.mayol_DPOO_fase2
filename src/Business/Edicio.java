package Business;

import Persistance.DAOProva;

import java.util.ArrayList;

public class Edicio {

    private int any;
    private int numJugadors;
    private int numProves;
    private ProvesManager pm;
    private JugadorManager jm;
    private int ultimaProva;

    //constructor general para nueva
    public Edicio(int any, int numInicialJugadors, int numProves) {
        this.any = any;
        this.numJugadors = numInicialJugadors;
        this.numProves = numProves;
        this.pm=new ProvesManager(new ArrayList<>());
        this.jm=new JugadorManager(new ArrayList<>());
        this.ultimaProva=0;
    }//contructor para leer del csv
    public Edicio(int any, int numInicialJugadors, int numProves, ArrayList<Jugador> jugadors, ArrayList<Prova> proves, int ultimaProva) {
        this.any = any;
        this.numJugadors = numInicialJugadors;
        this.numProves = numProves;
        this.jm=new JugadorManager(jugadors);
        this.pm=new ProvesManager(proves);
        this.ultimaProva = ultimaProva;
    }
    //constructor para copiar
    public Edicio(int any, int numJugadors, int numProves, ProvesManager pm, JugadorManager jm) {
        this.any = any;
        this.numJugadors = numJugadors;
        this.numProves = numProves;
        this.pm = pm;
        this.jm = jm;
        this.ultimaProva=0;
    }


    public Edicio copiaEdicio(int any, int numJug){
        return new Edicio(any, numJug, this.numProves, pm.copia(), new JugadorManager(new ArrayList<>()));
    }
    public boolean acabada(){
        return ultimaProva==numProves;
    }
    public boolean hayJugadores(){
        return jm.hayJugadores();

    }

    public ArrayList<ArrayList<Integer>> ejecutarPrueba(){
        int c=0;
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<>();
        ArrayList<Jugador> jugadors=jm.getJugadors();
        Prova pActual = pm.getProva(ultimaProva);
        for (Jugador j:jugadors) {
            resultados.add(pActual.ejecutarPrueba());
        }
        for (ArrayList<Integer> rj:resultados) {

            for (Integer valor : rj) {
                if (valor == 1) {
                    switch (pActual.getQuartil()) {
                        case "Q1" -> jugadors.get(c).modificaPI(4);
                        case "Q2" -> jugadors.get(c).modificaPI(3);
                        case "Q3" -> jugadors.get(c).modificaPI(2);
                        case "Q4" -> jugadors.get(c).modificaPI(1);
                    }
                } else if (valor == 2) {
                    switch (pActual.getQuartil()) {
                        case "Q1" -> jugadors.get(c).modificaPI(-5);
                        case "Q2" -> jugadors.get(c).modificaPI(-4);
                        case "Q3" -> jugadors.get(c).modificaPI(-3);
                        case "Q4" -> jugadors.get(c).modificaPI(-2);
                    }
                }
            }
            c++;
        }
        ultimaProva++;
        return resultados;
    }

    public boolean esEdicion(int año){
        return this.any==año;
    }
    public void addProba(Prova p){
        pm.addProva(p);

    }
    public void addJugador(String name){
        jm.addJugador(name);
    }

    /*
    //no borrar---------------------
    public String guardarEdicion(){
        StringBuilder tmp = new StringBuilder(this.any + "," + this.numJugadors + "," + this.numProves + ",[");
        for (Prova p: proves) {
            tmp.append(p.getNomProva()).append(";");
        }
        tmp.append("],[");
        for (Jugador j: jugadors) {
            tmp.append((j.getId())).append(";");
        }
        tmp.append("],").append((this.ultimaProva)).append("\n");
        return tmp.toString();
    }
    //------------------------

     */

    public int getNumProves() {
        return numProves;
    }
    public int getAny() {
        return any;
    }
    public int getNumJugadors() {
        return numJugadors;
    }
    public ArrayList<Prova> getProves() {
        return pm.getProves();
    }
    public ArrayList<Jugador> getJugadors() {
        return jm.getJugadors();
    }
    public int getUltimaProva() {
        return ultimaProva;
    }
    public String getNomProvaActual() {
        return pm.getNomProvaActual(ultimaProva);
    }
    public String getNomProva(int i) {
        return pm.getNomProva(i);
    }
    public String getTipusProva(int i) {
        return pm.getTipusProva(i);
    }

    public void eliminados() {
        jm.eliminados();
        numJugadors=jm.getNumJugadors();
    }
}
