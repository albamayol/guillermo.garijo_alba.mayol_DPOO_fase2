package Presentation;

import Business.*;
import Persistance.*;

/**
 * Controlador general del programa
 */
public class Controller {
    private UIManager ui;
    private ControllerCompositor cCompositor;
    private ControllerConductor cConductor;

    /**
     * Constructor del controlador general del programa
     */
    public Controller() {
        this.ui = new UIManager();
    }

    /**
     * Metodo que ejecuta el controlador
     */
    public void run() {
        EdicionsManager em = null;
        ProvesManager pm=null;
        switch (ui.menuPersistencia()) {
            case "I" -> {
                //csv
                em = new EdicionsManager(new DAOEdicioCSV("edicion.csv", "estudisMaster.csv","tesis.csv","publicacio.csv","pressupost.csv", "jugador.csv"));
                pm = new ProvesManager(new DAOProvaCSV("estudisMaster.csv", "tesis.csv", "publicacio.csv", "pressupost.csv"));
            }
            case "II" -> {
                //json
                em = new EdicionsManager(new DAOEdicioJSON("edicion.json"));
                pm = new ProvesManager(new DAOProvaJSON("prova.json"));
            }
        }
        this.cCompositor=new ControllerCompositor(em, ui, pm);
        this.cConductor=new ControllerConductor(em, ui);

        switch (ui.menuPrincipal()) {
            case 'A' -> cCompositor.run();
            case 'B' -> cConductor.run();
        }
    }
}
