package source.Entity;

import source.Camp.CampService;
import source.Controllers.CampManager;
import source.Database.App;
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
		this.registeredCamps = new ArrayList<>();
		this.registrations = new ArrayList<>();
		this.enquiries = new ArrayList<>();
		this.campService = null;
		this.isCampCommittee = null;
		this.accumulatedPoints = 0;
	}

	public Student(String name, String userID, String password, Faculty facultyInfo, int accumulatedPoints) {
		super(name, userID, password, facultyInfo);
		this.registrations = new ArrayList<Registration>();
		this.registeredCamps = new ArrayList<Camp>();
		this.enquiries = new ArrayList<Enquiry>();
		this.campService = null;
		this.isCampCommittee = null;
		this.accumulatedPoints = accumulatedPoints;
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
	 * RegisteredCamps
	 */

	public void setRegisteredCamps() {
		ArrayList<Camp>campList = App.getCampManager().getCamps();

		for(Camp camp : campList){
			for(Student attendee : camp.getAttendees()) {
				if(this == attendee) {
					registeredCamps.add(camp);
				}
			}
		}
	}

	public void addRegisteredCamps(Camp camp){
		this.registeredCamps.add(camp);
	}

	public void removeRegisteredCamps(Camp camp) {
		this.registeredCamps.remove(camp);
	}

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