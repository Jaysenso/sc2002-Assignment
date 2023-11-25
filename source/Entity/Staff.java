package source.Entity;

import source.Database.DatabaseQuery;
import source.Faculty.Faculty;
import source.Enquiry.EnquiryReply;
import source.Database.Dao.CampDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;


public class Staff extends User implements EnquiryReply {
    private CampDao campDao;
    public Staff(String name, String userID, String password, Faculty facultyInfo) {
        super(name, userID, password, facultyInfo);
        this.campDao = campDao;
    }

    // Implementations of CampManagement interface methods
    public void createCamp(CampInfo campInfo) {
        Camp newCamp = new Camp(campInfo, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        boolean success = campDao.createCamp(newCamp);
        if (success) {
            System.out.println("New camp created: " + campInfo.getName());
        } else {
            System.out.println("Failed to create camp.");
        }
    }

    public void editCamp(String campID, CampInfo newCampInfo) {
        DatabaseQuery query = new DatabaseQuery("campID", campID);
        Camp camp = campDao.readCamp(query);
        if (camp != null) {
            camp.shallowCopy(new Camp(newCampInfo, camp.getVisibility(), camp.getEnquiryList(), camp.getAttendees(), camp.getCampCommitteeMembers()));
            boolean success = campDao.updateCamp(camp);
            if (success) {
                System.out.println("Camp updated: " + newCampInfo.getName());
            } else {
                System.out.println("Failed to update camp.");
            }
        } else {
            System.out.println("Camp not found with ID: " + campID);
        }
    }

    public void deleteCamp(String campID) {
        DatabaseQuery query = new DatabaseQuery("campID", campID);
        boolean isDeleted = campDao.deleteCamp(query);
        if (isDeleted) {
            System.out.println("Camp deleted successfully.");
        } else {
            System.out.println("Camp not found or could not be deleted.");
        }
    }


    public void toggleCampVisibility(String campID, boolean isVisible) {
        DatabaseQuery query = new DatabaseQuery("campID", campID);
        Camp camp = campDao.readCamp(query);
        if (camp != null) {
            camp.setVisibility(isVisible);
            boolean success = campDao.updateCamp(camp);
            if (success) {
                System.out.println("Camp visibility updated: " + camp.getCampInfo().getName());
            } else {
                System.out.println("Failed to update camp visibility.");
            }
        } else {
            System.out.println("Camp not found with ID: " + campID);
        }
    }


    public ArrayList<Camp> viewAllCamps() {
        return campDao.getCamps();
    }

    public ArrayList<Camp> viewOwnCamps() {
        ArrayList<Camp> allCamps = campDao.getCamps();
        ArrayList<Camp> myCamps = new ArrayList<>();
        for (Camp camp : allCamps) {
            if (camp.getCampInfo().getStaffInCharge().equals(this.getUserID())) {
                myCamps.add(camp);
            }
        }
        return myCamps;
    }


    @Override
    public void replyToEnquiries(String enquiryID, String response) {
        Enquiry enquiry = findEnquiryById(enquiryID);
        if (enquiry != null && !enquiry.getProcessed()) {
            enquiry.setContent(response);
            enquiry.setRepliedBy(this.getUserID());
            enquiry.setRepliedDate(LocalDate.now());
            enquiry.setProcessed(true);
            // You might want to update the enquiry in the database or its parent camp
            System.out.println("Reply added to enquiry: " + enquiryID);
        } else {
            System.out.println("Enquiry not found or already processed.");
        }
    }

    private Enquiry findEnquiryById(String enquiryID) {
        // Implement logic to find and return the enquiry with the given ID
        return null; // Placeholder return
    }


    public Report generateReport(String reportType, Filter filter) {
        return null;
    }

    private Report generateAttendanceReport(Filter filter) {
        return null;
    }


    private Report generatePerformanceReport(Filter filter) {
        return null;
    }




    // Implementations of SuggestionApproval interface methods

    public List<Suggestion> viewSuggestions() {
        List<Suggestion> suggestions = new ArrayList<>();
        // Logic to add all suggestions to the list, perhaps from each camp
        return suggestions;
    }

    public void approveSuggestion(String suggestionID) {
        // Assuming you have a way to fetch a specific suggestion
        Suggestion suggestion = findSuggestionById(suggestionID);
        if (suggestion != null) {
            suggestion.setApproved(true);
            // You might want to update the suggestion in the database as well
            System.out.println("Suggestion approved: " + suggestionID);
        } else {
            System.out.println("Suggestion not found with ID: " + suggestionID);
        }
    }

    private Suggestion findSuggestionById(String suggestionID) {
        // Implement logic to find and return the suggestion with the given ID
        // This could involve searching through all camps' suggestions
        return null; // Placeholder return
    }

}
