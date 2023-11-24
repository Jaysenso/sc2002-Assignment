package source.Utility;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The InputHandler class holds all the input functionalities such that no unnecessary scanners are created and used in the codebase.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see <a href="https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html#:~:text=A%20Regular%20Expression%20(or%20Regex,characters%2C%20metacharacters%20(such%20as%20.">regex reference</a>
 * @since 11/12/2023
 */

public class InputHandler {
    /**
     * A static scanner that lasts throughout the lifetime of the application.
     *
     * @return int user input
     */
    private static Scanner scanner = new Scanner(System.in);

    private final static String EMAIL_REGEX = "\\w+@[e.]*ntu.edu.sg";
    private final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    /**
     * Encapsulates the logic of asking for an integer with the constraint that the input MUST be an integer.
     *
     * @return int user input
     */
    public static int getInt() {
        try {
            int i = scanner.nextInt();
            return i;
        } catch (InputMismatchException e) {
            PrettyPage.printError("You did not input an integer!");
        }
        return -1;
    }

    /**
     * Encapsulates the logic of asking for input
     *
     * @return string user input
     */
    public static String getString() {
        //Lazily clear the buffer
        scanner = new Scanner(System.in);
        try {
            String s = scanner.nextLine();
            return s;
        } catch (Exception e) {
            PrettyPage.printError("Error in getting string!");
        }
        return "";
    }

    /**
     * Asks for the user's input and try gets an int until a positive case is recorded.
     *
     * @param min          minimum number for the input
     * @param max          maximum number for the input
     * @param choiceText   the input query
     * @param errorMessage the error message to show on failure.
     */
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
                return choice;
            }
        } catch (InputMismatchException e) {
            //Implement our own exception later
            PrettyPage.printError("You did not input an integer!");
            //Try and try again, although this may be bad?
            return tryGetInt(min, max, choiceText, errorMessage);
        }
    }

    /**
     * Asks for the user's input and try gets an email until a positive case is recorded.
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html">Regex matcher</a>
     */
    public static String tryGetEmail(String choiceText, String errorMessage) {
        try {
            scanner = new Scanner(System.in);
            //Create pattern
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            while (true) {
                System.out.print(choiceText);
                String email = scanner.next();
                //Trim any unnecessary white spaces
                email = email.trim();
                Matcher matcher = pattern.matcher(email);
                //if the matcher could not find the pattern required
                if (!matcher.find()) {
                    PrettyPage.printError(errorMessage);
                    continue;
                }
                return email;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return tryGetEmail(choiceText, errorMessage);
        }
        return "";
    }

    /**
     * Asks for the user's input and try gets a password until a positive case is recorded.
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html">Regex matcher</a>
     */
    public static String tryGetPassword(String choiceText, String errorMessage) {
        try {
            scanner = new Scanner(System.in);
            //Create pattern
            Pattern pattern = Pattern.compile(PASSWORD_REGEX);
            while (true) {
                System.out.print(choiceText);
                String password = scanner.next();
                //Trim any unnecessary white spaces
                password = password.trim();
                Matcher matcher = pattern.matcher(password);
                //if the matcher could not find the pattern required
                if (password.isEmpty()) {
                    PrettyPage.printError("Password cannot be empty!");
                } else if (!matcher.find()) {
                    PrettyPage.printError(errorMessage);
                    continue;
                }
                return password;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return tryGetEmail(choiceText, errorMessage);
        }
        return "";
    }

}
