package source.Utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        try {
           int i = scanner.nextInt();
           scanner.close();
           return i;
        } catch (InputMismatchException e) {
            PrettyPage.printError("You did not input an integer!");
        }
        return -1;
    }

    public static int tryGetInt(int min, int max, String choiceText, String errorMessage) {
        //Should be abstracted so that we do not create a scanner everywhere in the code
        try {
            scanner = new Scanner(System.in);
            while (true) {
                System.out.print(choiceText);

                int choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    PrettyPage.printError(errorMessage);
                    continue;
                }
                //if it's a legitimate choice, then we close our scanner and return the choice
                scanner.close();
                return choice;
            }
        } catch (InputMismatchException e) {
            //Implement our own exception later
            PrettyPage.printError("You did not input an integer!");
            //Try and try again, although this may be bad?
            return tryGetInt(min,max,choiceText,errorMessage);
        }
    }
}
