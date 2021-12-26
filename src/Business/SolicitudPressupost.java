package Business;

import java.util.ArrayList;

public class SolicitudPressupost extends Prova{
    private String nomEntitat;
    private double quantitat;

    public SolicitudPressupost(String nomProva, String tipus, String nomEntitat, double quantitat) {
        super(nomProva, tipus);
        this.nomEntitat = nomEntitat;
        this.quantitat = quantitat;
    }

    @Override
    public ResultadoPrueba ejecutarPrueba() {
        return new ResultadoSolicitutPresupost(Math.log(quantitat)/Math.log(2));
    }
}
