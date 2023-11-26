package source.EnquiryOperations;

import source.Entity.Enquiry;
import source.Utility.InputHandler;

import java.time.LocalDate;
/**
 * The GetUserQuery class holds the logic for retrieving an enquiry for a given query
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class GetUserQuery implements EnquiryOperations {
    /**
     * The name of the camp the enquiry is addressed to
     */
    private final String campName;
    /**
     * The name of the Student that created the enquiry
     */
    private final String createdBy;
    /**
     * The enquiry that is extracted based on the query
     */
    private Enquiry enquiry;

    /**
     * The constructor initialises 2 string references, campName and createdBy
     *
     * @param campName  the camp name of the enquiry it is targeting
     * @param createdBy the student that made the enquiry
     */
    public GetUserQuery(String campName, String createdBy) {
        this.createdBy = createdBy;
        this.campName = campName;
    }

    /**
     * The realised execute method
     */
    @Override
    public void execute() {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter Title: ");
        String title = InputHandler.getString();
        System.out.print("Enter Enquiry Content: ");
        String content = InputHandler.getString();
        this.enquiry = new Enquiry(campName, createdBy, "", content, title, "", createdDate, null);
    }

    /**
     * The method to retrieve the targeted enquiry
     * @return specific enquiry based on query
     */
    public Enquiry getEnquiry() {
        return enquiry;
    }

    /**
     * The method to update the targeted enquiry
     * @param enquiry specific enquiry based on query
     */
    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

}