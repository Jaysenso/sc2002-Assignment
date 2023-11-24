package source.Entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Camp {
    private CampInfo campInfo;
    private boolean visibility;
    private ArrayList<Enquiry> enquiryList;
    private ArrayList<Student> attendees;
    private ArrayList<Student> campCommitteeMembers;

    public Camp(CampInfo campInfo, boolean visibility, ArrayList<Enquiry> enquiryList, ArrayList<Student> attendees, ArrayList<Student> campCommitteeMembers) {
        this.campInfo = campInfo;
        this.visibility = visibility;
        this.enquiryList = enquiryList;
        this.attendees = attendees;
        this.campCommitteeMembers = campCommitteeMembers;
    }

    @Override
    public String toString() {
        return campInfo.getName() +
                "\n" +
                "Start Date : " + campInfo.getStartDate() +
                "\n" +
                "End Date : " + campInfo.getEndDate() +
                "\n" +
                "Registration Closing Date : " + campInfo.getClosingDate() +
                "\n" +
                "User group : " + campInfo.getFaculty().getClass().getSimpleName() +
                "\n" +
                "Attendee Slots: " + campInfo.getCurrentSlots() + "/" + campInfo.getMaxSlots() +
                "\n" +
                "Camp Committee Slots: " + campInfo.getCampCommitteeSlots() + "/" + campInfo.getMaxCampCommitteeSlots() +
                "\n" +
                "Description : " + campInfo.getDescription() +
                "\n" +
                "Staff-In-Charge : " + campInfo.getStaffInCharge();
    }

    public ArrayList<Student> getAttendees() {
        return this.attendees;
    }

    public ArrayList<Student> getCampCommitteeMembers() {
        return this.campCommitteeMembers;
    }



    public void addAttendees(Student attendee) {
        this.attendees.add(attendee);
    }

    public void addCommittee(Student attendee) {
        this.campCommitteeMembers.add(attendee);
    }

    public void addInquiry(Enquiry enquiry) {
        this.enquiryList.add(enquiry);
    }

    //check if date is before reg losing date
    public boolean isAvailable(LocalDate date) {
        return !date.isBefore(campInfo.getClosingDate());
    }

    //toggle visibility
    public void toggleVisibility() {
        this.visibility = (!this.visibility);
    }

    public CampInfo getCampInfo() {
        return this.campInfo;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Camp or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Camp)) {
            return false;
        }

        // typecast o to Camp so that we can compare data members
        Camp c = (Camp) obj;

        // Compare the data members and return accordingly
        return campInfo.getName().equals(c.campInfo.getName());
    }

    public void shallowCopy(Camp camp) {
        this.campInfo = camp.campInfo;
        this.visibility = camp.visibility;
        this.attendees = camp.attendees;
        this.campCommitteeMembers = camp.campCommitteeMembers;
        this.enquiryList = camp.enquiryList;
    }
}
