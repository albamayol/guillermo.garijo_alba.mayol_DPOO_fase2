package Business.Proves;

import Business.Resultados.ResultadoPrueba;
import Business.Resultados.ResultadoTesis;

/**
 * Subclase de prueba tipo TesiDoctoral
 */
public class TesiDoctoral extends Prova{
    private String campEstudis;
    private int dificultat;

    /**
     * Construcotr general de TesiDoctoral
     * @param nomProva Nombre de la prueba
     * @param tipus Tipo de la prueba
     * @param campEstudis Campo de estudios de la tesis
     * @param dificultat Dificultad del jurado de la tesis
     */
    public TesiDoctoral(String nomProva, String tipus, String campEstudis, int dificultat) {
        super(nomProva, tipus);
        this.campEstudis = campEstudis;
        this.dificultat = dificultat;
    }

    /**
     * Constructor para el daoCSV
     * @param nomProva Nombre de la prueba
     * @param tipus Tipo de prueba
     * @param us Uso de la prueba
     * @param campEstudis Campo de estudios de la tesis
     * @param dificultat Dificultad del jurado de la tesis
     */
    public TesiDoctoral(String nomProva, String tipus, boolean us, String campEstudis, int dificultat) {
        super(nomProva, us, tipus);
        this.campEstudis = campEstudis;
        this.dificultat = dificultat;
    }

    @Override
    public ResultadoPrueba ejecutarPrueba() {
        int sum=0;
        for(int i=0;i<dificultat;i++){
            sum+=2*i-1;
        }
        return new ResultadoTesis(Math.sqrt(sum));
    }

    @Override
    public String getDifficulty(){
        return String.valueOf(dificultat);
    }
    @Override
    public String getCampEstudis(){
        return campEstudis;
    }

    @Override
    public String getTipus(){
        return "Tesis";
    }
}
