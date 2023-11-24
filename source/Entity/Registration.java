package source.Entity;
import source.Entity.Camp;
import source.Entity.Student;
import source.Registration.StudentRole;

/**
 * The Registration class holds information about registrations made by a particular student.
 *
 * @author Edwin Lim
 * @version 1.0
 * @see Camp
 * @see Student
 * @see StudentRole
 * @since 11/4/2023
 */
public class Registration {

	private Student student;
	private Camp camp;
	private StudentRole campRole;

	public Registration(Student student, Camp camp){
		this.student = student;
		this.camp = camp;
	}

	@Override
	public String toString() {
		return "Registered to: " + getCamp() + " as " + getCampRole();
	}

	/**
	 * Student
	 */
	public void setStudent(Student student) {

		this.student = student;
	}

	public Student getStudent() {

		return student;
	}	

	/**
	 * Camp
	 */
	public void setCamp(Camp camp) {

		this.camp = camp;
	}

	public Camp getCamp() {

		return camp;
	}

	/**
	 * CampRole
	 */
	public void setCampRole(StudentRole campRole) {
		this.campRole = campRole;
	}

	public StudentRole getCampRole() {

		return campRole;
	}
}