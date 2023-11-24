package source.Entity;

public class Enquiry {

	private boolean processed = false;
	private Student student;
	private Camp camp;
	private String content;
	private String title;

	public Enquiry(Student student, Camp camp, String content, String title){
		this.student = student;
		this.camp = camp;
		this.content = content;
		this.title = title;
	}

	@Override
	public String toString() {

		return title;
	}

	/**
	 * Processed
	 */
	public void setProcessed(boolean processed) {

		this.processed = processed;
	}
	public boolean getProcessed() {

		return processed;
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
	 * Content
	 */
	public void setContent(String content) {

		this.content = content;
	}

	public String getContent() {

		return content;
	}

	/**
	 * Title
	 */
	public void setTitle(String title) {

		this.title = title;
	}

	public String getTitle() {

		return title;
	}

}