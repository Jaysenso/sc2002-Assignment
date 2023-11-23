package source.Database.Dao;

import source.Entity.Enquiry;

import java.util.ArrayList;
import java.util.List;

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
     */
    boolean createEnquiry(Enquiry Enquiry);

    /**
     * Reads an enquiry from the subsequent database using a given query and from which table
     * Should be mainly used for name queries.
     *
     * @param query query to check in our header
     * @param from  the header to query in
     */
    Enquiry readEnquiry(String query, String from);

    /**
     * Reads all Enquiries that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @param from  the header to query in
     * @return an arraylist of Enquiry if found, an empty list if not.
     */
    ArrayList<Enquiry> readEnquiries(String query, String from);

    /**
     * Updates an enquiry in the subsequent database. Directly updates the file straight away.
     *
     * @param Enquiry the Enquiry to update
     */
    boolean updateEnquiry(Enquiry Enquiry);

    /**
     * Deletes an enquiry in the subsequent database
     *
     * @param query query to check in our header
     * @param from  the header to query in
     */
    boolean deleteEnquiry(String query, String from);

    /**
     * Deletes an enquiry in the subsequent database
     */
    boolean deleteEnquiry(Enquiry Enquiry);

    /**
     * Function to get the list of all Enquiry objects if needed
     *
     * @return the list of Enquiry stored in the database
     */
    ArrayList<Enquiry> getEnquiries();
}
