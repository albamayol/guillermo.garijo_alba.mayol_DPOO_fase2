package Business.Proves;

import Business.Resultados.ResultadoMaster;
import Business.Resultados.ResultadoPrueba;

import java.util.Random;

public class EstudiMaster extends Prova{
    private String nomMaster;
    private int numCredits;
    private  int probAprovaCredit;

    public EstudiMaster(String nomProva, String tipus, String nomMaster, int numCredits, int probAprovaCredit) {
        super(nomProva, tipus);
        this.nomMaster = nomMaster;
        this.numCredits = numCredits;
        this.probAprovaCredit = probAprovaCredit;
    }

    @Override
    public ResultadoPrueba ejecutarPrueba() {
        Random r = new Random();
        Integer aprobados=0;
        Integer suspendidos=0;
        for(int i=0;i<numCredits;i++){
            int rnd=r.nextInt(100);
            if(rnd<=probAprovaCredit){
                aprobados++;
            }else {
                suspendidos++;
            }
        }
        Integer ok=-1;
        if(aprobados>suspendidos){
            ok=1;
        }else {
            ok=0;
        }
        return new ResultadoMaster(aprobados, suspendidos);
    }

    @Override
    public String getProbabilitatMaster(){
        return String.valueOf(probAprovaCredit);
    }
    @Override
    public String getNomMaster(){
        return nomMaster;
    }

    @Override
    public String getCredits() {
        return String.valueOf(numCredits);
    }
    @Override
    public String getTipus(){
        return "EstudiMaster";
    }
}
