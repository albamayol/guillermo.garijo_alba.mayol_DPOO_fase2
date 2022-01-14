package Business.Resultados;

import java.util.ArrayList;

/**
 * Clase para gestionar los resultados de las diferentes pruebas
 */
public abstract class ResultadoPrueba {
    protected String tipus;
    protected boolean pasa;

    /**
     * Construcor general de ResultadoPrueba
     * @param tipus Tipo de resultado (coincide con el tipo de prueba)
     */
    public ResultadoPrueba(String tipus) {
        this.tipus = tipus;
    }

    /**
     * Getter del tipo de resultado
     * @return tipus
     */
    public String getTipus() {
        return tipus;
    }

    /**
     * Setter de pasa (indica si el jugador ha pasado o no la prueba)
     * @param pasa Boolean indicando si pasa o no (true pasa, false no pasa)
     */
    public void setPasa(boolean pasa) {
        this.pasa=pasa;
    }

    /**
     * Getter de la expresion de la tesis
     * @return expresio
     */
    public double getExpresio() {
        return 0;
    }

    /**
     * Getter del logaritmo de la solicitud de presupuesto
     * @return log
     */
    public double getLog() {
        return 0;
    }

    /**
     * Getter del array de resultados de una publicacion
     * @return
     */
    public ArrayList<Integer> getResultats() {
        return null;
    }

    /**
     * Getter de creditos aprobados
     * @return aprobados
     */
    public String getAprobados() {
        return null;
    }

    /**
     * Getter de pasa (si esta aprobada o no la prueba)
     * @return pasa
     */
    public boolean getPasa() {
        return pasa;
    }

    /**
     * Getter del numero de creditos totales del master
     * @return Creditos totales (aprobados + suspendidos)
     */
    public String getTotales() {
        return null;
    }

    /**
     * Getter del quartil de la publicacion
     * @return numero de PI en funcion del quartil
     */
    public int getPIPublicacio(){
        return 0;
    }
}
