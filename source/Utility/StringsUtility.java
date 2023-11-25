package source.Utility;

/**
 * A class that contains some static strings that are useful for error messages.
 * Similar to strings.xml in Android Studio.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/6/2023
 */
public abstract class StringsUtility {
    /**
     * The string to indicate password mismatch
     */
    public final static String PASSWORD_MISMATCH = "The password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20";
    /**
     * The string to prompt users to enter a date
     */
    public final static String DATE_FORMAT = "dd/MM/yyyy";
    /**
     * The string to indicate a date mismatch
     */
    public final static String DATE_ERROR = "You did not key in the date in the right format!";
    /**
     * The string to indicate error in camp committee input
     */
    public final static String CAMP_COMMITTEE_OVERFLOW = "The number of committee members cannot be lesser than 0 or greater than the total slots!";

    public final static String ATTENDEE_SLOTS_ERROR = "The number of attendee slots cannot less than or equals to 0!";
}
