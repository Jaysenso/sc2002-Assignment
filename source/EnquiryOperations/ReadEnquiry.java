package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Database.DatabaseQuery;
import source.Entity.Enquiry;
/**
 * The ReadEnquiry class holds the logic for reading a specific enquiry based on a given query
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class ReadEnquiry implements EnquiryOperations {
    /**
     * The enquiry manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The query to be used for filtering
     */
    private final DatabaseQuery query;
    /**
     * The targeted enquiry based on given query
     */
    private Enquiry enquiry;

    /**
     * The constructor to intiialise a query and enquiry manager
     *
     * @param query a query to read in our database
     * @param enquiryManager a enquiry manager reference
     */
    public ReadEnquiry(DatabaseQuery query, EnquiryManager enquiryManager){
        this.query = query;
        this.enquiryManager = enquiryManager;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        enquiry = enquiryManager.getEnquiryDao().readEnquiry(query);
    }

    /**
     * The method to retrieve the targeted enquiry
     * @return the targeted enquiry
     */
    public Enquiry getEnquiry() {
        return enquiry;
    }

    /**
     * The method to update the targeted enquiry
     * @param enquiry the targeted enquiry
     */
    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }
}
