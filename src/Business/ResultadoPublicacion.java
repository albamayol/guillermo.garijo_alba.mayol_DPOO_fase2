package Business;

import java.util.ArrayList;

public class ResultadoPublicacion extends ResultadoPrueba{

    private ArrayList<Integer> resultados;

    public ResultadoPublicacion(ArrayList<Integer> r) {
        super("Publicacio");
        this.resultados=r;
    }
}
