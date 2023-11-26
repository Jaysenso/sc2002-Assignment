package source.Controllers.UserManagement;

import source.Controllers.StaffManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Entity.User;
import source.Utility.Option;
import source.Utility.PrettyPage;

import java.util.ArrayList;

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

    /**
     * Print the profile details
     */
    @Override
    public void getProfile() {
        String createdCamps = "";
        ArrayList<Camp> rc = staff.getCreatedCamps();
        for (int i = 0; i < rc.size(); i++) {
            createdCamps += rc.get(i).getCampInfo().getName();
            if (i != rc.size() - 1)
                createdCamps += ", ";
        }
        Option[] options = new Option[]{
                new Option("Name", staff.getName()),
                new Option("Faculty", staff.getFacultyInfo().getClass().getSimpleName()),
                new Option("UserID", staff.getUserID()),
                new Option("Email", staff.getUserID() + "@e.ntu.edu.sg"),
                new Option("Created Camps", createdCamps)
        };
        PrettyPage.printLines(options);
    }
}
