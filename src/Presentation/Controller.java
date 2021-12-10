package Presentation;

import Business.CompositorManager;

public class Controller {

    public static void run() {

        switch (UIManager.menuPrincipal()) {
            case 'A':
                boolean exit = false;
                while (!exit) {
                    switch (UIManager.menuCompositor()) {
                        case 1:
                            while (!exit) {
                                switch (UIManager.menuProves()) {
                                    case 'a':
                                        if (UIManager.chooseTrial() == 1) {
                                            System.out.println("hola\n");
                                            /*String = */ UIManager.askForString("Enter the trial's name: ");
                                            /*String = */ UIManager.askForString("Enter the journal's name: ");
                                            /*String = */ UIManager.askForString("Enter the journal's quartile: ");
                                            /*int = */ UIManager.askForInt("Enter the acceptance probability: ");
                                            /*int = */ UIManager.askForInt("Enter the revision probability: ");
                                            /*int = */ UIManager.askForInt("Enter the rejection probability: ");

                                        }
                                        //temporal:
                                        //exit = true;
                                        break;
                                    case 'b':
                                        //temporal:
                                        exit = true;
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
                            UIManager.shutDownMsg();
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
