package Presentation;

import Business.Edicio;
import Business.Jugador.Jugador;
import Business.Proves.Prova;
import Business.Resultados.ResultadoPrueba;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase para gestionar los prints por pantalla
 */
public class UIManager {

    private Scanner sc;

    /**
     * Constructor general de la clase UI
     */
    public UIManager() {
        sc=new Scanner(System.in);
    }

    /**
     * Metodo para mostrar el menu de eleccion de persistencia
     * @return String con la opcion seleccionada por el usuario
     */
    public String  menuPersistencia(){
        showMessage("The IEEE needs to know where your allegiance lies.\n");
        showMessage("\n\tI) People's Front of Engineering (CSV)");
        showMessage("\n\tII) Engineering People's Front (JSON)\n");
        String opcion="";
        while (true){
            opcion = askForString("\nPick a faction: ");
            if(!(opcion.equals("I") || opcion.equals("II"))){
                showMessage(" Incorrect option!");
            }else {
                return opcion;
            }
        }
    }

    /**
     * Metodo para mostrar el menu de seleccion de rol
     * @return String con la opcion elegida
     */
    public String menuPrincipal() {
        System.out.println("""
                ____ _            ____       _       _
               /__  \\ |__   ___  /__  \\_ __ (_) __ _| |___
                 / /\\/ '_ \\/ _ \\   / /\\/ '__| |/ _` | / __|
                / /  | | | |  __/ / /  | |  | | (_| | \\__ \\
                \\/   |_| |_|\\___| \\/   |_|  |_|\\__,_|_|___/
                """);
        System.out.println("\nWelcome to The Trials. Who are you?\n");
        System.out.println("\tA) The Composer\n\tB) This year's Conductor\n");
        String opt;
        while (true) {
            showMessage("\nEnter a role: ");
            opt=sc.nextLine();
            if (!(opt.equals("A") || opt.equals("B"))) {
                System.out.println("ERROR. " + opt + " is not an option. Try again\n");
            }else {
                return opt;
            }
        }
    }

    /**
     * Metodo para mostrar el menu del compositor
     * @return Opcion elegida por el usuario
     */
    public int menuCompositor() {
        System.out.println("Entering management mode...\n");
        System.out.println("\t1) Manage Trials\n\t2) Manage Editions\n\n\t3) Exit\n");
        Scanner scanner = new Scanner(System.in);
        String op;
        while (true) {
            System.out.print("Enter an option: ");
            op = scanner.nextLine();
            if (!(op.equals("1") || op.equals("2") || op.equals("3"))) {
                System.out.println("ERROR. Not an option. Try again\n");
            }else{
                return Integer.parseInt(op);
            }
        }
    }

    /**
     * Metodo para mostrar el menu de pruebas
     * @return String con la opcion elegida por el usuario
     */
    public String menuProves() {
        System.out.println("\n\ta) Create Trial\n\tb) List Trials\n\tc) Delete Trial\n\n\td) Back\n");
        while (true) {
            showMessage("\nEnter an option: ");
            String op=sc.nextLine();
            if (!(op.equals("a") || op.equals("b") || op.equals("c") || op.equals("d"))) {
                System.out.println("ERROR. " + op + " is not an option. Try again\n");
            }else {
                return op;
            }
        }
    }

    /**
     * Metodo para mostrar el menu de seleccion de tipo de prueba
     * @return int representando el tipo de prueba seleccionada (1:paparePublication, 2:masterStudies, 3:tesisDoctoral, 4:budgetRequest)
     */
    public int chooseTrial() {
        System.out.println("\n\t--- Trial types ---\n");
        System.out.println("\t1) Paper publication");
        System.out.println("\t2) Master studies");
        System.out.println("\t3) Doctoral thersis defense");
        System.out.println("\t4) Budget request\n");
        String num;
        while(true) {
            System.out.print("Enter the trial's type: ");
            num = sc.nextLine();
            if(!(num.equals("1") || num.equals("2") || num.equals("3") || num.equals("4"))) {
                System.out.println("ERROR. Not an option. Try again\n");
            }else {
                return Integer.parseInt(num);
            }
        }
    }

    /**
     * Metodo para mostrar el mensaje de cierre del programa
     */
    public void shutDownMsg() {
        System.out.println("Shutting down...");
    }

    /**
     * Metodo para mostrar un mensaje por pantalla
     * @param message Mensaje a mostrar
     */
    public void showMessage(String message) {
        System.out.print(message);
    }

    /**
     * Metodo para pedir un String al usuario
     * @param message
     * @return
     */
    public String askForString(String message) {
        showMessage(message);
        return sc.nextLine();
    }

    /**
     * Metodo para pedir un int al usuario
     * @param message Mensaje con el que se quiere pedir
     * @return el nummero introducido por el usuario
     */
    public int askForInt(String message) {
        String num;
        int r;
        System.out.print(message);
        num = sc.nextLine();
        try{
            r=Integer.parseInt(num);
            return r;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * Metodo para mostrar el menu de listar las pruebas junto con un mensaje
     * @param proves Array de pruebas a mostrar en la lista
     * @param message Mensaje con el que se quiere acompañar la pregunta
     * @return numero correspondiente a la posicion en la lista intrroducido por el usuario
     */
    public int menuLlistaProves(ArrayList<String> proves, String message) {
        showMessage(message);
        mostraProves(proves);
        System.out.println( "\t" + (proves.size() + 1) + ") Back\n");
        int num=-1;
        while (true) {
            while (num==-1) {
                num = askForInt("Enter an option: ");
                if(num==-1){
                    showMessage("\nPlease, enter a number ...");
                }
            }
            if (num > proves.size() + 1 || num <= 0) {
                showMessage("ERROR. " + num + " is not an option. Try again.\n");
            } else {
                break;
            }
        }
        return num;
    }

    /**
     * Metodo para mostrar las pruebas en forma de lista
     * @param proves Pruebas a mostrar
     */
    public void mostraProves(ArrayList<String> proves) {
        for (int i = 0; i < proves.size(); i++) {
            System.out.print("\t"+ (i + 1) + ") " + proves.get(i) + "\n");
        }
    }

    /**
     * Metodo para mostrar por pantalla los detalles de una prueba en concreto
     * @param proves Array de pruebas donde se seleccionara
     * @param i Prueba seleccionada
     */
    public void llistaProva(ArrayList<Prova> proves, int i) {
        if (i < proves.size()) {
            Prova p = proves.get(i);

            switch (p.getTipus()) {
                case "Publication" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Paper publication)");
                    System.out.println("Journal: " + p.getNomRevista() + " (" + p.getQuartil() + ")");
                    System.out.println("Chances: " + p.getProbabilitatAccepta() + "% acceptance, " + p.getProbabilitatRevisions() + "% revision, " + p.getProbabilitatRebutja() + "% rejection\n");
                }
                case "Tesis" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Doctorial thesis defense)");
                    System.out.println("Field: " + p.getCampEstudis());
                    System.out.println("Difficulty: " + p.getDifficulty());
                }
                case "EstudiMaster" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Master studies)");
                    System.out.println("Master: " + p.getNomMaster());
                    System.out.println("ECTS: " + p.getCredits() + " with a " + p.getProbabilitatMaster() + " chance to pass each one");
                }
                case "Pressupost" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Budget request)");
                    System.out.println("Entity: " + p.getEntity());
                    System.out.println("Budget: " + p.getBudget() + "€");
                }
            }

        }
    }

    /**
     * Metodo para gestionar el input de confirmacion para borrar pruebas
     * @param names Nombres de las pruebas creadas
     * @param i Prueba a borrar
     * @return
     */
    public int trialNameConfirmation(ArrayList<String> names, int i) {
        if (i <= names.size()) {
            String name = askForString("\nEnter the trial's name for confirmation: ");
            if (names.get(i-1).equals(name)) {
                return 1;
            } else {
                return 0;
            }
        }
        if (i == names.size() + 1) {
            return 2;
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public String menuGestioEdicio() {
        System.out.println("\n\ta) Create Edition\n\tb) List Editions\n\tc) Duplicate Edition\n\td) Delete Edition\n\n\te) Back\n");
        String op;
        while (true) {
            System.out.print("Enter an option: ");
            op=sc.nextLine();
            if (!(op.equals("a") || op.equals("b") || op.equals("c") || op.equals("d") || op.equals("e"))) {
                System.out.println("ERROR. " + op + " is not an option. Try again\n");
            }else {
                return op;
            }
        }
    }

    public void mostraEdicions(ArrayList<Integer> edicions) {
        for (int i = 0; i < edicions.size(); i++) {
            System.out.println("\t"+ (i + 1) + ") The Trials " + edicions.get(i));
        }
    }

    public int menuLlistaEdicions(ArrayList<Integer> edicions, String message) {

        System.out.println(message);
        mostraEdicions(edicions);
        System.out.println("");
        System.out.println("\t"+ (edicions.size() + 1) + ") Back\n");
        int num;
        while (true) {
            num = askForInt("Enter an option: ");
            if (num > edicions.size() + 1 || num <= 0) {
                showMessage("ERROR. " + num + " is not an option. Try again.\n");
            } else {
                break;
            }
        }
        return num;
    }

    public void mostraDetallsEdicio(Edicio edicio) {
        System.out.println("\nYear: " + edicio.getAny());
        System.out.println("Players: " + edicio.getNumJugadors());
        System.out.println("Trials: ");
        for (int i = 1; i <= edicio.getNumProves(); i++)  {
            System.out.println("\t" + i + "- " + edicio.getNomProva(i-1) + " (" + edicio.getTipusProva(i-1) + ")");
        }
    }

    public void executa(ArrayList<ResultadoPrueba> arrayExecucio, ArrayList<Jugador> jugadors) {

        if(arrayExecucio.size()==1){
            //presupost
            if(arrayExecucio.get(0).getPasa()){
                showMessage("\n\nThe research group got the budget!\n");

            }else{
                showMessage("\n\nThe research group failed ...\n");
            }
            for (Jugador j:jugadors) {
                showMessage("\n" + j.getNom() + " " + j.getTipusJugador() + " PI count: " + j.getPI());
                if(j.getPI()<=0){
                    System.out.print(" - Disqualified!");
                }
            }
            showMessage("\n");
        }else{
            //los otros
            for (int i = 0; i < jugadors.size(); i++) {
                String tipo = arrayExecucio.get(i).getTipus();
                Jugador j = jugadors.get(i);
                ResultadoPrueba rp = arrayExecucio.get(i);
                switch (tipo){
                    case "Publicacio":
                        ArrayList<Integer> resultats = rp.getResultats();
                        System.out.print("\n\t" + (j.getNom() + " is submitting... "));
                        for (int k = 0; k < resultats.size(); k++) {
                            switch (resultats.get(k)) {
                                case 1:
                                    System.out.print("Accepted! ");
                                    break;
                                case 2:
                                    System.out.print("Rejected. ");
                                    break;
                                case 3:
                                    System.out.print("Revisions... ");
                                    break;
                            }
                        }
                        break;

                    case "Tesis":
                        if(rp.getPasa()){
                            showMessage("\n\t"+ (j.getNom() + " was successfull. Congrats!"));

                        }else {
                            showMessage("\n\t"+ (j.getNom() + " failed. Sorry ..."));
                        }
                        break;
                    case "EstudiMaster":
                        System.out.print("\n\t" + (j.getNom() + " passed " + rp.getAprobados() + "/" + rp.getTotales() + " ECTS."));
                        if(rp.getPasa()){
                            showMessage("Congrats!");
                        }else{
                            showMessage("Sorry ...");
                        }
                        break;
                }
                System.out.print(" PI count: " + j.getPI());
                if(jugadors.get(i).getPI()<=0){
                    System.out.print(" - Disqualified!");
                }
            }

        }
  }
}
