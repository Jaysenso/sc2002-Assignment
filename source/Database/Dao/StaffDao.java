package source.Database.Dao;

import source.Entity.Staff;

import java.util.List;

/**
 * The StaffDao interface provides operations that follow the CRUD operations for persistent data storage. Also provides a method to get an entire list of students
 * and delete them.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface StaffDao {
    /**
     * Creates a staff in the subsequent database
     *
     * @param staff the staff to add
     */
    boolean createStaff(Staff staff);

    /**
     * Reads a staff from the subsequent database
     *
     * @param staffName the name of the staff
     */
    Staff readStaff(String staffName);

    /**
     * Updates a staff in the subsequent database
     *
     * @param staff the staff to add
     */
    boolean updateStaff(Staff staff);

    /**
     * Deletes a student in the subsequent database
     *
     * @param staff the staff to add
     */
    boolean deleteStaff(Staff staff);

    /**
     * Creates a student in the subsequent database
     *
     * @return the list of students stored in the database
     */
    List<Staff> getStaffs();
}
