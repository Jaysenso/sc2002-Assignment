package source.Controllers;

import source.Database.Dao.StaffDao;
import source.Database.StaffDaoImpl;
import source.Entity.Staff;
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
    private StaffDao staffDao;

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
     * @param from  the header to search in
     * @return staff object if found, null if not.
     */
    public Staff readStaff(String query, String from) {
        return staffDao.readStaff(query, from);
    }

    /**
     * A function to update the database given a staff.
     */
    public void updateStaff(Staff staff) {
        staffDao.updateStaff(staff);
    }

}
