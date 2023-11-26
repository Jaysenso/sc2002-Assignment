package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Database.DatabaseQuery;
import source.Entity.Enquiry;

import java.util.ArrayList;
/**
 * The QueryReadEnquiries class holds the logic for retrieving an array list of enquiries from the database based on a given query
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class QueryReadEnquiries implements EnquiryOperations {
    /**
     * The query for filtering
     */
    private final DatabaseQuery query;
    /**
     * The camp manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The targeted list of enquiries
     */
    private ArrayList<Enquiry> enquiries;

    /**
     * The constructor to initialise a query and enquiry manager
     *
     * @param query a query to read in our database
     * @param enquiryManager an enquiry manager reference
     */
    public QueryReadEnquiries(DatabaseQuery query, EnquiryManager enquiryManager){
        this.query = query;
        this.enquiryManager = enquiryManager;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
       enquiries = enquiryManager.getEnquiryDao().readEnquiries(query);
    }

    /**
     * The method to retrieve the targeted list of enquiries
     * @return an array list of targeted enquiries
     */
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * The method to update the targeted list of enquiries
     * @param enquiries an array list of targeted enquiries
     */
    public void setEnquiries(ArrayList<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }
}
