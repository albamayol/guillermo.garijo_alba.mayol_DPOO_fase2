package Business.Resultados;

/**
 * Subclase de Resultado de tipo ResultadoTesis
 */
public class ResultadoTesis extends ResultadoPrueba{
    private double expresio;

    /**
     * Constructor general de ResultadoTesis
     * @param ex Valor de la expresion para deterimnar si se supera la prueba o no
     */
    public ResultadoTesis(double ex) {
        super("Tesis");
        this.expresio=ex;
    }

    @Override
    public double getExpresio(){
        return expresio;
    }
}
