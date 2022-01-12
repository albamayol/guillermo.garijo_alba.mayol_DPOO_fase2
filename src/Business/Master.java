package Business;

public class Master extends Jugador {
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
