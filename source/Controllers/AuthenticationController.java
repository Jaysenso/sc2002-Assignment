package source.Controllers;

import source.Database.Dao.StaffDao;
import source.Database.Dao.StudentDao;
import source.Database.StaffDaoImpl;
import source.Database.StudentDaoImpl;
import source.Entity.Staff;
import source.Entity.Student;
import source.Entity.User;
import source.FileIO.Parser.Parser;
import source.FileIO.TextDataReader;
import source.Utility.DirectoryUtility;
import source.Utility.PrettyPage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The AuthenticationController provides functionalities to authenticate whether the user's input details are valid
 * and authenticated.
 *
 * @author Isaac Chun
 * @version 1.0
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

    public boolean haveTriesLeft() {
        return attemptsLeft > 0;
    }

    public int getTriesLeft() {
        return attemptsLeft;
    }

}
