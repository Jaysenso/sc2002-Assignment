package source.Controllers;

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

    public boolean authenticateAsStudent(String email, String password) {
        return authenticate(email, password, DirectoryUtility.STUDENT_DATA_PATH);
    }

    public boolean authenticateAsStaff(String email, String password) {
        return authenticate(email, password, DirectoryUtility.STUDENT_DATA_PATH);
    }


    private boolean authenticate(String email, String password, String path) {
        //If no more tries left.
        if (!haveTriesLeft())
            return false;

        //Instantiate our text data reader
        TextDataReader reader = new TextDataReader();
        //Return a string of data given from the reader
        ArrayList<String> rawData = reader.read(path);

        //We then create a parser to parse our data
        Parser parser = new Parser();
        //Set the data in our map given the parse results
        HashMap<String, ArrayList<String>> data = parser.parse(rawData);

        //if the csv has been corrupted, let us return out and throw some error
        if (!data.containsKey("email") || !data.containsKey("password")) {
            //Throw some exception maybe
            return false;
        }
        int idx = 0;
        ArrayList<String> emails = data.get("email");
        //Loop through the emails list and finds the email
        int pos = emails.indexOf(email);
        if (pos == -1) {
            //Else if it was invalid email
            attemptsLeft--;
            PrettyPage.printError("Either your email or your password was incorrect.");
            return false;
        }

        //Now we have the iterator, so we can acquire the password at that line
        //Else, we safely proceed on
        String pw = data.get("password").get(idx);
        //Then we must check the password to see if it matches
        if (pw.equals(password)) {
            //Reset our attempts left
            attemptsLeft = MAX_ATTEMPTS;
            return true;
        }
        PrettyPage.printError("Either your email or your password was incorrect.");
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
