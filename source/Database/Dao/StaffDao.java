package source.Database.Dao;

import source.Entity.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * The StaffDAO interface provides operations that follow the CRUD operations for persistent data storage.
 * Also provides a method to get an entire list of staff
 * and delete them.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface StaffDao {
    /**
     * Creates a staff in the subsequent database (perhaps for sign up feature~)
     *
     * @param staff the staff to add
     */
    boolean createStaff(Staff staff);

    /**
     * Reads a staff from the subsequent database using a given query and from which table
     * Should be mainly used for name queries.
     *
     * @param query query to check in our header
     * @param from  the header to query in
     */
    Staff readStaff(String query, String from);

    /**
     * Reads all staffs that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @param from  the header to query in
     * @return an arraylist of staff if found, an empty list if not.
     */
    ArrayList<Staff> readStaffs(String query, String from);

    /**
     * Updates a staff in the subsequent database. Directly updates the file straight away.
     *
     * @param staff the staff to update
     */
    boolean updateStaff(Staff staff);

    /**
     * Deletes a staff in the subsequent database
     *
     * @param query query to check in our header
     * @param from  the header to query in
     */
    boolean deleteStaff(String query, String from);

    /**
     * Deletes a staff in the subsequent database
     */
    boolean deleteStaff(Staff staff);

    /**
     * Function to get the list of all staff objects if needed
     *
     * @return the list of staff stored in the database
     */
    ArrayList<Staff> getStaffs();
    /**
     * Function to refresh the database by saving this context and then reading it again.
     *
     */
    void refresh();
}
