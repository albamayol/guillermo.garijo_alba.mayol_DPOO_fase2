package Business;

import Persistance.*;

import java.time.Year;
import java.util.ArrayList;

/**
 * Clase para la gestion de ediciones
 */
public class EdicionsManager{

    private ArrayList<Edicio> ediciones;
    private int currentYear;
    private DAOEdicio daoEdicio;

    /**
     * Constructor para los managers con CSV
     * @param dao Dao que se usara (ha de ser csv)
     */
    public EdicionsManager(DAOEdicioCSV dao) {
        this.daoEdicio =dao;
        this.ediciones = daoEdicio.llegeixEdicions();
        this.currentYear= Year.now().getValue();
    }

    /**
     * Constructor para los managers con JSON
     * @param dao Dao que se usara (ha de ser json)
     */
    public EdicionsManager(DAOEdicioJSON dao) {
        this.daoEdicio = dao;
        ediciones=daoEdicio.llegeixEdicions();
        this.currentYear= Year.now().getValue();
    }

    /**
     * Comprueba que el numero de jugadores introducidos por el usuario es correcto
     * @param num Numero introducido por el usuario
     * @return boolean Retorna true si num=[1-5], false si no
     */
    public boolean comprovaJugadors(int num){
        return num<=5 && num>=1;
    }
    /**
     * Comprueba que el numero de pruebas introducidas por el usuario es correcto
     * @param num Numero introducido por el usuario
     * @return boolean Retorna true si num=[3-12], false si no
     */
    public boolean comprobaNumProves(int num){
        return num<=12 && num>=3;
    }

    /**
     * Añade la edicion e a la lista de ediciones existentes
     * @param e Edicion a añadir
     */
    public void afegirEdicio(Edicio e){
        ediciones.add(e);
    }

    /**
     * Retorna la edicion en la posicion i de la lista de ediciones
     * @param i Posicion de la edicion que se quiere obtener
     * @return Retorna la edicion en esa posicion
     */
    public Edicio getEdicio(int i){
        return ediciones.get(i);
    }

    /**
     * Hace una copia la edicion e, con el año y el numero de jugadores actualizados
     * @param e Edicion de la que se quiere hacer una copia
     * @param any Nuevo año para la copia de la edicion
     * @param numJug Nuevo numero de jugadores para la copia
     * @return
     */
    public Edicio copiaEdicio(Edicio e, int any, int numJug){
        return e.copiaEdicio(any, numJug);
    }

    /**
     * Comprueba si hay alguna edicion creada
     * @return True si no hay ediciones, false si no las hay
     */
    public boolean noHayEdiciones(){
        return ediciones.isEmpty();
    }

    /**
     * Getter de los años de las ediciones
     * @return Array de ints de los años de las diferentes ediciones creadas
     */
    public ArrayList<Integer> anysEdicions(){
        ArrayList<Integer> r = new ArrayList<>();
        for (Edicio e:ediciones) {
            r.add(e.getAny());
        }
        return r;
    }

    /**
     * Metodo para guardar las ediciones en el fichero correspondiente
     */
    public void guardarEdiciones(){
        daoEdicio.guardaEdicions(ediciones);
    }

    /**
     * Comprueba si existe una edicion para un año en concreto
     * @param any Año que se quiere comprobar
     * @return True si existe, false si no existe
     */
    public boolean existeEdicion(int any){
        for (Edicio edicione : ediciones) {
            if (edicione.esEdicion(any)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna la edicion correspondiente al año pasado por parametro
     * @param any Año de la edicion
     * @return La edicion que corresponde a any
     */
    public Edicio retornEdicioSegonsAny(int any) {
        for (Edicio e : ediciones) {
            if (e.getAny() == any) {
                return e;
            }
        }
        return null;
    }

    /**
     * Elimina la edicion que corresponde al año pasado por parametro
     * @param any Año de la edicion que se quiere borrar
     */
    public void eliminaEdicion(int any){
        for(int i=0;i<ediciones.size();i++){
            if(ediciones.get(i).esEdicion(any)){
                ediciones.remove(i);
            }
        }
    }

    /**
     * Retorna la edicion en la posicion seleccionada
     * @param num Posicion en el array de ediciones
     * @return Edicion correspondiente a num
     */
    public Edicio getEditionByPlace(int num) {
        return ediciones.get(num);
    }

    /**
     * Retorna la edicion del año actual
     * @return Edicio
     */
    public Edicio getEdicioActual() {
        return retornEdicioSegonsAny(currentYear);
    }

}
