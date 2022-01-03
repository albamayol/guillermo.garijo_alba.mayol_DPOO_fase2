package Business;

public class Enginyer extends Jugador{
    public Enginyer(String nom, int PI, int id) {
        super(nom, PI, id);
    }
    @Override
    public String getTipus(){
        return "";
    }
    @Override
    public boolean esEnginyer(){
        return true;
    }
}
