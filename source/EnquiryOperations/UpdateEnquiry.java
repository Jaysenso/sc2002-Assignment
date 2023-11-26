package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;
/**
 * The UpdatedEnquiry class holds the logic for updating a given enquiry
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class UpdateEnquiry implements EnquiryOperations {
    /**
     * The enquiry to be updated
     */
    private final Enquiry enquiry;
    /**
     * The enquiry manager reference
     */
    private final EnquiryManager enquiryManager;

    /**
     * The constructor to initialise an enquiry manager and enquiry
     *
     * @param enquiry an enquiry to update
     * @param enquiryManager an enquiry manager reference
     */
    public UpdateEnquiry(Enquiry enquiry, EnquiryManager enquiryManager){
        this.enquiry = enquiry;
        this.enquiryManager = enquiryManager;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        enquiryManager.getEnquiryDao().updateEnquiry(enquiry);
    }
}
