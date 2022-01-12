package Business;

public class Enginyer extends Jugador{
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
