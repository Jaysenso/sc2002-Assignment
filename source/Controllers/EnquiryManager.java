package source.Controllers;

import source.Database.Dao.EnquiryDao;
import source.Database.DatabaseQuery;
import source.Database.EnquiryDaoImpl;
import source.Entity.Enquiry;
import source.Entity.Staff;
import source.Entity.Student;
import source.Entity.User;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The EnquiryManager class serves as a DB and abstracts the Data Access Object implementations and provides
 * what's necessary for others to be used for enquiries
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class EnquiryManager {
    /**
     * The enquiry data access object that contains direct implementations to our database..
     */
    private final EnquiryDao enquiryDao;

    /**
     * A default constructor.
     */
    public EnquiryManager() {
        this.enquiryDao = new EnquiryDaoImpl(DirectoryUtility.ENQUIRY_LIST_PATH);
    }

    /**
     * A function that creates an enquiry in the database and adds it to the student's list
     *
     * @param enquiry the enquiry to create
     * @param student student reference
     */
    public void addEnquiry(Enquiry enquiry, Student student) {
        student.addEnquiry(enquiry);
        enquiryDao.createEnquiry(enquiry);
    }

    /**
     * Handles sub logic functions of creating a new enquiry
     *
     * @param campName  the camp name of the enquiry it is targeting
     * @param createdBy the student
     */
    public Enquiry getUserQuery(String campName, String createdBy) {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter Title: ");
        String title = InputHandler.getString();
        System.out.print("Enter Enquiry Content: ");
        String content = InputHandler.getString();
        return new Enquiry(campName, createdBy, "", content, title, "", createdDate, null);
    }

    /**
     * Attempts to delete the student enquiry from the database
     *
     * @param enquiry the enquiry to delete
     */
    public void deleteStudentEnquiry(Student student, Enquiry enquiry) {
        if (!enquiry.getProcessed()) {
            student.removeEnquiry(enquiry);
            enquiryDao.deleteEnquiry(enquiry);
        } else {
            PrettyPage.printError("Your Enquiry has already been processed!");
        }
    }

    /**
     * Updates the enquiry in our database
     *
     * @param enquiry the enquiry to update
     */
    public void updateEnquiry(Enquiry enquiry) {
        enquiryDao.updateEnquiry(enquiry);
    }

    /**
     * Acquires all student enquiries given a name
     *
     * @param name the name to search in our DB
     */
    public ArrayList<Enquiry> getStudentEnquiries(String name) {
        ArrayList<Enquiry> filtered = new ArrayList<>();
        ArrayList<Enquiry> enquiries = enquiryDao.getEnquiries();
        for (Enquiry s : enquiries) {
            if (s.getCreatedBy().equals(name)) {
                filtered.add(s);
            }
        }
        return filtered;
    }

    /**
     * Acquires all enquiries held by the manager
     *
     * @return list of enquiries
     */
    public ArrayList<Enquiry> getEnquiries() {
        return enquiryDao.getEnquiries();
    }

    /**
     * Handles the logic of replying to an enquiry
     *
     * @param enquiry the enquiry
     * @param user    the logged in user reference
     */
    public void replyEnquiry(Enquiry enquiry, User user) {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter reply message: ");
        String replyMessage = InputHandler.tryGetString();
        enquiry.setReply(replyMessage);
        //Then assign the user types and
        String userType = (user instanceof Staff) ? "Staff in Charge" : "Camp Committee Member";
        enquiry.setRepliedDate(createdDate);
        enquiry.setRepliedBy(user.getUserID() + " (" + userType + ")");
        enquiry.setProcessed(true);

        enquiryDao.updateEnquiry(enquiry);
    }

    public void deleteEnquiry(Enquiry enquiry, ArrayList<Enquiry> enquiryList) {
        try {
            // Check if the campList contains the specified camp
            if (enquiryList.contains(enquiry)) {
                // If the camp is found, remove it from the list
                enquiryList.remove(enquiry);
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

    /**
     * Attempts to read enquiries given a query
     *
     * @param query the query to read in our database
     * @return enquiry objects that satisfy the criteria
     */
    public Enquiry readEnquiry(DatabaseQuery query) {
        return enquiryDao.readEnquiry(query);
    }

    /**
     * Attempts to read enquiries given a query
     *
     * @param query the query to read in our database
     * @return list of enquiry objects that satisfy the criteria
     */
    public ArrayList<Enquiry> readEnquiries(DatabaseQuery query) {
        return enquiryDao.readEnquiries(query);
    }

    /**
     * Attempts to read enquiries given queries
     *
     * @param query the query to read in our database
     * @return list of enquiry objects that satisfy all the criteria
     */
    public ArrayList<Enquiry> readEnquiries(DatabaseQuery[] query) {
        return enquiryDao.readEnquiries(query);
    }

}
