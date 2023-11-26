package source.Controllers;

import source.Database.Dao.StaffDao;
import source.Database.DatabaseQuery;
import source.Database.StaffDaoImpl;
import source.Entity.Staff;
import source.Entity.Student;
import source.Utility.DirectoryUtility;

/**
 * The StaffManager serves as a DB and abstracts the Data Access Object implementations and provides
 * what's necessary for others to be used.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class StaffManager {
    /**
     * The staff data access object that contains direct implementations to our database..
     */
    private final StaffDao staffDao;

    /**
     * A default constructor.
     */
    public StaffManager() {
        staffDao = new StaffDaoImpl(DirectoryUtility.STAFF_DATA_PATH);
    }

    /**
     * A function to read a staff given a query and a header
     *
     * @param query the query
     * @return staff object if found, null if not.
     */
    public Staff readStaff(DatabaseQuery query) {
        return staffDao.readStaff(query);
    }

    /**
     * A function to update the database given a staff.
     *
     * @param staff the staff object to update
     */
    public void updateStaff(Staff staff) {
        staffDao.updateStaff(staff);
    }

    /**
     * A function to get a staff from the database given their userID
     *
     * @param userID the user id of the staff
     * @return staff object
     */
    public Staff getStaff(String userID) {
        for (Staff s : staffDao.getStaffs()) {
            if (s.getUserID().equals(userID))
                return s;
        }
        return null;
    }
}
