package source.Database.Dao;

import source.Database.DatabaseQuery;
import source.Entity.Enquiry;

import java.util.ArrayList;

/**
 * The EnquiryDao interface provides operations that follow the CRUD operations for persistent data storage.
 * Also provides a method to get an entire list of enquiries
 * and delete them.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface EnquiryDao {
    /**
     * Creates an enquiry in the subsequent database (perhaps for sign up feature~)
     *
     * @param Enquiry the Enquiry to add
     * @return true if managed to create, false if not
     */
    boolean createEnquiry(Enquiry Enquiry);

    /**
     * Reads an enquiry from the subsequent database using a given query and from which table
     * Should be mainly used for name queries.
     *
     * @param query query to check in our header
     * @return an enquiry object if managed to find in our database
     */
    Enquiry readEnquiry(DatabaseQuery query);

    /**
     * Reads an enquiry from the subsequent database using a given query and from which table
     * MUST SATISFY ALL QUERIES.
     * Should be mainly used for name queries.
     *
     * @param queries queries to check that gives us the first result that satisfies the requirements.
     * @return an enquiry object if managed to find in our database
     */
    Enquiry readEnquiry(DatabaseQuery[] queries);

    /**
     * Reads all Enquiries that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query a database query
     * @return an arraylist of Enquiry if found, an empty list if not.
     */
    ArrayList<Enquiry> readEnquiries(DatabaseQuery query);

    /**
     * Reads all Enquiries that satisfies all the properties (Overloaded)
     * NOTE: List can be empty if no results are found
     *
     * @param queries an array of database queries
     * @return an arraylist of Enquiry if found, an empty list if not.
     */
    ArrayList<Enquiry> readEnquiries(DatabaseQuery[] queries);

    /**
     * Updates an enquiry in the subsequent database. Directly updates the file straight away.
     *
     * @param Enquiry the Enquiry to update
     * @return true if managed to update, false if not
     */
    boolean updateEnquiry(Enquiry Enquiry);

    /**
     * Deletes an enquiry in the subsequent database
     *
     * @param query query to check in our header
     * @return true if managed to delete, false if not
     */
    boolean deleteEnquiry(DatabaseQuery query);

    /**
     * Deletes an enquiry in the subsequent database
     * @return true if managed to delete, false if not
     */
    boolean deleteEnquiry(Enquiry Enquiry);

    /**
     * Function to get the list of all Enquiry objects if needed
     *
     * @return the list of Enquiry stored in the database
     */
    ArrayList<Enquiry> getEnquiries();

    /**
     * Function to refresh the database by saving this context and then reading it again.
     */
    void refresh();
}
