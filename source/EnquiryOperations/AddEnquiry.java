package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;
import source.Entity.Student;
/**
 * The AddEnquiry class is to hold the logic for adding an enquiry made by a student
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class AddEnquiry implements EnquiryOperations {
    /**
     * The camp manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The student reference
     */
    private final Student student;
    /**
     * The enquiry to be added
     */
    private final Enquiry enquiry;

    /**
     * An overloaded constructor to initialise a student, enquiry and enquiry manager
     *
     * @param enquiry the enquiry to create
     * @param student student reference
     * @param enquiryManager enquiry manager reference
     */
    public AddEnquiry(Student student, Enquiry enquiry, EnquiryManager enquiryManager) {
        this.enquiryManager = enquiryManager;
        this.student = student;
        this.enquiry = enquiry;
    }
    /**
     * The realised execute method
     */
    public void execute() {
        student.addEnquiry(enquiry);
        enquiryManager.getEnquiryDao().createEnquiry(enquiry);
    }
}
