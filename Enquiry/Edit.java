package Source.Enquiry;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Edit implements EnquiryOperations {
    private Enquiry enquiry;

    public Edit(Enquiry enquiry){
        this.enquiry = enquiry;
    }

    @Override
    public void execute() {
        Scanner EditScanner = new Scanner(System.in);
        do {
            System.out.println("This is the current content: " + enquiry.getContent() + "\nEnter the new content:");
            String newContent = EditScanner.nextLine();
            enquiry.setContent(newContent);
            System.out.println("This is your new content: " + enquiry.getContent());
            System.out.println("Press 1 to confirm, or any other number to continue editing");
            try {
                int confirmation = EditScanner.nextInt();
                EditScanner.nextLine(); // consume the newline
                if (confirmation == 1) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                EditScanner.nextLine(); // consume the invalid token
            }
        } while(true);
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public Enquiry getEnquiry() {
        return enquiry;
    }
}