package Business;

import java.util.ArrayList;
import java.util.Iterator;

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

    public ArrayList<ResultadoPrueba> ejecutarPrueba(){
        int c=0;
        ArrayList<ResultadoPrueba> resultados = new ArrayList<>();
        //ArrayList<Jugador> jugadors=jm.getJugadors();
        Prova pActual = pm.getProva(ultimaProva);
        switch (pActual.tipus){
            case "Publicacio":
            case "Master":
                for(int i=0;i<this.numJugadors;i++){
                    resultados.add(pActual.ejecutarPrueba());
                    jm.actualizaPI(i, resultados.get(i));
                    resultados.get(i).setPasa(jm.getPIJugador(i)<1);
                }
                break;
            case "Tesis":
                for (int i = 0; i < numJugadors; i++) {
                    ResultadoPrueba r = pActual.ejecutarPrueba();
                    r.setPasa(r.getExpresio()<jm.getPIJugador(i));
                    resultados.add(pActual.ejecutarPrueba());
                    jm.actualizaPI(i, resultados.get(i));
                }
                break;
            case "Presupost":
                int sumPI=0;
                for (int i = 0; i < numJugadors; i++) {
                    sumPI+=jm.getPIJugador(i);
                }
                ResultadoPrueba r= pActual.ejecutarPrueba();
                r.pasa=sumPI>r.getLog();
                resultados.add(r);
                for (int i = 0; i < numJugadors; i++) {
                    jm.actualizaPI(i, r);
                }
                break;

        }
        /*

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

         */
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

    public Iterator<Jugador> subirLvl() {
        return jm.subirLvl();
    }
}
