package Business.Proves;

import Business.Resultados.ResultadoPrueba;
import Business.Resultados.ResultadoSolicitutPresupost;

/**
 * Subclase de prueba tipo SolicitudPressupost
 */
public class SolicitudPressupost extends Prova{
    private String nomEntitat;
    private double quantitat;

    /**
     * Constructor general para SolicitudPressupost
     * @param nomProva Nombre de la prueba
     * @param nomEntitat Nombre de la entidad a solicitar
     * @param quantitat Cantidad a solicitar
     */
    public SolicitudPressupost(String nomProva, String nomEntitat, double quantitat) {
        super(nomProva, "Pressupost");
        this.nomEntitat = nomEntitat;
        this.quantitat = quantitat;
    }

    /**
     * Constructor para el csv
     * @param nomProva Nombre de la prueba
     * @param tipus Tipo de prueba
     * @param us Uso de la prueba
     * @param nomEntitat Nombre de la entidad a solicitar
     * @param quantitat Cantidad a solicitar
     */
    public SolicitudPressupost(String nomProva, String tipus, boolean us, String nomEntitat, double quantitat) {
        super(nomProva, us, tipus);
        this.nomEntitat = nomEntitat;
        this.quantitat = quantitat;
    }


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
