package Business;

public class Jugador {
    private String nom;
    private int PI;
    private int id;
    private static int ids=0;

    public Jugador(String nom, int PI, int id) {
        this.nom = nom;
        this.PI = PI;
        this.id = id;
    }

    public static Jugador crearJugador(String name, int PI){
        ids++;
        return new Jugador(name, PI, ids);
    }

    public int getId() {
        return id;
    }
    public void modificaPI(int mod){
        PI+=mod;
    }
    public int getPI() {
        return PI;
    }
    public boolean pisCero(){
        return this.PI<=0;
    }
    public boolean esJugador(String name){
        return this.nom.equals(name);
    }
    public String getNom() {
        return nom;
    }
    public String getTipus(){return null;}

    public boolean esMaster() {
        return false;
    }

    public boolean esEnginyer() {
        return false;
    }
}
