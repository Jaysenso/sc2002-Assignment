package source.Controllers.UserManagement;

import source.Entity.User;

/**
 * The UserManager is the main management of users, providing functions to update users without knowing
 * their real derived types.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class UserManager {
    /**
     * The current logged-in user
     */
    private User user;
    /**
     * The management strategy to use for different derived types of users
     */
    private IManagement management;

    /**
     * An overloaded constructor that binds the logged-in user to this manager
     *
     * @param user the user
     */
    public UserManager(User user) {
        this.user = user;
    }

    /**
     * Sets the user reference to the logged-in user
     *
     * @param user user;
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the management strategy of this user manager, where management is an interface
     *
     * @param management the management interface.
     */
    public void setManagement(IManagement management) {
        this.management = management;
    }

    /**
     * Update the user in the associated databases
     */
    public void update() {
        if (management != null)
            management.update();
    }

    public void getProfile() {
        if (management != null)
            management.getProfile();
    }


}
