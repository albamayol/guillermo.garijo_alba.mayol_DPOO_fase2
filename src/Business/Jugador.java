package Business;

public class Jugador {
    private String nom;
    private int PI;
    private int id;

    public void incrementaPI() {
        this.PI++;
    }

    public void disminueixPI() {
        this.PI--;
    }

    public int getPI() {
        return PI;
    }

    public boolean esJugador(String name){
        return this.nom.equals(name);
    }
}
