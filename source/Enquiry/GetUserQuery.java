package source.Enquiry;

import source.Entity.Enquiry;
import source.Utility.InputHandler;

import java.time.LocalDate;

public class GetUserQuery implements StudentEnquiryOperations {
    private String campName;
    private String createdBy;

    public GetUserQuery(String campName, String createdBy) {
        this.createdBy = createdBy;
        this.campName = campName;
    }

    @Override
    public void execute() {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter Title: ");
        String title = InputHandler.getString();
        System.out.print("Enter Enquiry Content: ");
        String content = InputHandler.getString();
        new Enquiry(campName, createdBy, "", content, title, "", createdDate, null);
    }
}