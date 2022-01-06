package Business;

public class Doctor extends Jugador {

    public Doctor(String nom, int PI, int id) {
        super(nom, PI, id, "Doctor");
    }

    @Override
    public boolean esMaster() {
        return false;
    }

    @Override
    public boolean esEnginyer() {
        return false;
    }
}
