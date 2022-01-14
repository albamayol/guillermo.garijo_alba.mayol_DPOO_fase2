package Persistance;

import Business.Proves.Prova;

import java.util.ArrayList;

/**
 * Interfaz para implementar la persistencia de las pruebas
 */
public interface DAOProva {
    /**
     * Metodo para guardar pruebas en los ficheros correspondientes
     * @param proves Array de pruebas a guardar
     */
    void guardaProves(ArrayList<Prova> proves);

    /**
     * Metodo para leer las pruebas de los ficheros
     * @return Array con las pruebas leidas
     */
    ArrayList<Prova> llegeixProves();
}
