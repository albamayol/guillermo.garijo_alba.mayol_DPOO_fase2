package Business;

import Persistance.DAOEdicio;
import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;

public class EdicionsManager {

    private ArrayList<Edicio> ediciones;
    private int currentYear;
    private JugadorManager jm;
    private ProvesManager pm;
    private DAOEdicio daoEdicio;

    public EdicionsManager() {
        this.jm= new JugadorManager();
        this.pm=new ProvesManager();
        this.daoEdicio=new DAOEdicio(jm.getDaoJugador(), pm.getDaoProva(), "edicion.csv");
        this.ediciones = daoEdicio.leerEdiciones();
        this.currentYear= Year.now().getValue();
    }

    //1 aceptado, 2 rechazado, 3 revisndo, 4 descalificado
    public ArrayList<ArrayList<Integer>> ejecutarPrueba(){
        for (Edicio e:ediciones) {
            if(e.esEdicion(currentYear)){
                return e.ejecutarPrueba();
            }
        }
        return null;
    }

    //retorna true cuando el equipo se queda sin jugadores
    public boolean equipoEliminado(){
        for (Edicio e:ediciones) {
            if(e.esEdicion(currentYear) && e.todosLosJugadoresEliminados()){
                return true;
            }
        }
        return false;
    }

    //metodo para guardar las ediciones antes de cerrar el programa
    public void edicionesToCSV(){
        ArrayList<String> aGuardar = new ArrayList<>();
        for (Edicio e:ediciones) {
            aGuardar.add(e.edicionToCSV());
        }
        daoEdicio.guardarEdiciones(aGuardar);
        jm.guardarJugadores();
    }


    public boolean creaEdicioBuida(int año, int numJug, int numProb){
        if(!existeEdicion(año) && numJug<=15 && numJug>=1 && numProb>=3 && numProb<=12){
            ediciones.add(new Edicio(año, numJug, numProb, new ArrayList<>(), new ArrayList<>()));
            return true;
        }
        return false;
    }

    public void añadePruebaAEdicion(Prova p, int año){
        for (Edicio e:ediciones) {
            if(e.esEdicion(año)){
                e.addProba(p);
            }
        }
    }


    public boolean existeEdicion(int año){
        Iterator<Edicio> it = ediciones.iterator();
        while (it.hasNext()){
            if(it.next().esEdicion(año)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Edicio> llistaEdicions(){
        return ediciones;
    }

    public void añadirJugador(String name){
        for (Edicio e:ediciones) {
            if(e.esEdicion(currentYear)){
                e.addJugador(jm.getJugador(name));
            }
        }
    }

    public void eliminaEdicion(int año){
        for(int i=0;i<ediciones.size();i++){
            if(ediciones.get(i).esEdicion(año)){
                ediciones.remove(i);
            }
        }
    }

    public void duplicaEdicion(int año, int añoNuevo, int numJug){
        for(int i=0;i<ediciones.size();i++){
            if(ediciones.get(i).esEdicion(año)){
                ediciones.add(ediciones.get(i).clone(añoNuevo, numJug));
            }
        }
    }
}
