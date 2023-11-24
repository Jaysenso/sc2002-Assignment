package source.Enquiry;
import source.Entity.Enquiry;
import source.Entity.Student;

public class DeleteStudentEnquiry implements StudentEnquiryOperations {
    private Enquiry enquiry;

    public DeleteStudentEnquiry(Enquiry enquiry){
        this.enquiry = enquiry;
    }

    @Override
    public void execute(){

        //enquiry.getStudent().getEnquiries().remove(enquiry);
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public Enquiry getEnquiry() {
        return enquiry;
    }
}