package source.Enquiry;
import source.Entity.Enquiry;
import source.Entity.Student;

public class DeleteEnquiry implements EnquiryOperations {

    private Student student;
    private Enquiry enquiry;

    public DeleteEnquiry(Enquiry enquiry, Student student){
        this.enquiry = enquiry;
        this.student = student;
    }

    @Override
    public void execute(){
        student.getEnquiries().remove(enquiry);
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
}