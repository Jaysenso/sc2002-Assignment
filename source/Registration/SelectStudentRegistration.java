package source.Registration;

import source.Controllers.RegistrationManager;
import source.Entity.Registration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectStudentRegistration implements StudentRegistrationOperations{
    private RegistrationManager registrationManager;

    public SelectStudentRegistration(RegistrationManager registrationManager){
        this.registrationManager = registrationManager;
    }

    @Override
    public void execute() {
//        Scanner selectRegistrationScanner = new Scanner(System.in);
//        do {
//            System.out.println(
//                    "These are the current registered camps under your name:\n"
//                            + registrationManager.getStudent().getRegistrations()
//                            + "\n Please select the registration you want, with the leftmost being 0:\n"
//            );
//            Registration registration = registrationManager.getStudent().getRegistrations().get(selectRegistrationScanner.nextInt());
//            System.out.println(
//                    "Is this the registration you want?\n"
//                            + "Type 1 to confirm, any other number to continue selecting\n"
//                            + registration
//            );
//            try {
//                int confirmation = selectRegistrationScanner.nextInt();
//                selectRegistrationScanner.nextLine(); // consume the newline
//                if (confirmation == 1) {
//                    registrationManager.setRegistration(registration);
//                    break;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Please enter a number.");
//                selectRegistrationScanner.nextLine(); // consume the invalid token
//            }
//        } while(true);
    }

    public void setRegistrationManager(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    public RegistrationManager getRegistrationManager() {
        return registrationManager;
    }
}
