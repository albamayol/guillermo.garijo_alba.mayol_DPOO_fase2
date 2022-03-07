package Business.Resultados;

/**
 * Subclase de Resultado de tipo ResultadoMaster
 */
public class ResultadoMaster extends ResultadoPrueba{
    private int aprobados;
    private int suspendidos;

    /**
     * Constructor general de ResultadoMaster
     * @param aprobados Numero de creditos aprobados
     * @param suspendidos Numero de creditos suspendidos
     */
    public ResultadoMaster(int aprobados, int suspendidos) {
        super("EstudiMaster");
        this.aprobados=aprobados;
        this.suspendidos=suspendidos;
        pasa=aprobados>=suspendidos;
    }

    @Override
    public String getAprobados(){
        return String.valueOf(aprobados);
    }
    @Override
    public  String getTotales(){
        return String.valueOf(aprobados+suspendidos);
    }

}
