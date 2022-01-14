package Business;

/**
 * Subclase Doctor
 */
public class Doctor extends Jugador {

    /**
     * Constructor para jugadores de tipo doctor
     * @param nom Nombre del jugador
     * @param PI Numero de PIs iniciales
     */
    public Doctor(String nom, int PI) {
        super(nom, PI, "Doctor");
    }

    @Override
    public boolean esMaster() {
        return false;
    }

    @Override
    public boolean esEnginyer() {
        return false;
    }

    @Override
    public boolean esDoctor() {
        return true;
    }

    @Override
    public String getTipusJugador() {
        return "Doctor";
    }
}
