package source.EnquiryOperations;

import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;
import source.Entity.Staff;
import source.Entity.Student;
import source.Entity.User;
import source.Utility.InputHandler;

import java.time.LocalDate;
/**
 * The ReplyEnquiry class holds the logic for replying to a given enquiry
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class ReplyEnquiry implements EnquiryOperations {
    /**
     * The enquiry manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The enquiry to be replied to
     */
    private final Enquiry enquiry;
    /**
     * The user reference
     */
    private final User user;

    /**
     * The constructor to initialise an enquiry manager, enquiry and user
     *
     * @param enquiry a enquiry
     * @param user    a logged-in user reference
     * @param enquiryManager an enquiry manager reference
     */
    public ReplyEnquiry(EnquiryManager enquiryManager, Enquiry enquiry, User user) {
        this.enquiry = enquiry;
        this.enquiryManager = enquiryManager;
        this.user = user;
    }

    /**
     * The realised execute method
     */
    public void execute() {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter reply message: ");
        String replyMessage = InputHandler.tryGetString();
        //Loop through and find the relevant entries
        for (Enquiry e : enquiryManager.getEnquiryDao().getEnquiries()) {
            if (e.equals(enquiry)) {
                e.setReply(replyMessage);
                break;
            }
        }
        enquiry.setReply(replyMessage);
        //Then assign the user types and
        String userType = (user instanceof Staff) ? "Staff in Charge" : "Camp Committee Member";

        //Check if it student or staff
        if (user instanceof Student) {
            Student s = (Student) user;
            //Increment the points of the student
            s.setAccumulatedPoints(s.getAccumulatedPoints() + 1);
        }
        enquiry.setRepliedDate(createdDate);
        enquiry.setRepliedBy(user.getUserID() + " (" + userType + ")");
        enquiry.setProcessed(true);

        enquiryManager.getEnquiryDao().updateEnquiry(enquiry);
    }
}
