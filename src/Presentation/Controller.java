package Presentation;

import Business.EdicionsManager;
import Business.ProvesManager;

import java.util.ArrayList;

public class Controller {
    private UIManager ui;
    private EdicionsManager em;
    private ProvesManager pmanager;

    public Controller(){
        this.ui = new UIManager();
        this.pmanager=new ProvesManager();
        this.em=new EdicionsManager();
    }

    public void run() {

        ArrayList<String> nomsProves = new ArrayList<>();
        ArrayList<Integer> anysEdicions = new ArrayList<>();

        switch (ui.menuPrincipal()) {
            case 'A':
                boolean exit = false;
                while (!exit) {
                    switch (ui.menuCompositor()) {
                        case 1:
                            while (!exit) {
                                switch (ui.menuProves()) {
                                    case 'a':
                                        if (ui.chooseTrial() == 1) {
                                            while(true) {
                                                boolean error = pmanager.creaProva(ui.askForString("Enter the trial's name: "), ui.askForString("Enter the journal's name: "), ui.askForString("Enter the journal's quartile: "), ui.askForInt("Enter the acceptance probability: "), ui.askForInt("Enter the revision probability: "), ui.askForInt("Enter the rejection probability: "), "Paper publication");
                                                if (error) {
                                                    ui.showMessage("ERROR. data entered not correct. Try again.\n");
                                                    continue;
                                                }
                                                break;
                                            }
                                            ui.showMessage("The trial was created successfully!\n");
                                            for (int i = 0; i < pmanager.llistaProves().size(); i++) {
                                                nomsProves.add(pmanager.llistaProves().get(i).getNomProva());
                                            }
                                        }
                                        break;
                                    case 'b':
                                        nomsProves= pmanager.nomsProves();
                                        if (nomsProves.isEmpty()) {
                                            ui.showMessage("ERROR. There are no Trials created.\n");
                                        } else {
                                            ui.llistaProva(pmanager.llistaProves(), ui.menuLlistaProves(nomsProves, "Here are the current trials, do you want to see more details or go back?") - 1);
                                        }
                                        break;
                                    case 'c':
                                        if (nomsProves.isEmpty()) {
                                            ui.showMessage("ERROR. There are no Trials created.\n");
                                        } else {
                                            int numProva = ui.menuLlistaProves(nomsProves, "Which trial do you want to delete?");
                                            switch (ui.trialNameConfirmation(pmanager.llistaProves(), numProva)) {
                                                case 1:
                                                    pmanager.eliminaProva(numProva-1);
                                                    nomsProves.remove(numProva-1);
                                                    ui.showMessage("The trial was successfully deleted.\n");
                                                    break;
                                                case 2:
                                                    break;
                                                default:
                                                    ui.showMessage("Trial's introduced name and option entered are not correct. Try again.");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 'd':
                                        exit = true;
                                        break;
                                }
                            }
                            exit = false;
                            break;
                        case 2:
                            while (!exit) {
                                switch (ui.menuGestioEdicio()) {
                                    case 'a':
                                        while (true) {
                                            if (em.creaEdicioBuida(ui.askForInt("Enter the edition's year: "), ui.askForInt("Enter the initial number of players: "), ui.askForInt("Enter the number of trials: "))) {
                                                if (!nomsProves.isEmpty()) {
                                                    ui.showMessage("\t--- Trials ---");
                                                    ui.mostraProves(nomsProves);
                                                    for (int i = 1; i <= em.retornaLastEdicion().getNumProves(); i++) {
                                                        int num = ui.askForInt("Pick a trial (" + i + "/" + em.retornaLastEdicion().getNumProves() + "): ");
                                                        if (num > pmanager.llistaProves().size() || num <= 0) {
                                                            ui.showMessage("ERROR. That trial does not exist.");
                                                            i--;
                                                        } else {
                                                            em.añadePruebaAEdicion(pmanager.llistaProves().get(num - 1), em.retornaLastEdicion().getAny());
                                                        }
                                                    }
                                                    ui.showMessage("The edition was created successfully!");
                                                    for (int j = 0; j < em.llistaEdicions().size(); j++) {
                                                        anysEdicions.add(em.llistaEdicions().get(j).getAny());
                                                    }
                                                } else {
                                                    ui.showMessage("ERROR. There are no Trials to add.");
                                                }
                                            } else {
                                                ui.showMessage("ERROR. Data entered not correct. Try again.");
                                                continue;
                                            }
                                            break;
                                        }
                                        break;
                                    case 'b':
                                        if(anysEdicions.isEmpty()) {
                                            ui.showMessage("ERROR. There are not Editions yet created.");
                                        } else {
                                            while (true) {
                                                int num = ui.menuLlistaEdicions(anysEdicions, "Here are the current editions, do you want to see more details or go back?");
                                                if (num == anysEdicions.size() + 1) {   //cas Back
                                                    break;
                                                } else {
                                                    ui.mostraDetallsEdicio(em.llistaEdicions().get(num - 1));
                                                }
                                            }
                                        }
                                        break;
                                    case 'c':
                                        if (anysEdicions.isEmpty()){
                                            ui.showMessage("ERROR. There are not Editions yet created.");
                                        } else {
                                            while (true) {
                                                int num = ui.menuLlistaEdicions(anysEdicions, "Which edition do you want to clone?");
                                                if (num == anysEdicions.size() + 1) {   //cas Back
                                                    break;
                                                } else {
                                                    int any = em.llistaEdicions().get(num - 1).getAny();
                                                    int anyNou;
                                                    int numJugNou;
                                                    while (true) {
                                                        anyNou = ui.askForInt("Enter the new edition's year: ");
                                                        if (anyNou <= any){
                                                            ui.showMessage("ERROR. New year is not correct.");
                                                        } else {
                                                            while (true) {
                                                                numJugNou = ui.askForInt("Enter the new edition's initial number of players: ");
                                                                if (numJugNou < 1 || numJugNou > 5) {
                                                                    ui.showMessage("ERROR. Number of players incorrect.");
                                                                } else {
                                                                    ui.showMessage("The edition was cloned successfully!");
                                                                    em.duplicaEdicion(any, anyNou, numJugNou);
                                                                    anysEdicions.add(anyNou);
                                                                    break;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case 'd':
                                        if (anysEdicions.isEmpty()) {
                                            ui.showMessage("ERROR. There are not Editions yet created.");
                                        } else {
                                            while (true) {
                                                int num = ui.menuLlistaEdicions(anysEdicions, "Which edition do you want to delete?");
                                                if (num == anysEdicions.size() + 1) {   //cas Back
                                                    break;
                                                } else {
                                                    int numConfirmation = ui.askForInt("Enter the edition's year for confirmation: ");
                                                    if (em.eliminaEdicion(numConfirmation)) {
                                                        ui.showMessage("The edition was successfully deleted.");
                                                        for (int i = 0; i < anysEdicions.size(); i++) {
                                                            if (numConfirmation == anysEdicions.get(i)) {
                                                                anysEdicions.remove(i);
                                                            }
                                                        }
                                                        break;
                                                    } else {
                                                        ui.showMessage("ERROR. The edition year to delete is not correct.");
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case 'e':
                                        exit = true;
                                        break;
                                }
                            }
                            exit = false;
                            break;
                        case 3:
                            ui.shutDownMsg();
                            pmanager.guardarPruebas();
                            em.edicionesToCSV();
                            exit = true;
                            break;
                    }
                }
                break;
            case 'B':
                break;
        }


    }

}
