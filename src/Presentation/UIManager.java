package Presentation;

import Business.Edicio;
import Business.Prova;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIManager {

    public UIManager() {
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
            System.out.println("Enter a role: ");
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
            System.out.println("Enter an option: ");
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
        System.out.println("\ta) Create Trial\n\tb) List Trials\n\tc) Delete Trial\n\n\td) Back\n");

        char op = '\0';
        while (true) {
            System.out.println("Enter an option: ");
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
        System.out.println("\t--- Trial types ---\n");
        System.out.println("\t1) Paper publication\n");
        int op;
        do {
            System.out.println("Enter the trial's type: ");
            try {
                op = scanner.nextInt();
                //clean buffer: \n
                scanner.nextLine();
                if(op != 1) {   //al només haver-hi una única opció de prova, aquesta serà la condició(temporal)
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
            System.out.println(message);
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
            System.out.println(message);
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
        UIManager uiManager = new UIManager();

        System.out.println(message);
        uiManager.mostraProves(proves);

        System.out.println("\t");
        System.out.println(proves.size() + 1 + ") Back\n");
        int num;
        while (true) {
            num = uiManager.askForInt("Enter an option: ");
            if (num > proves.size() + 1 || num <= 0) {
                uiManager.showMessage("ERROR. " + num + " is not an option. Try again.\n");
            } else {
                break;
            }
        }
        return num;
    }

    public void mostraProves(ArrayList<String> proves) {
        for (int i = 0; i < proves.size(); i++) {
            System.out.println("\t");
            System.out.println(i + 1 + ") " + proves.get(i) + "\n");
        }
    }

    public void llistaProva(ArrayList<Prova> proves, int i) {
        if (i < proves.size()) {
            System.out.println("Trial: " + proves.get(i).getNomProva() + " (" + proves.get(i).getTipus() + ")");
            System.out.println("Journal: " + proves.get(i).getNomRevista() + " (" + proves.get(i).getQuartil() + ")");
            System.out.println("Chances: " + proves.get(i).getProbabilitatAccepta() + "% acceptance, " + proves.get(i).getProbabilitatRevisions() + "% revision, " + proves.get(i).getProbabilitatRebutja() + "% rejection");
        }
    }

    public int trialNameConfirmation(ArrayList<Prova> proves, int i) {
        UIManager uiManager = new UIManager();
        if (i <= proves.size()) {
            String name = uiManager.askForString("Enter the trial's name for confirmation: ");
            if (proves.get(i-1).getNomProva().equals(name)) {
                return 1;
            }
            else {
                return 0;
            }
        }
        if (i == proves.size() + 1) {
            return 2;
        }
        return 0;
    }

    public char menuGestioEdicio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\ta) Create Edition\n\tb) List Editions\n\tc) Duplicate Edition\n\td) Delete Edition\n\n\te) Back\n");

        char op = '\0';
        while (true) {
            System.out.println("Enter an option: ");
            try {
                op = stringOfOneToChar(scanner.nextLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (op != 'a' && op != 'b' && op != 'c' && op != 'd' && op != 'e') {
                System.out.println("ERROR. " + op + " is not an option. Try again\n");
                continue;
            }
            break;
        }
        return op;
    }

    public void mostraEdicions(ArrayList<Integer> edicions) {
        for (int i = 0; i < edicions.size(); i++) {
            System.out.println("\t");
            System.out.println(i + 1 + ") The Trials " + edicions.get(i) + "\n");
        }
    }

    public int menuLlistaEdicions(ArrayList<Integer> edicions, String message) {
        UIManager uiManager = new UIManager();

        System.out.println(message);
        uiManager.mostraEdicions(edicions);

        System.out.println("\t");
        System.out.println(edicions.size() + 1 + ") Back\n");
        int num;
        while (true) {
            num = uiManager.askForInt("Enter an option: ");
            if (num > edicions.size() + 1 || num <= 0) {
                uiManager.showMessage("ERROR. " + num + " is not an option. Try again.\n");
            } else {
                break;
            }
        }
        return num;
    }

    public void mostraDetallsEdicio(Edicio edicio) {
        System.out.println("Year: " + edicio.getAny());
        System.out.println("Players: " + edicio.getNumInicialJugadors());
        System.out.println("Trials: ");
        for (int i = 1; i <= edicio.getNumProves(); i++)  {
            System.out.println(i + "- " + edicio.getProves().get(i-1).getNomProva() + " (" + edicio.getProves().get(i-1).getTipus() + ")");
        }
    }
}
