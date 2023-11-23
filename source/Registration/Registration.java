package source.Registration;
import source.Entity.Camp;
import source.Entity.Student;

public class Registration {

	private Student student;
	private Camp camp;
	private StudentRole campRole;
	private RegistrationOperations registrationOperations;

	public Registration(Student student, Camp camp, String roleType){
		this.student = student;
		this.camp = camp;
		this.CreateRole(roleType);
	}

	@Override
	public String toString() {
		return "Registered to: " + getCamp() + " as " + getCampRole();
	}

	public void CreateRole(String roleType){
		if (roleType.equals("Camp Attendee")) {
			this.campRole = new CampAttendee();
		} else if (roleType.equals("Camp Committee")) {
			this.campRole = new CampCommittee();
			this.student.setIsCampCommittee(true);
		} else {
			throw new IllegalArgumentException("Invalid role type: " + roleType);
		}
	}

	/**
	 * 
	 * Student
	 */
	public void setStudent(Student student) {

		this.student = student;
	}

	public Student getStudent() {

		return student;
	}	

	/**
	 * 
	 * Camp
	 */
	public void setCamp(Camp camp) {

		this.camp = camp;
	}

	public Camp getCamp() {

		return camp;
	}

	/**
	 * 
	 * CampRole
	 */
	public void setCampRole(StudentRole campRole) {
		this.campRole = campRole;
	}

	public StudentRole getCampRole() {

		return campRole;
	}

	/**
	 * 
	 * RegistrationOperations
	 */
	public void UseRegistrationOperations(RegistrationOperations registrationOperations) {

			registrationOperations.execute();
	}

	public void setRegistrationOperations(RegistrationOperations registrationOperations) {

		this.registrationOperations = registrationOperations;
	}

	public RegistrationOperations getRegistrationOperations() {

		return registrationOperations;
	}
}