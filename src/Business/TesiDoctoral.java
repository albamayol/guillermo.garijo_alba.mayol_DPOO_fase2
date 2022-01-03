package Business;

import java.util.ArrayList;

public class TesiDoctoral extends Prova{
    private String campEstudis;
    private int dificultat;

    public TesiDoctoral(String nomProva, String tipus, String campEstudis, int dificultat) {
        super(nomProva, tipus);
        this.campEstudis = campEstudis;
        this.dificultat = dificultat;
    }

    @Override
    public ResultadoPrueba ejecutarPrueba() {
        int sum=0;
        for(int i=0;i<dificultat;i++){
            sum+=2*i-1;
        }
        return new ResultadoTesis(Math.sqrt(sum));
    }

    @Override
    public String getDifficulty(){
        return String.valueOf(dificultat);
    }
    @Override
    public String getCampEstudis(){
        return campEstudis;
    }
}
