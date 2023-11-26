package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;

import java.util.ArrayList;
/**
 * The GetStudentEnquiries class holds the logic for getting the list of enquiries in the database for a given student name
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class GetStudentEnquiries implements EnquiryOperations {
    /**
     * The student name for filter
     */
    private final String name;
    /**
     * The camp manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The filtered array list of enquiries
     */
    private ArrayList<Enquiry> filtered;

    /**
     * The constructor to initialise a string and enquiry manager
     * @param enquiryManager an enquiry manager reference
     * @param name a string for filter
     */
    public GetStudentEnquiries(String name, EnquiryManager enquiryManager){
        this.name = name;
        this.enquiryManager = enquiryManager;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        ArrayList<Enquiry> enquiries = enquiryManager.getEnquiryDao().getEnquiries();
        for (Enquiry s : enquiries) {
            if (s.getCreatedBy().equals(name)) {
                filtered.add(s);
            }
        }
    }

    /**
     * The method to retrieve the list of filtered enquiries
     * @return an array list of filtered enquiries
     */
    public ArrayList<Enquiry> getFiltered() {
        return filtered;
    }

    /**
     * The method to update the list of enquiries
     * @param filtered an array list of filtered enquiries
     */
    public void setFiltered(ArrayList<Enquiry> filtered) {
        this.filtered = filtered;
    }
}
