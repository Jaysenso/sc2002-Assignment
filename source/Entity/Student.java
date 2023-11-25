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
	private int accumulatedPoints;


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

	/**
	 *
	 * RegisteredCamps
	 */
	public void addRegisteredCamps(Camp camp){
		//Prevent duplicates
		for(Camp c : registeredCamps)
		{
			if(c.equals(camp))
				return;
		}
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

	public int getAccumulatedPoints() {
		return accumulatedPoints;
	}

	public void setAccumulatedPoints(int accumulatedPoints) {
		this.accumulatedPoints = accumulatedPoints;
	}

	public boolean isAttendee(Camp selectedCamp) {
		for(Camp camp : this.registeredCamps){
			if(selectedCamp.equals(camp))
				return true;
		}
		return false;
	}

	public boolean isCommittee(Camp selectedCamp) {
		return this.isCampCommittee == selectedCamp;
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

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true
		if (obj == this) {
			return true;
		}

        /* Check if o is an instance of Student or not
          "null instanceof [type]" also returns false */
		if (!(obj instanceof Student)) {
			return false;
		}
		Student s = (Student) obj;
		// Compare the data members and return accordingly
		return getName().equals(s.getName());
	}
}