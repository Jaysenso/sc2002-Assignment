package source.Database;

import source.Controllers.CampManager;
import source.Entity.Student;
import source.Entity.User;

/**
 * The ApplicationContext class is a static class that denotes static variables that last throughout application
 * lifetime such as the logged-in user (as there is only one instance of it) and other useful static
 * variables.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see User
 * @since 11/4/2023
 */
public abstract class ApplicationContext {

    public static User user;
    private static CampManager campManager;

    public static void initialize() {
        campManager = new CampManager();
    }

    public static CampManager getCampManager() {
        return campManager;
    }
}
