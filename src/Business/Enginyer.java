package Business;

/**
 * Subclase Ingeniero
 */
public class Enginyer extends Jugador{

    /**
     * Constructor para jugadores de tipo ingeniero
     * @param nom Nombre del jugador
     * @param PI Numero de PIs iniciales
     */
    public Enginyer(String nom, int PI) {
        super(nom, PI, "Enginyer");
    }

    @Override
    public boolean esMaster() {
        return false;
    }

    @Override
    public boolean esEnginyer(){
        return true;
    }

    @Override
    public boolean esDoctor() {
        return false;
    }

    @Override
    public String getTipusJugador() {
        return "Enginyer";
    }
}
