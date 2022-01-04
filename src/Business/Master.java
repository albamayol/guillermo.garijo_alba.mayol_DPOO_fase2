package Business;

public class Master extends Jugador {
    private String tipus;
    public Master(String nom, int PI, int id) {
        super(nom, PI, id);
        tipus="Master";
    }
    @Override
    public String getTipus(){
        return tipus;
    }
    @Override
    public boolean esMaster(){
        return true;
    }
}
