package Business.Proves;

import Business.Resultados.ResultadoPrueba;
import Business.Resultados.ResultadoSolicitutPresupost;

public class SolicitudPressupost extends Prova{
    private String nomEntitat;
    private double quantitat;

    public SolicitudPressupost(String nomProva, String tipus, String nomEntitat, double quantitat) {
        super(nomProva, tipus);
        this.nomEntitat = nomEntitat;
        this.quantitat = quantitat;
    }

    public SolicitudPressupost(String nomProva, String tipus, boolean us, String nomEntitat, double quantitat) {
        super(nomProva, us, tipus);
        this.nomEntitat = nomEntitat;
        this.quantitat = quantitat;
    }
    //constructor para csv


    @Override
    public ResultadoPrueba ejecutarPrueba() {
        return new ResultadoSolicitutPresupost(Math.log(quantitat)/Math.log(2));
    }

    @Override
    public String getBudget() {
        return String.valueOf(quantitat);
    }

    @Override
    public String getEntity() {
        return nomEntitat;
    }
    @Override
    public String getTipus(){
        return "Pressupost";
    }
}
