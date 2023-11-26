package source.Database;

import source.Controllers.*;
import source.Controllers.UserManagement.UserManager;
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
public abstract class App {
    /**
     * The current logged-in user
     */
    private static User user;
    /**
     * The camp manager reference
     */
    private static CampManager campManager;
    /**
     * The user manager reference
     */
    private static UserManager userManager;
    /**
     * The student manager reference
     */
    private static StudentManager studentManager;
    /**
     * The staff manager reference
     */
    private static StaffManager staffManager;
    /**
     * The enquiry manager reference
     */
    private static EnquiryManager enquiryManager;
    /**
     * The staff manager reference
     */
    private static SuggestionManager suggestionManager;

    /**
     * A function to initialize ALL static managers. Should be called in main as the first line.
     */
    public static void initialize() {
        userManager = new UserManager(user);
        studentManager = new StudentManager();
        staffManager = new StaffManager();
        enquiryManager = new EnquiryManager();
        suggestionManager = new SuggestionManager();
        campManager = new CampManager();
    }

    /**
     * Updates the logged-in user given a user object
     *
     * @param user the new user
     */
    public static void setUser(User user) {
        App.user = user;
        userManager.setUser(user);
    }

    /**
     * Acquires the camp manager
     *
     * @return the camp manager reference
     */
    public static CampManager getCampManager() {
        return campManager;
    }

    /**
     * Acquires the user manager
     *
     * @return the user manager reference
     */
    public static UserManager getUserManager() {
        return userManager;
    }


    /**
     * Acquires the student manager
     *
     * @return the student manager reference
     */
    public static StudentManager getStudentManager() {
        return studentManager;
    }
    /**
     * Acquires the enquiry manager
     *
     * @return the enquiry manager reference
     */
    public static EnquiryManager getEnquiryManager() {
        return enquiryManager;
    }
    /**
     * Acquires the suggestion manager
     *
     * @return the suggestion manager reference
     */
    public static SuggestionManager getSuggestionManager() {
        return suggestionManager;
    }
    /**
     * Acquires the staff manager
     *
     * @return the staff manager reference
     */
    public static StaffManager getStaffManager() {
        return staffManager;
    }

    /**
     * Acquires the current user
     *
     * @return the user reference
     */
    public static User getUser() {
        return App.user;
    }


}
