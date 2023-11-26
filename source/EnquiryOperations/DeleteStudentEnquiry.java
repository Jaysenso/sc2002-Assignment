package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Utility.PrettyPage;

/**
 * The DeleteStudentEnquiry class holds the logic for deleting a given enquiry filtered for a given student name in the enquiry database
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class DeleteStudentEnquiry implements EnquiryOperations {
    /**
     * The enquiry to be deleted
     */
    private final Enquiry enquiry;
    /**
     * The student reference
     */
    private final Student student;
    /**
     * The camp manager reference
     */
    private final EnquiryManager enquiryManager;

    /**
     * The constructor to initialise an enquiry, student and enquiry manager
     *
     * @param enquiry the enquiry to delete
     * @param enquiryManager the enquiry manager reference
     * @param student the student reference for filter
     */
    public DeleteStudentEnquiry(Enquiry enquiry, Student student, EnquiryManager enquiryManager) {
        this.enquiry = enquiry;
        this.student = student;
        this.enquiryManager = enquiryManager;
    }
    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        if (!enquiry.getProcessed()) {
            student.removeEnquiry(enquiry);
            enquiryManager.getEnquiryDao().deleteEnquiry(enquiry);
        } else {
            PrettyPage.printError("Your Enquiry has already been processed!");
        }
    }
}