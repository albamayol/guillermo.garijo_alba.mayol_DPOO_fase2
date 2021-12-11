package Presentation;

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
                System.out.println("ERROR. Input Missmatch Exception\n");
                continue;
            }
            break;
        }
        return num;
    }

    public int menuLlistaProves(ArrayList<String> proves) {
        System.out.println("Here are the current trials, do you want to see more details or go back?\n");
        for (int i = 0; i < proves.size(); i++) {
            System.out.println("\t");
            System.out.println(i + 1 + ") " + proves.get(i) + "\n");
        }
        System.out.println("\t");
        System.out.println(proves.size() + 1 + ") Back\n");
        UIManager uiManager = new UIManager();
        int num;
        while (true) {
            num = uiManager.askForInt("Enter an option: ");
            if (num > proves.size() || num < 1) {
                uiManager.showMessage("ERROR. " + num + " is not an option. Try again.\n");
            } else {
                break;
            }
        }

        return num;
    }


}
