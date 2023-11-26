package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Database.DatabaseQuery;
import source.Entity.Enquiry;

import java.util.ArrayList;
/**
 * The QueriesReadEnquiries class holds the logic for retrieving an array list of enquiries based on multiple queries
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class QueriesReadEnquiries implements EnquiryOperations {
    /**
     * The list of queries for filter
     */
    private final DatabaseQuery[] queries;
    /**
     * The camp manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The list of enquiries that is retrieved from the database based on the given queries
     */
    private ArrayList<Enquiry> enquiries;

    /**
     * The constructor to initialise an enquiry manager and a list of queries
     *
     * @param enquiryManager an enquiry manager reference
     * @param queries an array list of queries
     */
    public QueriesReadEnquiries(EnquiryManager enquiryManager, DatabaseQuery[] queries){
        this.enquiryManager = enquiryManager;
        this.queries = queries;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        enquiries = enquiryManager.getEnquiryDao().readEnquiries(queries);
    }

    /**
     * The method to retrieve the targeted enquiries
     * @return an array list of targeted enquiries
     */
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * The method to update the targeted enquiries
     * @param enquiries an array list of targeted enquiries
     */
    public void setEnquiries(ArrayList<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }
}
