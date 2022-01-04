package Business;

public class Enginyer extends Jugador{
    private String tipus;
    public Enginyer(String nom, int PI, int id) {
        super(nom, PI, id);
        tipus="Enginyer";
    }
    @Override
    public String getTipus(){
        return tipus;
    }
    @Override
    public boolean esEnginyer(){
        return true;
    }
}
