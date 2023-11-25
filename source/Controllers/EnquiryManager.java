package source.Controllers;

import source.Database.Dao.EnquiryDao;
import source.Database.DatabaseQuery;
import source.Database.EnquiryDaoImpl;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Entity.User;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class EnquiryManager {

    private final EnquiryDao enquiryDao;

    public EnquiryManager() {
        this.enquiryDao = new EnquiryDaoImpl(DirectoryUtility.ENQUIRY_LIST_PATH);
    }

    public void addEnquiry(Enquiry enquiry, Student student) {
        student.getEnquiries().add(enquiry);
        enquiryDao.createEnquiry(enquiry);
    }

    public Enquiry getUserQuery(String campName, String createdBy) {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter Title: ");
        String title = InputHandler.getString();
        System.out.print("Enter Enquiry Content: ");
        String content = InputHandler.getString();
        return new Enquiry(campName, createdBy, "", content, title, "", createdDate, null);
    }

    public void deleteStudentEnquiry(Enquiry enquiry) {
        enquiryDao.deleteEnquiry(enquiry);
    }

    public void editStudentEnquiry(Enquiry enquiry) {
        enquiryDao.updateEnquiry(enquiry);
    }

    public ArrayList<Enquiry> getStudentEnquiries(String name) {
        return enquiryDao.readEnquiries(new DatabaseQuery(name, "created_by"));
    }

    public ArrayList<Enquiry> getCampEnquiries(String name) {
        return enquiryDao.readEnquiries(new DatabaseQuery(name, "camp_name"));
    }

    public void replyEnquiry(Enquiry enquiry, User user) {
        LocalDate createdDate = LocalDate.now();
        enquiry.setRepliedDate(createdDate);
        enquiry.setRepliedBy(user.getUserID());
        enquiry.setProcessed(true);
        System.out.println("Enter ");
        String replyMessage = InputHandler.getString();
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

    public void editEnquiry(User user, Enquiry enquiry, String content) {
        //If user is Staff, will edit his reply
        //if (user instanceof Staff){enquiry.setReply(content);}
        //If user is Student, will edit his enquiry
        //else{enquiry.setContent(content);}
    }

    public Enquiry readEnquiry(DatabaseQuery query) {
        return enquiryDao.readEnquiry(query);
    }

    public ArrayList<Enquiry> readEnquiries(DatabaseQuery query) {
        return enquiryDao.readEnquiries(query);
    }

    public ArrayList<Enquiry> readEnquiries(DatabaseQuery[] query) {
        return enquiryDao.readEnquiries(query);
    }

}
