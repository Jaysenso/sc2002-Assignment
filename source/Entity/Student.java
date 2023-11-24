package source.Entity;

import source.Camp.CampService;
import source.Faculty.Faculty;
import source.Registration.Registration;

import java.util.ArrayList;
/**
 * The Student class holds information about students.
 *
 * @author Edwin Lim
 * @version 1.0
 * @see Camp
 * @since 11/4/2023
 */
public class Student extends User {

	private ArrayList<Registration> registrations;
	private ArrayList<Enquiry> enquiries;
	private CampService campService;
	private boolean visibility = false;
	private boolean isCampCommittee = false;

	public Student(String name, String userID, String password, Faculty facultyInfo) {
		super(name, userID, password, facultyInfo);
		this.registrations = new ArrayList<Registration>();
		this.enquiries = new ArrayList<Enquiry>();
	}

	@Override
	public String toString() {
		return "Student Name: " + getName();
	}

    public void createRegistration(Camp camp, String roleType) {

        Registration registration = new Registration(this, camp, roleType);
        registrations.add(registration);
    }

    public void createEnquiry(Camp camp, String content, String title) {

        Enquiry enquiry = new Enquiry(this, camp, content, title);
        enquiries.add(enquiry);
    }

	/**
	 * Registrations
	 */
	public void addRegistration(Registration registration){
		registrations.add(registration);
	}

    public void setRegistrations(ArrayList<Registration> registration) {

		this.registrations = registration;
    }

	public ArrayList<Registration> getRegistrations() {

		return registrations;
    }

	/**
	 * Enquiries
	 */
	public void addEnquiry(Enquiry enquiry){
		enquiries.add(enquiry);
	}

    public void setEnquiries(ArrayList<Enquiry> enquiries) {

		this.enquiries = enquiries;
    }

	public ArrayList<Enquiry> getEnquiries() {

		return enquiries;
    }

	/**
	 * Visibility
	 */
	public void setVisibility(boolean visibility) {

		this.visibility = visibility;
	}

	public boolean getVisibility() {

		return visibility;
	}

	/**
	 * IsCampCommittee
	 */
	public void setIsCampCommittee(boolean isCampCommittee) {

		this.isCampCommittee = isCampCommittee;
	}

	public boolean getIsCampCommittee() {

		return isCampCommittee;
	}	

	/**
	 * CampService
	 */
	public void UseCampService(CampService campService) {

		campService.execute();
	}

	public void setCampService(CampService campService) {

		this.campService = campService;
	}

	public CampService getCampService() {

		return campService;
	}

}