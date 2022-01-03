package Business;

public class Doctor extends Jugador {

    public Doctor(String nom, int PI, int id) {
        super(nom, PI, id);
    }

    @Override
    public String getTipus(){
        return "Doctor";
    }
}
