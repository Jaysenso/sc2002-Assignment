package source.Enquiry;

import source.Entity.Enquiry;

import java.util.InputMismatchException;
import java.util.Scanner;

<<<<<<<< HEAD:source/Enquiry/EditEnquiry.java
public class EditEnquiry implements EnquiryOperations {
    private Enquiry enquiry;

    public EditEnquiry(Enquiry enquiry){
========
public class EditStudentEnquiry implements StudentEnquiryOperations {
    private Enquiry enquiry;

    public EditStudentEnquiry(Enquiry enquiry){
>>>>>>>> origin/exp-edwin:source/Enquiry/EditStudentEnquiry.java
        this.enquiry = enquiry;
    }

    @Override
    public void execute() {
<<<<<<<< HEAD:source/Enquiry/EditEnquiry.java
        Scanner EditScanner = new Scanner(System.in);
        do {
            System.out.println("This is the current content: " + enquiry.getContent() + "\nEnter the new content:");
            String newContent = EditScanner.nextLine();
========
        Scanner editEnquiryScanner = new Scanner(System.in);
        do {
            System.out.println(
                    "This is the current content: "
                    + enquiry.getContent()
                    + "\nEnter the new content:"
            );
            String newContent = editEnquiryScanner.nextLine();
>>>>>>>> origin/exp-edwin:source/Enquiry/EditStudentEnquiry.java
            enquiry.setContent(newContent);
            System.out.println("This is your new content: " + enquiry.getContent());
            System.out.println("Press 1 to confirm, or any other number to continue editing");
            try {
<<<<<<<< HEAD:source/Enquiry/EditEnquiry.java
                int confirmation = EditScanner.nextInt();
                EditScanner.nextLine(); // consume the newline
========
                int confirmation = editEnquiryScanner.nextInt();
                editEnquiryScanner.nextLine(); // consume the newline
>>>>>>>> origin/exp-edwin:source/Enquiry/EditStudentEnquiry.java
                if (confirmation == 1) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
<<<<<<<< HEAD:source/Enquiry/EditEnquiry.java
                EditScanner.nextLine(); // consume the invalid token
========
                editEnquiryScanner.nextLine(); // consume the invalid token
>>>>>>>> origin/exp-edwin:source/Enquiry/EditStudentEnquiry.java
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