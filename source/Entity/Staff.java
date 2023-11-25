package source.Entity;

import source.Database.DatabaseQuery;
import source.Faculty.Faculty;
import source.Database.Dao.CampDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Filter;
import java.util.stream.Collectors;


public class Staff extends User{
    private final CampDao campDao;
    public Staff(String name, String userID, String password, Faculty facultyInfo, CampDao campDao) {
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


    public void replyToEnquiries(String enquiryID, String response) {
        Enquiry enquiry = findEnquiryByTitle(enquiryID);
        if (enquiry != null && !enquiry.getProcessed()) {
            enquiry.setContent(response);
            enquiry.setRepliedBy(this.getUserID());
            enquiry.setRepliedDate(LocalDate.now());
            enquiry.setProcessed(true);
            System.out.println("Reply added to enquiry: " + enquiryID);
        } else {
            System.out.println("Enquiry not found or already processed.");
        }
    }

    private Enquiry findEnquiryByTitle(String title) {
        // Assuming campDao.getCamps() returns a list of all camps
        for (Camp camp : campDao.getCamps()) {
            for (Enquiry enquiry : camp.getEnquiryList()) {
                if (Objects.equals(enquiry.getTitle(), title)) {
                    return enquiry;
                }
            }
        }
        return null; // If no matching enquiry is found
    }

    /**
     * Generates a report of the list of students attending the camps created by this staff.
     *
     * @param roleFilter Filters the participants by their role (e.g., "attendee", "campCommittee").
     * @return A string that represents the report.
     */
    public String generateAttendanceReport(String roleFilter) {
        List<Camp> allCamps = campDao.getCamps();
        List<Camp> myCamps = allCamps.stream()
                .filter(camp -> camp.getCampInfo().getStaffInCharge().equals(this.getUserID()))
                .toList();

        StringBuilder reportBuilder = new StringBuilder();
        for (Camp camp : myCamps) {
            reportBuilder.append("Camp Name: ").append(camp.getCampInfo().getName()).append("\n");
            reportBuilder.append("Camp Details: ").append(camp.getCampInfo().toString()).append("\n");

            List<Student> participants;
            if ("attendee".equalsIgnoreCase(roleFilter)) {
                participants = camp.getAttendees();
            } else if ("campCommittee".equalsIgnoreCase(roleFilter)) {
                participants = camp.getCampCommitteeMembers();
            } else {
                // If no specific role filter is provided, or it's an unknown filter, combine both lists
                participants = new ArrayList<>(camp.getAttendees());
                participants.addAll(camp.getCampCommitteeMembers());
            }

            for (Student student : participants) {
                reportBuilder.append(student.toString()).append("\n");
            }
            reportBuilder.append("\n");
        }

        return reportBuilder.toString();
    }



    public List<Suggestion> viewSuggestions() {
        List<Suggestion> suggestions = new ArrayList<>();
        return suggestions;
    }

    public void approveSuggestion(String suggestionID, List<Suggestion> suggestions) {
        Suggestion suggestion = findSuggestionById(suggestionID, suggestions);
        if (suggestion != null && !suggestion.isApproved()) {
            suggestion.setApproved(true);
            System.out.println("Suggestion approved: " + suggestionID);
        } else if (suggestion == null) {
            System.out.println("Suggestion not found with ID: " + suggestionID);
        } else if (suggestion.isApproved()) {
            System.out.println("Suggestion with ID: " + suggestionID + " is already approved.");
        }
    }

    private Suggestion findSuggestionById(String suggestionID, List<Suggestion> suggestions) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getSuggestionID().equals(suggestionID)) {
                return suggestion;
            }
        }
        return null; // Suggestion not found
    }




}
