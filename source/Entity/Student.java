package source.Entity;

import source.Camp.CampService;
import source.Faculty.Faculty;

import java.util.ArrayList;

public class Student extends User {
	private ArrayList<Camp> registeredCamps;
	private ArrayList<Registration> registrations;
	private ArrayList<Enquiry> enquiries;
	private CampService campService;
	private boolean visibility = false;
	private Camp isCampCommittee;


	public Student() {
		super();
	}

	public Student(String name, String userID, String password, Faculty facultyInfo) {
		super(name, userID, password, facultyInfo);
		this.registrations = new ArrayList<Registration>();
		this.registeredCamps = new ArrayList<Camp>();
		this.enquiries = new ArrayList<Enquiry>();
		this.isCampCommittee = null;
	}

	@Override
	public String toString() {
		return "Student Name: " + getName();
	}

//    public boolean createRegistration(Camp selectedCamp, String roleType) {
//		try{
//			for(Registration registration : registrations) {
//				registration.getCamp().getCampInfo().getStartDate()
//			}
//
//			if(selectedCamp.getCampInfo().getCurrentSlots() >= s)
//		}
//
//        Registration registration = new Registration(this, camp, roleType);
//        registrations.add(registration);
//    }
//
//    public void createEnquiry(Camp camp, String content, String title) {

//        Enquiry enquiry = new Enquiry(this, camp, content, title);
//        enquiries.add(enquiry);
 //   }

	/**
	 * 
	 * Registrations
	 */

    public void setRegistrations(ArrayList<Registration> registration) {

		this.registrations = registration;
    }

	public ArrayList<Registration> getRegistrations() {

		return registrations;
    }

	/**
	 * 
	 * Enquiries
	 */
    public void setEnquiries(ArrayList<Enquiry> enquiries) {

		this.enquiries = enquiries;
    }

	public ArrayList<Enquiry> getEnquiries() {

		return enquiries;
    }

	/**
	 * 
	 * Visibility
	 */
	public void setVisibility(boolean visibility) {

		this.visibility = visibility;
	}

	public boolean getVisibility() {

		return visibility;
	}

	public void updateRegisteredCamps(Camp camp) {
		this.registeredCamps.add(camp);
	}

	/**
	 * 
	 * IsCampCommittee
	 */
	public void setIsCampCommittee(Camp camp) {
		this.isCampCommittee = camp;
	}

	public Camp getIsCampCommittee() {
		return isCampCommittee;
	}	

	/**
	 * 
	 * Source.Camp.CampService
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

	public ArrayList<Camp> getRegisteredCamps() {
		return registeredCamps;
	}

	public void setRegisteredCamps(ArrayList<Camp> registeredCamps) {
		this.registeredCamps = registeredCamps;
	}
}