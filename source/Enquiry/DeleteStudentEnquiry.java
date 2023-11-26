package source.Enquiry;

import source.Entity.Enquiry;

import java.util.InputMismatchException;
import java.util.Scanner;


public class DeleteStudentEnquiry implements StudentEnquiryOperations {
    private Enquiry enquiry;

    public DeleteStudentEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    @Override
    public void execute() {
        Scanner editEnquiryScanner = new Scanner(System.in);
        do {
            System.out.println(
                    "This is the current content: "
                            + enquiry.getContent()
                            + "\nEnter the new content:"
            );
            String newContent = editEnquiryScanner.nextLine();
            enquiry.setContent(newContent);
            System.out.println("This is your new content: " + enquiry.getContent());
            System.out.println("Press 1 to confirm, or any other number to continue editing");
            try {
                int confirmation = editEnquiryScanner.nextInt();
                editEnquiryScanner.nextLine(); // consume the newline
                if (confirmation == 1) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                editEnquiryScanner.nextLine(); // consume the invalid token
            }
        } while (true);
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public Enquiry getEnquiry() {
        return enquiry;
    }
}