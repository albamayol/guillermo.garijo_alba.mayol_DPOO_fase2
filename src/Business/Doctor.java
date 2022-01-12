package Business;

public class Doctor extends Jugador {

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
