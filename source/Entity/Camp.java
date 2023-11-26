package source.Entity;


import java.util.ArrayList;

/**
 * The Camp class is an entity class that can be accessed by both Staff and Students to access any relevant information
 * to this camp
 *
 * @author Isaac Chun
 * @version 1.5
 * @since 11/20/2023
 */
public class Camp {
    /**
     * A wrapper to store all the information related to this camp
     */
    private CampInfo campInfo;
    /**
     * A boolean to dictate whether this camp is visible
     */
    private boolean visibility;
    /**
     * The list of enquiries stored in this camp
     */
    private ArrayList<Enquiry> enquiryList;
    /**
     * The attendees of this camp
     */
    private ArrayList<Student> attendees;
    /**
     * The camp committee members of this camp
     */
    private ArrayList<Student> campCommitteeMembers;
    /**
     * A list of students that are not allowed to join back this camp
     */
    private ArrayList<Student> blacklisted;

    /**
     * An overloaded constructor to initialize the initial values of the camp
     *
     * @param campInfo             A wrapper to store all the information related to this camp
     * @param visibility           A boolean to dictate whether this camp is visible
     * @param enquiryList          The list of enquiries stored in this camp
     * @param attendees            The attendees of this camp
     * @param campCommitteeMembers The camp committee members of this camp
     * @param blacklisted          A list of students that are not allowed to join back this camp
     */
    public Camp(CampInfo campInfo,
                boolean visibility,
                ArrayList<Enquiry> enquiryList,
                ArrayList<Student> attendees,
                ArrayList<Student> campCommitteeMembers,
                ArrayList<Student> blacklisted) {
        this.campInfo = campInfo;
        this.visibility = visibility;
        this.enquiryList = enquiryList;
        this.attendees = attendees;
        this.campCommitteeMembers = campCommitteeMembers;
        this.blacklisted = blacklisted;
    }

    /**
     * Acquires the list of attendees of this camp
     *
     * @return attendee list
     */
    public ArrayList<Student> getAttendees() {
        return this.attendees;
    }

    /**
     * Sets the list of attendees of this camp
     *
     * @param attendees attendees of the camp
     */
    public void setAttendees(ArrayList<Student> attendees) {
        this.attendees = attendees;
    }

    /**
     * Acquires the list of committee members of this camp
     *
     * @return camp committed list
     */
    public ArrayList<Student> getCampCommitteeMembers() {
        return this.campCommitteeMembers;
    }

    /**
     * Sets the list of camp committee in this camp
     *
     * @param campCommitteeMembers the members
     */
    public void setCampCommittee(ArrayList<Student> campCommitteeMembers) {
        this.campCommitteeMembers = campCommitteeMembers;
    }

    /**
     * Acquires the information of this camp
     *
     * @return camp information
     */
    public CampInfo getCampInfo() {
        return this.campInfo;
    }

    /**
     * Acquires the visibility status of this camp
     *
     * @return visibility
     */
    public boolean getVisibility() {
        return this.visibility;
    }

    /**
     * Sets the visibility status of this camp
     *
     * @param visibility the visibility status of the camp
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * Adds an enquiry to this camp
     *
     * @param enquiry enquiry to add
     */
    public void addInquiry(Enquiry enquiry) {
        this.enquiryList.add(enquiry);
    }

    /**
     * Acquires the list of enquiries of this camp
     *
     * @return enquiry list
     */
    public ArrayList<Enquiry> getEnquiryList() {
        return this.enquiryList;
    }

    /**
     * Sets the enquiry list of this camp
     *
     * @param enquiryList new enquiry list
     */
    public void setEnquiryList(ArrayList<Enquiry> enquiryList) {
        this.enquiryList = enquiryList;
    }

    /**
     * Adds an attendee to this camp
     *
     * @param student new student to add
     */
    public void addAttendee(Student student) {
        this.attendees.add(student);
        updateCampInfoCurrentSlots();
    }

    /**
     * Adds a camp committee to this camp
     *
     * @param attendee new student to add
     */
    public void addCommittee(Student attendee) {
        this.campCommitteeMembers.add(attendee);
        updateCampInfoCurrentSlots();
    }

    /**
     * Removes an attendee from this camp
     *
     * @param attendee new student to add
     */
    public void withdrawAttendee(Student attendee) {
        this.attendees.remove(attendee);
        updateCampInfoCurrentSlots();
    }

    /**
     * Updates the camp information slots
     */
    public void updateCampInfoCurrentSlots() {
        int currentSlots = attendees.size() + campCommitteeMembers.size();
        this.campInfo.setCurrentSlots(currentSlots);
    }

    /**
     * Overloaded equals to treat two camps as equal as long as their names match
     */
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

    /**
     * Acquires the list of blacklisted students from this camp
     *
     * @return black listed students
     */
    public ArrayList<Student> getBlacklisted() {
        return blacklisted;
    }

    /**
     * Sets the black listed list of this camp
     *
     * @param blacklisted the new list to add
     */
    public void setBlacklisted(ArrayList<Student> blacklisted) {
        this.blacklisted = blacklisted;
    }

    /**
     * Adds a student to be blacklisted in this camp
     *
     * @param student the student to blacklist
     */
    public void addBlacklisted(Student student) {
        this.blacklisted.add(student);
    }
}
