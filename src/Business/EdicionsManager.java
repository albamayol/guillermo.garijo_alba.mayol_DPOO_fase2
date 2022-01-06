package Business;

import Persistance.*;

import java.time.Year;
import java.util.ArrayList;

public class EdicionsManager {

    private ArrayList<Edicio> ediciones;
    private int currentYear;
    private DAOEdicio daoEdicio;

    /*
    public EdicionsManager(DAOProva daoProva, DAOJugador daoJugador) {
        this.daoEdicio =new DAOEdicioCSV(daoJugador, daoProva, "edicion.csv");
        this.ediciones = daoEdicio.leerEdiciones();
        this.currentYear= Year.now().getValue();
    }

     */

    public EdicionsManager( DAOEdicio daoEdicio) {
        this.daoEdicio = daoEdicio;
        ediciones=daoEdicio.llegeixEdicions();
        this.currentYear= Year.now().getValue();
    }

    public boolean comprovaJugadors(int num){
        return num<=5 && num>=1;
    }
    public boolean comprobaNumProves(int num){
        return num<=12 && num>=3;
    }
    public void afegirEdicio(Edicio e){
        ediciones.add(e);
    }
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

    public boolean existeEdicion(int año){
        for (Edicio edicione : ediciones) {
            if (edicione.esEdicion(año)) {
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
