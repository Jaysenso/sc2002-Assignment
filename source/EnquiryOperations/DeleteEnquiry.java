package source.EnquiryOperations;

import source.Entity.Enquiry;

import java.util.ArrayList;
/**
 * The DeleteEnquiry class holds the logic for deleting a given enquiry in the enquiries database
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class DeleteEnquiry implements EnquiryOperations {
    /**
     * The enquiry to be deleted
     */
    private final Enquiry enquiry;
    /**
     * The enquiry list reference
     */
    private final ArrayList<Enquiry> enquiries;

    /**
     * The constructor to initialise an enquiry and an array list of enquiries
     * @param enquiries an array list of enquiries
     * @param enquiry the enquiry to be deleted
     */
    public DeleteEnquiry(Enquiry enquiry, ArrayList<Enquiry> enquiries){
        this.enquiries = enquiries;
        this.enquiry = enquiry;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        try {
            // Check if the campList contains the specified camp
            if (enquiries.contains(enquiry)) {
                // If the camp is found, remove it from the list
                enquiries.remove(enquiry);
                System.out.println("Camp deleted successfully");
            } else {
                // If the camp is not found, throw an exception
                throw new IllegalArgumentException("Camp not found in the list");
            }
        } catch (Exception e) {
            // Handle the exception (e.g., print an error message)
            System.err.println("Error deleting camp: " + e.getMessage());
        }
    }
}
