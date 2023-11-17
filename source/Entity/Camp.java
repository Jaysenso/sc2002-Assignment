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

    public void addAttendees(Student attendee){
        this.attendees.add(attendee);
    }

    public void addCommittee(Student attendee){
        this.campCommitteeMembers.add(attendee);
    }

    public void addInquiry(Enquiry enquiry){
        this.enquiryList.add(enquiry);
    }

    //check if date is before reg losing date
    public boolean isAvailable(LocalDate date){
        return !date.isBefore(campInfo.getClosingDate());
    }

    //toggle visibility
    public void toggleVisibility(){
        this.visibility = (!this.visibility);
    }
}
