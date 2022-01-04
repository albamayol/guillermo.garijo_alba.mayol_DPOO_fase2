package Presentation;

import Business.*;
import Persistance.*;

public class Controller {
    private UIManager ui;
    private ControllerCompositor cCompositor;
    private ControllerConductor cConductor;

    public Controller() {
        this.ui = new UIManager();
        /*
        DAOJugadorCSV daoJugadorCSV = new DAOJugadorCSV("jugador.csv");
        DAOProvaCSV daoProvaCSV = new DAOProvaCSV("prueba.csv");
        EdicionsManager em= new EdicionsManager(daoProvaCSV, daoJugadorCSV);
        ProvesManager pm = new ProvesManager(daoProvaCSV);
        this.cConductor= new ControllerConductor(em, ui);
        this.cCompositor=new ControllerCompositor(em, ui, pm);

         */
    }

    public void run() {
        switch (ui.menuPersistencia()){
            case "I":
                //csv
                break;
            case "II":
                //json
                DAOEdicioJSON daoEdicioJSON = new DAOEdicioJSON("edicion.json");
                DAOProvaJSON daoProvaJSON=new DAOProvaJSON("prova.json");
                EdicionsManager em = new EdicionsManager(daoEdicioJSON);
                ProvesManager pm = new ProvesManager(daoProvaJSON);
                this.cCompositor=new ControllerCompositor(em, ui, pm);
                this.cConductor=new ControllerConductor(em, ui);
                break;
        }


        switch (ui.menuPrincipal()) {
            case 'A':
                cCompositor.run();
                break;
            case 'B':
                cConductor.run();
                break;
        }
    }
}
