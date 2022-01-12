package Business;

public abstract class Jugador {
    private String nom;
    private int PI;
    protected String tipusJugador;

    public Jugador(String nom, int PI, String tipus) {
        this.nom = nom;
        this.PI = PI;
        this.tipusJugador=tipus;
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
    public String getNom() {
        return nom;
    }
    public String getTipus(){return tipusJugador;}
    public abstract boolean esMaster();
    public abstract boolean esEnginyer();
    public abstract boolean esDoctor();
    public abstract String getTipusJugador();
    public void setTipusJugdaor(String tipusJugador){
        this.tipusJugador=tipusJugador;
    }
}
