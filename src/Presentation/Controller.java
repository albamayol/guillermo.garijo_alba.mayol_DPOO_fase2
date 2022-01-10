package Presentation;

import Business.*;
import Persistance.*;

public class Controller {
    private UIManager ui;
    private ControllerCompositor cCompositor;
    private ControllerConductor cConductor;

    public Controller() {
        this.ui = new UIManager();
    }

    public void run() {
        EdicionsManager em = null;
        ProvesManager pm=null;
        switch (ui.menuPersistencia()) {
            case "I" -> {
                //csv
                em = new EdicionsManager(new DAOEdicioCSV("edicion.csv"));
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
