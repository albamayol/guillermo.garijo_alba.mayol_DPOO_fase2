package Business.Resultados;

public class ResultadoTesis extends ResultadoPrueba{
    private double expresio;

    public ResultadoTesis(double ex) {
        super("Tesis");
        this.expresio=ex;
    }

    @Override
    public double getExpresio(){
        return expresio;
    }
}
