package Source.Enquiry;
import Source.Entity.Student;

public class Delete implements EnquiryOperations {

    private Student student;
    private Enquiry enquiry;

    public Delete(Enquiry enquiry, Student student){
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