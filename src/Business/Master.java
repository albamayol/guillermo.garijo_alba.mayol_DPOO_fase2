package Business;

/**
 * Subclase Master
 */
public class Master extends Jugador {

    /**
     * Constructor para jugadores de tipo master
     * @param nom Nombre del jugador
     * @param PI Numero de PIs iniciales
     */
    public Master(String nom, int PI) {
        super(nom, PI, "Master");
    }
    @Override
    public boolean esMaster(){
        return true;
    }

    @Override
    public boolean esEnginyer() {
        return false;
    }

    @Override
    public boolean esDoctor() {
        return false;
    }

    @Override
    public String getTipusJugador() {
        return "Master";
    }
}
