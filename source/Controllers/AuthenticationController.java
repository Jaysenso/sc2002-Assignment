package source.Controllers;

import source.Entity.User;
import source.Utility.PrettyPage;

/**
 * The AuthenticationController provides functionalities to authenticate whether the user's input details are valid
 * and authenticated.
 *
 * @author Isaac Chun
 * @version 1.1
 * @since 11/12/2023
 */
public class AuthenticationController {

    /**
     * The max amount of attempts a user can log in for.
     */
    private static final int MAX_ATTEMPTS = 5;
    /**
     * The number of attempts left for the user. Default value is MAX_ATTEMPTS
     */
    private static int attemptsLeft = MAX_ATTEMPTS;

    /**
     * An authentication function that checks if the stored password was the same as a given password.
     * Works through the user abstract class.
     *
     * @param user     the user
     * @param password the password
     * @see User
     */
    public boolean authenticate(User user, String password) {
        //TODO : Hashing
        //Else, we can query the password of this student
        if (user.getPassword().equals(password)) {
            //Reset attempts
            attemptsLeft = MAX_ATTEMPTS;
            return true;
        }
        PrettyPage.printError("Incorrect password given!");
        //Else if it was invalid password
        attemptsLeft--;
        return false;
    }

    /**
     * Returns if the user still can log in
     *
     * @return true if the user still has tries remaining, false if not.
     */
    public boolean haveTriesLeft() {
        return attemptsLeft > 0;
    }

    /**
     * Returns the number of tries the user has left
     *
     * @return attempts left for the user
     */
    public int getTriesLeft() {
        return attemptsLeft;
    }

}
