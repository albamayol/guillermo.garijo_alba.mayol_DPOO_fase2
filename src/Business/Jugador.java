package Business;

/**
 * Super clase de jugadores
 */
public abstract class Jugador {
    private String nom;
    private int PI;
    protected String tipusJugador;

    /**
     * Constructor super para las subclases
     * @param nom Nombre del jugador
     * @param PI Numero de PIs iniciales
     * @param tipus Tipo de jugador
     */
    public Jugador(String nom, int PI, String tipus) {
        this.nom = nom;
        this.PI = PI;
        this.tipusJugador=tipus;
    }

    /**
     * Metodo para modificar los PIs de un jugador
     * @param mod Numero a sumar o restar de los PIs actuales (positivo para sumar, negativo para restar)
     */
    public void modificaPI(int mod){
        PI+=mod;
    }

    /**
     * Getter de los PIs actuales de un jugador
     * @return
     */
    public int getPI() {
        return PI;
    }

    /**
     * Metodo para comprobar si los PIs de un jugador han llegado a 0
     * @return True si sus PIs son iguales o menores a 0, false en caso conrtario
     */
    public boolean pisCero(){
        return this.PI<=0;
    }

    /**
     * Getter del nombre del jugador
     * @return Nombre del jugador
     */
    public String getNom() {
        return nom;
    }

    /**
     * Metodo para comprobar si un jugador es del tipo master
     * @return True si es master, false si no lo es
     */
    public abstract boolean esMaster();

    /**
     * Metodo para comprobar si un jugador es del tipo ingeniero
     * @return True si es master, false si no lo es
     */
    public abstract boolean esEnginyer();

    /**
     * Metodo para comprobar si un jugador es del tipo doctor
     * @return True si es master, false si no lo es
     */
    public abstract boolean esDoctor();

    /**
     * Getter del tipo de jugador
     * @return El tipo del jugador
     */
    public abstract String getTipusJugador();

    /**
     * Setter del tipo de jugador
     * @param tipusJugador Tipo que se quiere aplicar
     */
    public void setTipusJugdaor(String tipusJugador){
        this.tipusJugador=tipusJugador;
    }
}
