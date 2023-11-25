package source.Entity;

import source.Database.App;
import source.Utility.DateRangeValidator;
import source.Utility.Option;
import source.Utility.PrettyPage;

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

    public CampInfo getCampInfo() {
        return this.campInfo;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean registerAttendees(Student student){

        DateRangeValidator checker = new DateRangeValidator(this.campInfo.getStartDate(), this.campInfo.getEndDate());

        if(isAttendee(student)){
            PrettyPage.printError("Error: You have already registered.");
            return false;
        }

        for (Camp camp : student.getRegisteredCamps()) {
            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("Error : You are already registered for a camp on the same date.");
                return false;
            }
        }

        if (this.getCampInfo().getCurrentSlots() >= this.getCampInfo().getMaxSlots()) {
            PrettyPage.printError("Error : Camp is already full.");
            return false;
        }

        if (LocalDate.now().isAfter(this.campInfo.getClosingDate())) {
            PrettyPage.printError("Error : Registration period has closed.");
            return false;
        }

        this.attendees.add(student);
        this.campInfo.updateCurrentSlot(attendees,campCommitteeMembers);
        student.addRegisteredCamps(this);
        PrettyPage.printLine(new Option("Success","You Have Registered Successfully for " + this.getCampInfo().getName()));
        return true;
    }

    public boolean registerCommittees(Student committee){

        if(isAttendee(committee)){
            PrettyPage.printError("Error: You are already an Attendee for this camp. ");
            return false;
        }
        if(committee.getIsCampCommittee() != null && committee.getIsCampCommittee() != this) {
            PrettyPage.printError("Error: You are already Camp Committee for another camp.");
            return false;
        }

        for(Student registeredCampCommittee : campCommitteeMembers){
            if(committee == registeredCampCommittee){
                PrettyPage.printError("Error: You are already Camp Committee for this camp.");
                return false;
            }
        }

        DateRangeValidator checker = new DateRangeValidator(this.campInfo.getStartDate(), this.campInfo.getEndDate());
        for (Camp camp : committee.getRegisteredCamps()) {

            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("Error : You are already registered for a camp on the same date.");
                return false;
            }
        }

        if (this.getCampInfo().getCampCommitteeSlots() >= this.getCampInfo().getMaxCampCommitteeSlots()) {
            PrettyPage.printError("Error : Camp Committee slots are full.");
            return false;
        }

        if (committee.getIsCampCommittee() != null) {
            PrettyPage.printError("Error : You are already a camp committee.");
            return false;
        }

        if (LocalDate.now().isAfter(this.campInfo.getClosingDate())) {
            PrettyPage.printError("Error : Registration period has closed.");
            return false;
        }

        this.campCommitteeMembers.add(committee);
        this.campInfo.updateCampCommitteeSlots(campCommitteeMembers.size());
        committee.setIsCampCommittee(this);
        committee.addRegisteredCamps(this);
        PrettyPage.printError("Registered Successfully.");
        return true;
    }

    public boolean withdrawAttendees(Student attendee) {
        if (!isAttendee(attendee)) {
            PrettyPage.printError("You are not part of the camp!");
            return false;

        } else {
            this.attendees.remove(attendee);
            this.campInfo.updateCurrentSlot(attendees,campCommitteeMembers);
            attendee.removeRegisteredCamps(this);
            PrettyPage.printError("Registered Successfully.");
            return true;
        }
    }

    public boolean isAttendee(Student x) {
        for(Student attendee : attendees){
            if(attendee.equals(x)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCommittee(Student x) {
        for(Student attendee : campCommitteeMembers){
            if(attendee.equals(x)) {
                return true;
            }
        }
        return false;
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

    public void shallowCopy(Camp camp){
        this.campInfo = camp.campInfo;
        this.visibility = camp.visibility;
        this.attendees = camp.attendees;
        this.campCommitteeMembers = camp.campCommitteeMembers;
        this.enquiryList = camp.enquiryList;
    }
}
