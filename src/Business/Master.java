package Business;

public class Master extends Jugador {
    public Master(String nom, int PI, int id) {
        super(nom, PI, id, "Master");
    }
    @Override
    public boolean esMaster(){
        return true;
    }

    @Override
    public boolean esEnginyer() {
        return false;
    }
}
