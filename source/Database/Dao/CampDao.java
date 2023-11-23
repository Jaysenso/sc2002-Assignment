package source.Database.Dao;

import source.Entity.Camp;

import java.util.ArrayList;

/**
 * The CampDao interface provides operations that follow the CRUD operations for persistent data storage.
 * Also provides a method to get an entire list of Camps
 * and delete them.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface CampDao {
    /**
     * Creates a camp in the subsequent database
     *
     * @param camp the Camp to add
     */
    boolean createCamp(Camp camp);

    /**
     * Reads a Camp from the subsequent database using a given query and from which table
     * Should be mainly used for name queries.
     *
     * @param query query to check in our header
     * @param from  the header to query in
     */
    Camp readCamp(String query, String from);

    /**
     * Reads all camps that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @param from  the header to query in
     * @return an arraylist of camps if found, an empty list if not.
     */
    ArrayList<Camp> readCamps(String query, String from);

    /**
     * Updates a camp in the subsequent database. Directly updates the file straight away.
     *
     * @param camp the camp to update
     */
    boolean updateCamp(Camp camp);

    /**
     * Deletes a camp in the subsequent database
     *
     * @param query query to check in our header
     * @param from  the header to query in
     */
    boolean deleteCamp(String query, String from);

    /**
     * Deletes a camp in the subsequent database
     */
    boolean deleteCamp(Camp camp);

    /**
     * Function to get the list of all Camp objects if needed
     *
     * @return the list of Camp stored in the database
     */
    ArrayList<Camp> getCamps();
}
