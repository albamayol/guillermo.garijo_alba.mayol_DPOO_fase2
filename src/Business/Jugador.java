package Business;

public abstract class Jugador {
    private String nom;
    private int PI;
    private int id;
    private static int ids=0;
    protected String tipusJugador;

    public Jugador(String nom, int PI, int id, String tipus) {
        this.nom = nom;
        this.PI = PI;
        this.id = id;
        this.tipusJugador=tipus;
    }

    public static Jugador crearJugador(String name, int PI, String tipus){
        ids++;
        if(tipus.equals("Master")){
            return new Master(name, PI, ids);
        }else if(tipus.equals("Doctor")){
            return new Doctor(name, PI, ids);
        }else if(tipus.equals("Enginyer")){
            return new Enginyer(name, PI, ids);
        }
        return null;
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
    public String getTipus(){return tipusJugador;}

    public abstract boolean esMaster();

    public abstract boolean esEnginyer();

    public abstract boolean esDoctor();

    public abstract String getTipusJugador();

    public void setTipusJugdaor(String tipusJugador){
        this.tipusJugador=tipusJugador;
    }
}
