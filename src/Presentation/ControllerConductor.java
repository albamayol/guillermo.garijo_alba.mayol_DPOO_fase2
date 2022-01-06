package Presentation;

import Business.Edicio;
import Business.EdicionsManager;
import Business.Jugador;

import java.util.ArrayList;
import java.util.Iterator;

public class ControllerConductor {
    private UIManager ui;
    private EdicionsManager em;
    private boolean exit;

    public ControllerConductor(EdicionsManager em, UIManager ui){
        this.em=em;
        this.exit=false;
        this.ui=ui;
    }

    private boolean continuaExecucio(){
        String t = ui.askForString("Continue the execution? [yes/no]: ");
        if(t.equals("yes")){
            return false;
        }else if(t.equals("no")){
            return true;
        }else{
            ui.showMessage("That is not a valid option. Tryy again.");
            return continuaExecucio();
        }
    }

    public void run(){

        Edicio edActual = em.getEdicioActual();
        if(edActual ==null){
            ui.showMessage("ERROR. There is no Edition to execute this year. Going back.");
            return;
        }
        ui.showMessage("Entering execution mode...");
        ui.showMessage("THE TRIALS .......");
        if (!edActual.hayJugadores()) {
            for (int i = 1; i <= edActual.getNumJugadors(); i++) {
                edActual.addJugador(ui.askForString("Enter the player's name (" + i + "/" + edActual.getNumJugadors() + "): "));
            }
        }

        while (!exit) {
            if (edActual.acabada()) {
                String ganador;
                if (!edActual.hayJugadores()) {
                    ganador = "PLAYERS LOST";
                } else {
                    ganador = "PLAYERS WON";
                }
                ui.showMessage("THE TRIALS " + edActual.getAny() + " HAVE ENDED - " + ganador);
                em.guardarEdiciones();
                return;
            }

            int provaActual = edActual.getUltimaProva();
            ui.showMessage("Trial #" + (provaActual + 1) + " - " + edActual.getNomProvaActual());
            ui.executa(edActual.ejecutarPrueba(), edActual.getJugadors());
            edActual.eliminados();
            //meter un bucle para informar si alguien a subido de "lvl"
            Iterator<Jugador> subidos = edActual.subirLvl();
            while (subidos.hasNext()){
                Jugador tmp = subidos.next();
                ui.showMessage(tmp.getNom() + " is now a " + tmp.getTipus() + "(with 5 PI).");
            }
            exit = continuaExecucio();

        }

        em.guardarEdiciones();
    }
}
