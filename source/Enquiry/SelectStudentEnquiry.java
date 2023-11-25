package source.Enquiry;

import source.Controllers.EnquiryManager;

public class SelectStudentEnquiry implements StudentEnquiryOperations {
    private final EnquiryManager enquiryManager;

    public SelectStudentEnquiry(EnquiryManager enquiryManager) {
        this.enquiryManager = enquiryManager;
    }

    public void execute() {
//        Scanner selectEnquiryScanner = new Scanner(System.in);
//        do {
//            System.out.println(
//                    "These are the current enquiries:\n"
//                    + enquiryManager.getStudent().getEnquiries()
//                    + "\n Please select the enquiry you want, with the leftmost being 0:\n"
//            );
//            Enquiry enquiry = enquiryManager.getStudent().getEnquiries().get(selectEnquiryScanner.nextInt());
//            System.out.println(
//                    "Is this the enquiry you want?\n"
//                    + "Type 1 to confirm, any other number to continue selecting\n"
//                    + enquiry
//            );
//            try {
//                int confirmation = selectEnquiryScanner.nextInt();
//                selectEnquiryScanner.nextLine(); // consume the newline
//                if (confirmation == 1) {
//                    enquiryManager.setEnquiry(enquiry);
//                    break;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Please enter a number.");
//                selectEnquiryScanner.nextLine(); // consume the invalid token
//            }
//        } while(true);
    }
}
