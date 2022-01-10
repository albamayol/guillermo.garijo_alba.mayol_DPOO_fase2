package Business.Resultados;

public class ResultadoMaster extends ResultadoPrueba{
    private int aprobados;
    private int suspendidos;

    public ResultadoMaster(int aprobados, int suspendidos) {
        super("EstudiMaster");
        this.aprobados=aprobados;
        this.suspendidos=suspendidos;
        pasa=aprobados>suspendidos;
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
