package source.Enquiry;

import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;
import source.Entity.Student;

import java.util.Scanner;

public class AddEnquiry implements StudentEnquiryOperations {
    private EnquiryManager enquiryManager;
    private Student student;
    private Enquiry enquiry;
    public AddEnquiry(Student student, Enquiry enquiry, EnquiryManager enquiryManager) {
        this.enquiryManager = enquiryManager;
        this.student = student;
        this.enquiry = enquiry;
    }

    public void execute() {
        student.addEnquiry(enquiry);
        enquiryManager.getEnquiryDao().createEnquiry(enquiry);
    }
}
