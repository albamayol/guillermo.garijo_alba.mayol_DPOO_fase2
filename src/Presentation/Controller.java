package Presentation;

import Business.*;
import Persistance.DAOJugador;
import Persistance.DAOProva;

import java.util.ArrayList;

public class Controller {
    private UIManager ui;
    private ControllerCompositor cCompositor;
    private ControllerConductor cConductor;

    public Controller() {
        this.ui = new UIManager();
        DAOJugador daoJugador = new DAOJugador("jugador.csv");
        DAOProva daoProva = new DAOProva("prueba.csv");
        EdicionsManager em= new EdicionsManager(daoProva, daoJugador);
        ProvesManager pm = new ProvesManager(daoProva);
        this.cConductor= new ControllerConductor(em, ui);
        this.cCompositor=new ControllerCompositor(em, ui, pm);
    }

    public void run() {
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
