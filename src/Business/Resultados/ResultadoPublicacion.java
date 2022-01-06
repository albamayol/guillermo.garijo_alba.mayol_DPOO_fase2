package Business.Resultados;

import java.util.ArrayList;

public class ResultadoPublicacion extends ResultadoPrueba{

    private ArrayList<Integer> resultados;
    private String quartil;

    public ResultadoPublicacion(ArrayList<Integer> r, String s) {
        super("Publicacio");
        this.resultados=r;
        quartil=s;
        if(r.get(r.size()-1)==1){
            this.pasa=true;
        }
    }

    @Override
    public ArrayList<Integer> getResultats(){
        return resultados;
    }
}
