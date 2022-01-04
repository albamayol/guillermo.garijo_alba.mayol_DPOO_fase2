package Business;

public class Doctor extends Jugador {
    private String tipus;

    public Doctor(String nom, int PI, int id) {
        super(nom, PI, id);
        tipus="Doctor";
    }

    @Override
    public String getTipus(){
        return tipus;
    }
}
