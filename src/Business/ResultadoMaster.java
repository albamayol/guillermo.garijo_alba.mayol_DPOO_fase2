package Business;

public class ResultadoMaster extends ResultadoPrueba{
    private int aprobados;
    private int suspendidos;

    public ResultadoMaster(int aprobados, int suspendidos) {
        super("Master");
        this.aprobados=aprobados;
        this.suspendidos=suspendidos;
        pasa=aprobados>suspendidos;
    }

}
