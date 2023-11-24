package source.Controllers.UserManagement;

import source.Controllers.StaffManager;
import source.Database.App;
import source.Entity.Staff;
import source.Entity.User;

/**
 * The StaffManagement class provides functionalities to update a staff object from the
 * user's perspective.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class StaffManagement implements IManagement {
    /**
     * The staff object associated with this management
     */
    private final Staff staff;
    /**
     * The staff manager reference
     */
    private final StaffManager staffManager;

    /**
     * An overloaded constructor that takes in the current Application user and binds it
     * to staff.
     * NOTE: It is guaranteed to work!
     *
     * @param user the current logged-in user
     */
    public StaffManagement(User user) {
        if (user instanceof Staff) {
            this.staff = (Staff) user;
        } else {
            this.staff = null;
        }
        //Initialize the student manager to be the app's static reference
        staffManager = App.getStaffManager();
    }
    /**
     * Update the user in the associated databases
     */
    @Override
    public void update() {
        staffManager.updateStaff(staff);

    }
}
