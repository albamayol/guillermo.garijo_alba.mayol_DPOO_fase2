package Business;

public class Master extends Jugador {
    public Master(String nom, int PI, int id) {
        super(nom, PI, id);
    }
    @Override
    public String getTipus(){
        return "Master";
    }
    @Override
    public boolean esMaster(){
        return true;
    }
}
