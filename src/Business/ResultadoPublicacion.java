package Business;

import java.util.ArrayList;

public class ResultadoPublicacion extends ResultadoPrueba{

    private ArrayList<Integer> resultados;
    private String quartil;

    public ResultadoPublicacion(ArrayList<Integer> r, String s) {
        super("Publicacio");
        this.resultados=r;
        quartil=s;
    }

    @Override
    public ArrayList<Integer> getResultats(){
        return resultados;
    }
}
