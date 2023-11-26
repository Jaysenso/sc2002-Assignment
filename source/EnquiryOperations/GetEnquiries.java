package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;

import java.util.ArrayList;
/**
 * The GetEnquiries class holds the logic for getting an array list of enquiries in the enquiries database
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class GetEnquiries implements EnquiryOperations {
    /**
     * The camp manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The enquiry manager reference
     */
    private ArrayList<Enquiry> enquiries;

    /**
     * The constructor that initialises an enquiry manager
     * @param enquiryManager an enquiry manager reference
     */
    public GetEnquiries(EnquiryManager enquiryManager){
        this.enquiryManager = enquiryManager;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        enquiries = enquiryManager.getEnquiryDao().getEnquiries();
    }
    /**
     * The method to retrieve the list of enquiries
     */
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * The method to update the list of enquiries
     */
    public void setEnquiries(ArrayList<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }
}
