package Business;

import Persistance.*;

import java.time.Year;
import java.util.ArrayList;

public class EdicionsManager{

    private ArrayList<Edicio> ediciones;
    private int currentYear;
    private DAOEdicio daoEdicio;

    /**
     * Constructor para los managers con CSV
     * @param dao Dao que se usara
     */
    public EdicionsManager(DAOEdicioCSV dao) {
        this.daoEdicio =dao;
        this.ediciones = daoEdicio.llegeixEdicions();
        this.currentYear= Year.now().getValue();
    }

    /**
     * Constructor para los managers con JSON
     * @param dao Dao que se usara
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
     * @return Retorna la edicion
     */
    public Edicio getEdicio(int i){
        return ediciones.get(i);
    }
    public Edicio copiaEdicio(Edicio e, int any, int numJug){
        return e.copiaEdicio(any, numJug);
    }
    public boolean noHayEdiciones(){
        return ediciones.isEmpty();
    }

    public ArrayList<Integer> anysEdicions(){
        ArrayList<Integer> r = new ArrayList<>();
        for (Edicio e:ediciones) {
            r.add(e.getAny());
        }
        return r;
    }

    public void guardarEdiciones(){
        daoEdicio.guardaEdicions(ediciones);
    }

    public boolean existeEdicion(int any){
        for (Edicio edicione : ediciones) {
            if (edicione.esEdicion(any)) {
                return true;
            }
        }
        return false;
    }

    public Edicio retornEdicioSegonsAny(int any) {
        for (Edicio e : ediciones) {
            if (e.getAny() == any) {
                return e;
            }
        }
        return null;
    }

    public void eliminaEdicion(int any){
        for(int i=0;i<ediciones.size();i++){
            if(ediciones.get(i).esEdicion(any)){
                ediciones.remove(i);
            }
        }
    }

    public Edicio getEditionByPlace(int num) {
        return ediciones.get(num);
    }
    public Edicio getEdicioActual() {
        return retornEdicioSegonsAny(currentYear);
    }

}
