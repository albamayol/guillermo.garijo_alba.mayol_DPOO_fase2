package Persistance;

import Business.Edicio;

import java.util.ArrayList;

/**
 * Interfaz para implementar la persistencia de las ediciones del programa
 */
public interface DAOEdicio {
    /**
     * Metodo para guardar las ediciones en los ficheros correspondientes
     * @param ediciones Array de ediciones a guardar
     */
    void guardaEdicions(ArrayList<Edicio> ediciones);

    /**
     * Metodo para leer todas las ediciones guardadas
     * @return Array con todas las ediciones del fichero csv
     */
    ArrayList<Edicio> llegeixEdicions();
}
