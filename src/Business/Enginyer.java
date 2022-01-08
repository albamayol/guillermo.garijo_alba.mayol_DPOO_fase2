package Business;

public class Enginyer extends Jugador{
    public Enginyer(String nom, int PI, int id) {
        super(nom, PI, id, "Enginyer");
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
