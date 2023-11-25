package source.Entity;

import source.Faculty.Faculty;

import java.util.ArrayList;

/**
 * The Student class holds all information about its registered camps, its enquiries and all other information such as points.
 *
 * @author Isaac Chun
 * @version 1.4
 * @since 11/23/2023
 */
public class Student extends User {
    /**
     * The list of camps the student is registered in
     */
    private ArrayList<Camp> registeredCamps;
    /**
     * --
     */
    private ArrayList<Registration> registrations;
    /**
     * The list of enquiries the student has
     */
    private ArrayList<Enquiry> enquiries;
    /**
     * The camp the student is a committee of
     */
    private Camp isCampCommittee;
    /**
     * The number of points a student has
     */
    private int accumulatedPoints;

    /**
     * A default constructor.
     */
    public Student() {
        super();
        this.registeredCamps = new ArrayList<>();
        this.registrations = new ArrayList<>();
        this.enquiries = new ArrayList<>();
        this.isCampCommittee = null;
        this.accumulatedPoints = 0;
    }

    /**
     * An overloaded constructor that initializes the values of Student and its super class.
     *
     * @param name              the name of the student
     * @param userID            the userID of the student
     * @param password          the password of the student
     * @param facultyInfo       the faculty of the student
     * @param accumulatedPoints the points of the student
     */
    public Student(String name,
                   String userID,
                   String password,
                   Faculty facultyInfo,
                   int accumulatedPoints) {
        super(name, userID, password, facultyInfo);
        this.registrations = new ArrayList<Registration>();
        this.registeredCamps = new ArrayList<Camp>();
        this.enquiries = new ArrayList<Enquiry>();
        this.isCampCommittee = null;
        this.accumulatedPoints = accumulatedPoints;
    }

    /**
     * RegisteredCamps
     */
    public void addRegisteredCamps(Camp camp) {
        this.registeredCamps.add(camp);
    }

    public void removeRegisteredCamps(Camp camp) {
        this.registeredCamps.remove(camp);
    }

    /**
     * Registrations
     */
    public void setRegistrations(ArrayList<Registration> registration) {

        this.registrations = registration;
    }

    public ArrayList<Registration> getRegistrations() {

        return registrations;
    }

    /**
     * A method that sets the enquiries of the student.
     *
     * @param enquiries an array list of enquiries
     */
    public void setEnquiries(ArrayList<Enquiry> enquiries) {

        this.enquiries = enquiries;
    }

    /**
     * Acquires all the enquiries made by the student.
     *
     * @return enquiries list
     */
    public ArrayList<Enquiry> getEnquiries() {

        return enquiries;
    }

    /**
     * Sets the camp reference to which this student is a camp committee of
     *
     * @param camp the camp
     */
    public void setIsCampCommittee(Camp camp) {
        this.isCampCommittee = camp;
    }

    /**
     * Acquires the camp that this student is a camp committee of
     */
    public Camp getIsCampCommittee() {
        return isCampCommittee;
    }

    /**
     * Acquires the list of camps that this student is registered for
     *
     * @return list of registered camps
     */
    public ArrayList<Camp> getRegisteredCamps() {
        return registeredCamps;
    }

    /**
     * Sets the registered camps of this student
     *
     * @param registeredCamps the new registered campssl
     */
    public void setRegisteredCamps(ArrayList<Camp> registeredCamps) {
        this.registeredCamps = registeredCamps;
    }

    /**
     * Acquires the number of points this student has
     *
     * @return the number of points the student has
     */
    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    /**
     * Sets the number of accumulated points the student has
     *
     * @param accumulatedPoints the new points
     */
    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

    /**
     * Checks if the user is an attendee of a given camp
     *
     * @return true if user is an attendee, false if not
     */
    public boolean isAttendee(Camp selectedCamp) {
        for (Camp camp : this.registeredCamps) {
            if (camp == selectedCamp)
                return true;
        }
        return false;
    }
    /**
     * Checks if the user is a camp committee of a given camp
     *
     * @return true if user is lcamp committee member, false if not
     */
    public boolean isCommittee(Camp selectedCamp) {
        return this.isCampCommittee.equals(selectedCamp);
    }
}