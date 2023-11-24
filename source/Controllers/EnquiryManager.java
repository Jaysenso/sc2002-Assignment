package source.Controllers;
import source.Enquiry.StudentEnquiryOperations;
import source.Entity.Student;
import source.Entity.Enquiry;
import source.Entity.Camp;

public class EnquiryManager {
    private Student student;
    private Camp camp;
    private Enquiry enquiry;
    public EnquiryManager(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    public void useStudentEnquiryOperation(StudentEnquiryOperations studentEnquiryOperations){
        studentEnquiryOperations.execute();
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public Student getStudent() {
        return student;
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }
    public Enquiry getEnquiry() {
        return enquiry;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }
    public Camp getCamp() {
        return camp;
    }
}
