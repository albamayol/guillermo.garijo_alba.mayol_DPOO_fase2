package Presentation;

import Business.Edicio;
import Business.Jugador;
import Business.Proves.Prova;
import Business.Resultados.ResultadoPrueba;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIManager {

    public UIManager() {
    }

    public String  menuPersistencia(){
        showMessage("The IEEE needs to know where your allegiance lies.");
        showMessage("\tI) People's Front of Engineering (CSV)");
        showMessage("\tII) Engineering People's Front (JSON)");
        boolean error=true;
        String opcion="";
        while (error){
            opcion = askForString("Pick a faction: ");
            if(!(opcion.equals("I") || opcion.equals("II"))){
                showMessage("Incorrect option!");
            }else {
                error=false;
            }
        }
        return opcion;
    }

    public char menuPrincipal() {
        System.out.println("""
                ____ _            ____       _       _
               /__  \\ |__   ___  /__  \\_ __ (_) __ _| |___
                 / /\\/ '_ \\/ _ \\   / /\\/ '__| |/ _` | / __|
                / /  | | | |  __/ / /  | |  | | (_| | \\__ \\
                \\/   |_| |_|\\___| \\/   |_|  |_|\\__,_|_|___/
                """);
        System.out.println("\nWelcome to The Trials. Who are you?\n");
        System.out.println("\tA) The Composer\n\tB) This year's Conductor\n");
        Scanner scanner = new Scanner(System.in);
        //inicialitzem el char
        char opt = '\0';
        while (true) {
            System.out.print("Enter a role: ");
            try {
                opt = stringOfOneToChar(scanner.nextLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (opt != 'A' && opt != 'B') {
                System.out.println("ERROR. " + opt + " is not an option. Try again\n");
                continue; //continue te lleva al primer bucle que ecuentra, en el bucle donde esté metido
            }
            break;
        }
        return opt;
    }

    public char stringOfOneToChar(String string) {
        return string.charAt(0);
    }

    public int menuCompositor() {
        System.out.println("Entering management mode...\n");
        System.out.println("\t1) Manage Trials\n\t2) Manage Editions\n\n\t3) Exit\n");
        Scanner scanner = new Scanner(System.in);
        int op;
        do {
            System.out.print("Enter an option: ");

            try {
                op = scanner.nextInt();
                //clean buffer: \n
                scanner.nextLine();
                if(op < 1 || op > 3) {
                    System.out.println("ERROR. not an option. Try again\n");
                    continue;
                }
            } catch (InputMismatchException e) {
                //clean buffer
                scanner.nextLine();
                System.out.println("ERROR. Input Missmatch Exception\n");
                continue;
            }
            break;
        } while(true);
        return op;
    }

    public char menuProves() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\ta) Create Trial\n\tb) List Trials\n\tc) Delete Trial\n\n\td) Back\n");

        char op = '\0';
        while (true) {
            System.out.print("Enter an option: ");
            try {
                op = stringOfOneToChar(scanner.nextLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (op != 'a' && op != 'b' && op != 'c' && op != 'd') {
                System.out.println("ERROR. " + op + " is not an option. Try again\n");
                continue; //continue te lleva al primer bucle que ecuentra, en el bucle donde esté metido
            }
            break;
        }
        return op;
    }

    public int chooseTrial() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t--- Trial types ---\n");
        System.out.println("\t1) Paper publication");
        System.out.println("\t2) Master studies");
        System.out.println("\t3) Doctoral thersis defense");
        System.out.println("\t4) Budget request\n");
        int op;
        do {
            System.out.print("Enter the trial's type: ");
            try {
                op = scanner.nextInt();
                //clean buffer: \n
                scanner.nextLine();
                if(op != 1 && op != 2 && op != 3 && op != 4) {   //al només haver-hi una única opció de prova, aquesta serà la condició(temporal)
                    System.out.println("ERROR. not an option. Try again\n");
                    continue;
                }
            } catch (InputMismatchException e) {
                //clean buffer
                scanner.nextLine();
                System.out.println("ERROR. Input Missmatch Exception\n");
                continue;
            }
            break;
        } while(true);
        return op;

    }

    public void shutDownMsg() {
        System.out.println("Shutting down...");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String askForString(String message) {
        Scanner sc = new Scanner(System.in);
        String s;
        while (true) {
            System.out.print(message);
            try {
                s = sc.nextLine();
            } catch (NullPointerException e) {
                System.out.println("ERROR. Empty argument.\n");
                continue;
            }
            break;
        }
        return s;
    }

    public int askForInt(String message) {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            System.out.print(message);
            try {
                num = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("ERROR. Input Missmatch Exception\n");
                continue;
            }
            break;
        }
        return num;
    }

    public int menuLlistaProves(ArrayList<String> proves, String message) {
        System.out.println(message);
        mostraProves(proves);
        System.out.println("");
        System.out.println( "\t" + (proves.size() + 1) + ") Back\n");
        int num;
        while (true) {
            num = askForInt("Enter an option: ");
            if (num > proves.size() + 1 || num <= 0) {
                showMessage("ERROR. " + num + " is not an option. Try again.\n");
            } else {
                break;
            }
        }
        return num;
    }

    public void mostraProves(ArrayList<String> proves) {
        for (int i = 0; i < proves.size(); i++) {
            System.out.print("\t"+ (i + 1) + ") " + proves.get(i) + "\n");
        }
    }

    public void llistaProva(ArrayList<Prova> proves, int i) {
        if (i < proves.size()) {
            Prova p = proves.get(i);

            switch (p.getTipus()) {
                case "Publicacio" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Paper publication)");
                    System.out.println("Journal: " + p.getNomRevista() + " (" + p.getQuartil() + ")");
                    System.out.println("Chances: " + p.getProbabilitatAccepta() + "% acceptance, " + p.getProbabilitatRevisions() + "% revision, " + p.getProbabilitatRebutja() + "% rejection\n");
                }
                case "Tesis" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Doctorial thesis defense)");
                    System.out.println("Field: " + p.getCampEstudis());
                    System.out.println("Difficulty: " + p.getDifficulty());
                }
                case "Master" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Master studies)");
                    System.out.println("Master: " + p.getNomMaster());
                    System.out.println("ECTS: " + p.getCredits() + " with a " + p.getProbabilitatMaster() + " chance to pass each one");
                }
                case "Presupost" -> {
                    System.out.println("\nTrial: " + p.getNomProva() + " (Budget request)");
                    System.out.println("Entity: " + p.getEntity());
                    System.out.println("Budget: " + p.getBudget() + "€");
                }
            }

        }
    }

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

    public char menuGestioEdicio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\ta) Create Edition\n\tb) List Editions\n\tc) Duplicate Edition\n\td) Delete Edition\n\n\te) Back\n");

        char op = '\0';
        while (true) {
            System.out.print("Enter an option: ");
            try {
                op = stringOfOneToChar(scanner.nextLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (op != 'a' && op != 'b' && op != 'c' && op != 'd' && op != 'e') {
                System.out.println("ERROR. " + op + " is not an option. Try again\n");
                continue;
            }
            System.out.println("");
            break;
        }
        return op;
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
            showMessage("The research group got the budget!");
            for (Jugador j:jugadors) {
                showMessage(j.getNom() + " " + j.getTipus());
                showMessage("PI count: " + j.getPI());
            }

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
                            showMessage("\t"+ (j.getNom() + " was successfull. Congrats!"));

                        }else {
                            showMessage("\t"+ (j.getNom() + "   failed. Sorry ..."));
                        }
                        break;
                    case "Master":
                        System.out.print("\t" + (j.getNom() + " passed " + rp.getAprobados() + "/" + rp.getTotales() + " ECTS."));
                        if(rp.getPasa()){
                            showMessage("Congrats!");
                        }else{
                            showMessage("Sorry ...");
                        }
                        break;
                }
                System.out.println(" PI count: " + j.getPI());
                if(jugadors.get(i).getPI()<=0){
                    System.out.print(" - Disqualified!");
                }
            }

        }
    }
}
