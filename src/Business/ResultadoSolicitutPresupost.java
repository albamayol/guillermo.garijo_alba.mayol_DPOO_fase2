package Business;

public class ResultadoSolicitutPresupost extends ResultadoPrueba{
    private double log;

    public ResultadoSolicitutPresupost(double log) {
        super("Presupost");
        this.log=log;
    }
    @Override
    public double getLog(){
        return log;
    }
}
