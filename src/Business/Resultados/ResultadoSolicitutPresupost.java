package Business.Resultados;

/**
 * Subclase de Resultado de tipo ResultadoSolicitudPressupost
 */
public class ResultadoSolicitutPresupost extends ResultadoPrueba{
    private double log;

    /**
     * Constructor general de ResultadoSolicitudPressupost
     * @param log Logaritmo en base 2 de la suma de los PIs de los jugadores
     */
    public ResultadoSolicitutPresupost(double log) {
        super("Presupost");
        this.log=log;
    }
    @Override
    public double getLog(){
        return log;
    }
}
