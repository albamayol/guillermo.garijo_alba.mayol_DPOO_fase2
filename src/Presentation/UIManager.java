package Presentation;

import Business.Edicio;
import Business.Jugador;
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
        System.out.println("\ta) Create Trial\n\tb) List Trials\n\tc) Delete Trial\n\n\td) Back\n");

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
        System.out.println("\t--- Trial types ---\n");
        System.out.println("\t1) Paper publication\n");
        int op;
        do {
            System.out.print("Enter the trial's type: ");
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
            System.out.println("\nTrial: " + proves.get(i).getNomProva() + " (" + proves.get(i).getTipus() + ")");
            System.out.println("Journal: " + proves.get(i).getNomRevista() + " (" + proves.get(i).getQuartil() + ")");
            System.out.println("Chances: " + proves.get(i).getProbabilitatAccepta() + "% acceptance, " + proves.get(i).getProbabilitatRevisions() + "% revision, " + proves.get(i).getProbabilitatRebutja() + "% rejection\n");
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

    public void executa(ArrayList<ArrayList<Integer>> arrayExecucio, ArrayList<Jugador> jugadors) {
        for (int i = 0; i < arrayExecucio.size(); i++) {
            System.out.print("\t" + (jugadors.get(i).getNom() + " is submitting... "));
            for (int j = 0; j < arrayExecucio.get(i).size(); j++) {
                switch (arrayExecucio.get(i).get(j)) {
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
            System.out.print("PI count: " + jugadors.get(i).getPI());
            if(jugadors.get(i).getPI()<=0){
                System.out.print(" - Disqualified!");
            }
            System.out.println("");
        }

    }
}
