package Business;

public class Jugador {
    private String nom;
    private int PI;
    private int id;

    public Jugador(String nom, int PI, int id) {
        this.nom = nom;
        this.PI = PI;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toCSV(){
        return "" + this.id + "," + this.nom + "," + this.PI;
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
}
