package Presentation;

import Business.Edicio;
import Business.EdicionsManager;
import Business.Master;
import Business.ProvesManager;

/**
 * Clase que gestiona todas las funcionalidades del compositor
 */
public class ControllerCompositor {
    private boolean exit;
    private final UIManager ui;
    private final ProvesManager pmanager;
    private final EdicionsManager emanager;

    /**
     * Constructor para el controlador del compositor
     * @param em Managaer de ediciones que utilizara
     * @param ui UI que utilizara
     * @param pm Manager de pruebas que utilizara
     */
    public ControllerCompositor(EdicionsManager em, UIManager ui, ProvesManager pm){
        this.pmanager=pm;
        this.ui=ui;
        this.emanager=em;
        this.exit=false;
    }

    private void creaPaperPublication(){
        //boolean error = true;
        //while(error){
            //error=false;
            String trialName = ui.askForString("\nEnter the trial's name: ");
            if(pmanager.existeixProva(trialName)){
                ui.showMessage("ERROR. Nom de proba ja existeix. Try again.\n");
                return;
            }
            String journalName = ui.askForString("Enter the journal's name: ");
            if(journalName.isEmpty()){
                ui.showMessage("ERROR. Journal name is empty. Try again.\n");
                return;
            }
            String quartil = ui.askForString("Enter the journal's quartile: ");
            if(!pmanager.comprobaQuartil(quartil)){
                ui.showMessage("ERROR. Quartil is not correct. Try again.\n");
                return;
            }
            int probAcceptance = ui.askForInt("Enter the acceptance probability: ");
            if(!pmanager.comprobaProb(probAcceptance)){
                ui.showMessage("ERROR. Acceptance probability is not valid. Try again.\n");
                return;
            }
            int probRevision = ui.askForInt("Enter the revision probability: ");
            if(!pmanager.comprobaProb(probRevision) || probRevision+probAcceptance>100){
                ui.showMessage("ERROR. Revision probability is not valid or Revision probability + acceptance probability are higher than 100. Try again.\n");
                return;
            }
            int probRejection = ui.askForInt("Enter the rejection probability: ");
            if(!pmanager.comprobaProb(probRejection) || probRejection+probAcceptance+probRevision!=100){
                ui.showMessage("ERROR. Rejection probability is not valid or Revision probability + acceptance probability + rejection probability do not sum 100. Try again.\n");
                return;
            }
            pmanager.creaPaperPubli(trialName, journalName, quartil, probAcceptance, probRevision, probRejection);
            ui.showMessage("\nThe trial was created successfully!\n");
            //return false;
        //}
    }

    private void creaMasterStudies(){
        String trialName = ui.askForString("\nEnter the trial's name: ");
        if(pmanager.existeixProva(trialName)){
            ui.showMessage("ERROR. Nom de proba ja existeix. Try again.\n");
            return;
        }
        String masterName=ui.askForString("Enter the master's name: ");
        if(masterName.isEmpty()){
            ui.showMessage("ERROR. Master's name can not be empty. Try again.\n");
            return;
        }
        int numCredits = ui.askForInt("Enter the master's ECTS number: ");
        if(numCredits<60 || numCredits>120){
            ui.showMessage("ERROR. Number of credits incorrect. [60-120]");
            return;
        }
        int probCredit=ui.askForInt("Enter the credit pass probability: ");
        if(probCredit<0 || probCredit>100){
            ui.showMessage("ERROR. Probability incorrect. [0-100]");
            return;
        }
        pmanager.creaMaster(trialName, masterName, numCredits, probCredit);
        ui.showMessage("\nThe trial was created successfully!\n");

    }

    private void creaTesis(){
        String trialName = ui.askForString("\nEnter the trial's name: ");
        if(pmanager.existeixProva(trialName)){
            ui.showMessage("ERROR. Nom de proba ja existeix. Try again.\n");
            return;
        }
        String field=ui.askForString("Enter the Thesis field of study: ");
        int diff = ui.askForInt("Enter the defense difficulty: ");
        if(diff<0 || diff>10){
            ui.showMessage("ERROR. Difficulty incorrect. [1-10]");
            return;
        }
        pmanager.creaTesis(trialName, field, diff);
        ui.showMessage("\nThe trial was created successfully!\n");
    }

    private void creaBudgetReq(){
        String trialName = ui.askForString("\nEnter the trial's name: ");
        if(pmanager.existeixProva(trialName)){
            ui.showMessage("ERROR. Nom de proba ja existeix. Try again.\n");
            return;
        }
        String entity=ui.askForString("Enter the entity name: ");
        int budget = ui.askForInt("Enter the budget amount: ");
        if(budget<1000 || budget>2000000000){
            ui.showMessage("ERROR. Probability incorrect. [10000-2000000000]");
            return;
        }
        pmanager.creaPressupost(trialName, entity, budget);
        ui.showMessage("\nThe trial was created successfully!\n");
    }

    private void eliminaProva(){
        int numProva = ui.menuLlistaProves(pmanager.nomsProves(), "\nWhich trial do you want to delete?\n");
        switch (ui.trialNameConfirmation(pmanager.nomsProves(), numProva)) {
            case 0:
                ui.showMessage("The trial name introduced does not correspond with the one selected. Deletion not confirmed. Going back.\n");
                return;
            case 1:
                if(!pmanager.isInUse(numProva-1)){
                    pmanager.eliminaProva(numProva-1);
                    ui.showMessage("\nThe trial was successfully deleted.\n");
                    return;
                }else{
                    ui.showMessage("\nThis trial is in use. Can not delete. Going back.\n");
                }

            case 2:
                //volver atras
        }
    }

    private void creaEdicio(){
        int any = ui.askForInt("Enter the edition's year: ");
        if(emanager.existeEdicion(any)){
            ui.showMessage("This edition already exists.");
            return;
        }
        int numJugadors = ui.askForInt("Enter the initial number of players: ");
        if(!emanager.comprovaJugadors(numJugadors)){
            ui.showMessage("The number of players is not correct. [1,5]");
            return;
        }
        int numProves = ui.askForInt("Enter the number of trials: ");
        if(!emanager.comprobaNumProves(numProves)){
            ui.showMessage("The number of trials is not correct. [3,12]");
            return;
        }
        Edicio e = new Edicio(any, numJugadors, numProves);
        ui.showMessage("\n\t--- Trials ---\n");
        ui.mostraProves(pmanager.nomsProves());
        ui.showMessage("");
        for (int i = 1; i <= numProves; i++) {
            int num = ui.askForInt("Pick a trial (" + i + "/" + numProves + "): ");
            if (num > pmanager.llistaProves().size() || num <= 0) {
                ui.showMessage("ERROR. That trial does not exist. Going back.");
                return;
            } else {
                e.addProba(pmanager.getProva(num-1)); //cordarse de poner el uso a true ->done (no va)
                pmanager.setToUse(num-1);
            }
        }
        emanager.afegirEdicio(e);
        ui.showMessage("\nThe edition was created successfully");
    }

    private void duplicaEdicio(){
        int num = ui.menuLlistaEdicions(emanager.anysEdicions(), "Which edition do you want to clone?\n");
        if (num == emanager.anysEdicions().size() + 1) {   //cas Back
            return;
        }
        int anyNou = ui.askForInt("\nEnter the new edition's year: ");
        if(emanager.existeEdicion(anyNou)){
            ui.showMessage("This years' edition already exists.");
            return;
        }
        int numJugNou = ui.askForInt("Enter the new edition's initial number of players: ");
        if(!emanager.comprovaJugadors(numJugNou)){
            ui.showMessage("The number of players is not correct. [1,5]");
            return;
        }
        emanager.afegirEdicio(emanager.copiaEdicio(emanager.getEdicio(num-1), anyNou, numJugNou));
        ui.showMessage("\nThe edition was cloned successfully.");
    }

    private void eliminaEdicion(){
            int num = ui.menuLlistaEdicions(emanager.anysEdicions(), "Which edition do you want to delete?\n");
            if (num == emanager.anysEdicions().size() + 1) {   //cas Back
                return;
            }
            int numConfirmation = ui.askForInt("\nEnter the edition's year for confirmation: ");
            if(emanager.getEdicio(num-1).esEdicion(numConfirmation)){
                emanager.eliminaEdicion(numConfirmation);
                ui.showMessage("\nThe edition was successfully deleted.");
            }else{
                ui.showMessage("Incorrect confirmation. Gooing back.");
            }
    }

    /**
     * Metodo para ejecutar el controller de compositor
     */
    public void run(){
        while (!exit) {
            switch (ui.menuCompositor()) {
                case 1 -> {
                    while (!exit) {
                        switch (ui.menuProves()) {
                            case 'a':
                                switch (ui.chooseTrial()){
                                    case 1:
                                        creaPaperPublication();
                                        break;
                                    case 2:
                                        creaMasterStudies();
                                        break;
                                    case 3:
                                        creaTesis();
                                        break;
                                    case 4:
                                        creaBudgetReq();
                                        break;
                                }
                                break;

                            case 'b':
                                if (pmanager.noHayPruebas()) {
                                    ui.showMessage("ERROR. There are no Trials created.\n");
                                    break;
                                }
                                ui.llistaProva(pmanager.llistaProves(), ui.menuLlistaProves(pmanager.nomsProves(), "\nHere are the current trials, do you want to see more details or go back?\n") - 1);
                                break;
                            case 'c':
                                if (pmanager.noHayPruebas()) {
                                    ui.showMessage("ERROR. There are no Trials created.\n");
                                    break;
                                }
                                eliminaProva();
                                break;
                            case 'd':
                                pmanager.guardarPruebas();
                                exit = true;
                                break;
                        }
                    }
                    exit = false;
                }
                case 2 -> {
                    while (!exit) {
                        switch (ui.menuGestioEdicio()) {
                            case 'a':
                                if (pmanager.noHayPruebas()) {
                                    ui.showMessage("There are no trials. You can not create any edition. Going back.");
                                    exit = true;
                                    return;
                                }
                                creaEdicio();
                                break;
                            case 'b':
                                if (emanager.noHayEdiciones()) {
                                    ui.showMessage("ERROR. There are not Editions yet created.");
                                    break;
                                }
                                int num = ui.menuLlistaEdicions(emanager.anysEdicions(), "Here are the current editions, do you want to see more details or go back?\n");
                                if (num == emanager.anysEdicions().size() + 1) {   //cas Back
                                    break;
                                }
                                ui.mostraDetallsEdicio(emanager.getEditionByPlace(num-1));
                                break;
                            case 'c':
                                if (emanager.noHayEdiciones()) {
                                    ui.showMessage("ERROR. There are not Editions yet created.");
                                    break;
                                }
                                duplicaEdicio();
                                break;
                            case 'd':
                                if (emanager.noHayEdiciones()) {
                                    ui.showMessage("ERROR. There are not Editions yet created. You can not delete anything. Going back.");
                                }
                                eliminaEdicion();
                                break;
                            case 'e':
                                //modificaciones al uso de las pruebas, se han de guardar
                                pmanager.guardarPruebas();
                                emanager.guardarEdiciones();
                                exit = true;
                                break;
                        }
                    }
                    exit = false;
                }
                case 3 -> {
                    ui.shutDownMsg();
                    exit = true;
                    break;
                }
            }
        }


    }
}
