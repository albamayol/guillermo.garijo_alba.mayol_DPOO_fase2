package Presentation;

import Business.ProvesManager;

import java.util.ArrayList;

public class Controller {
    private UIManager ui;
    private ProvesManager pmanager;

    public Controller(){
        this.ui = new UIManager();
        this.pmanager = new ProvesManager();
    }

    public void run() {

        switch (ui.menuPrincipal()) {
            case 'A':
                boolean exit = false;
                while (!exit) {
                    switch (ui.menuCompositor()) {
                        case 1:
                            ArrayList<String> nomsProves = new ArrayList<>();
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
                            //temporal:
                            exit = true;
                            break;
                        case 3:
                            ui.shutDownMsg();
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
