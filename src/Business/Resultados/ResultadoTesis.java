package Business.Resultados;

public class ResultadoTesis extends ResultadoPrueba{
    private double expresio;

    public ResultadoTesis(double ex) {
        super("Tesis");
        this.expresio=ex;
    }

    public ResultadoPrueba comprueba(int PI){
        pasa=PI>expresio;
        return this;
    }
    @Override
    public double getExpresio(){
        return expresio;
    }
}
