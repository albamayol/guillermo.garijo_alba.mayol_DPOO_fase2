package Presentation;

import Business.EdicionsManager;
import Business.JugadorManager;
import Business.Prova;
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

        switch (ui.menuPrincipal()) {
            case 'A':
                boolean exit = false;
                while (!exit) {
                    switch (ui.menuCompositor()) {
                        case 1:
                            //ProvesManager pmanager = new ProvesManager();
                            while (!exit) {
                                switch (ui.menuProves()) {
                                    case 'a':
                                        if (ui.chooseTrial() == 1) {
                                            System.out.println("hola\n");
                                            while(true) {
                                                boolean error = pmanager.creaProva(ui.askForString("Enter the trial's name: "), ui.askForString("Enter the journal's name: "), ui.askForString("Enter the journal's quartile: "), ui.askForInt("Enter the acceptance probability: "), ui.askForInt("Enter the revision probability: "), ui.askForInt("Enter the rejection probability: "));
                                                if (error) {
                                                    ui.showMessage("ERROR. data entered not correct. Try again.\n");
                                                    continue;
                                                }
                                                break;
                                            }
                                            ui.showMessage("The trial was created successfully!\n");
                                        }
                                        break;
                                    case 'b':
                                        ArrayList<String> nomsProves = new ArrayList<>();
                                        for (int i = 0; i < pmanager.llistaProves().size(); i++) {
                                            nomsProves.add(pmanager.llistaProves().get(i).getNomProva());
                                        }
                                        if (nomsProves.isEmpty()) {
                                            ui.showMessage("ERROR. There are no Trials created.\n");
                                        } else {
                                            ui.menuLlistaProves(nomsProves);
                                        }
                                        //temporal:
                                        //exit = true;
                                        break;
                                    case 'c':
                                        //temporal:
                                        exit = true;
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
