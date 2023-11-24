package source.Enquiry;
import source.Controllers.EnquiryManager;
import source.Entity.Enquiry;

import java.util.Scanner;

public class CreateStudentEnquiry implements StudentEnquiryOperations {
    private EnquiryManager enquiryManager;

    public CreateStudentEnquiry(EnquiryManager enquiryManager){
        this.enquiryManager = enquiryManager;
    }

    public void execute(){
        Scanner createEnquiryScanner = new Scanner(System.in);

        System.out.println("Enter the title of your enquiry:\n");
        String title = createEnquiryScanner.nextLine();

        System.out.println("Enter the content of your enquiry:\n");
        String content = createEnquiryScanner.nextLine();

        Enquiry enquiry = new Enquiry(enquiryManager.getStudent(), enquiryManager.getCamp(), content, title);
        enquiryManager.getStudent().addEnquiry(enquiry);
    }

    public void setEnquiryManager(EnquiryManager enquiryManager) {
        this.enquiryManager = enquiryManager;
    }

    public EnquiryManager getEnquiryManager() {
        return enquiryManager;
    }
}
