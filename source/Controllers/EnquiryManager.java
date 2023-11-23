package source.Controllers;

import source.Entity.Enquiry;
import source.Entity.Staff;
import source.Entity.User;

import java.util.ArrayList;

public class EnquiryManager {
    public void addEnquiry(Enquiry enquiry, ArrayList<Enquiry> enquiryList){
        enquiryList.add(enquiry);
    }

    public void deleteEnquiry(Enquiry enquiry, ArrayList<Enquiry> enquiryList){
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

    public void editEnquiry(User user, Enquiry enquiry, String content){
        //If user is Staff, will edit his reply
        //if (user instanceof Staff){enquiry.setReply(content);}
        //If user is Student, will edit his enquiry
        //else{enquiry.setContent(content);}
    }

    public void viewEnquiries(){

    }

}
