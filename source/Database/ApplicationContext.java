package source.Database;

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
}
