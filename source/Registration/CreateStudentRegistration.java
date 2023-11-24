package source.Registration;

import source.Controllers.RegistrationManager;
import source.Entity.Registration;

import java.util.Scanner;

public class CreateStudentRegistration implements StudentRegistrationOperations{
    private RegistrationManager registrationManager;

    public CreateStudentRegistration(RegistrationManager registrationManager){
        this.registrationManager = registrationManager;
    }

    @Override
    public void execute() {
//        Registration registration = new Registration(registrationManager.getStudent(), registrationManager.getCamp());
//        registrationManager.getStudent().addRegistration(registration);
//
//        Scanner createRegistrationScanner = new Scanner(System.in);
//        System.out.println("Choose 0 for Student Committee | Choose 1 for Student Attendee");
//        int roleType = createRegistrationScanner.nextInt();
//
//        if (roleType == 1) {
//            registration.setCampRole(new CampAttendee());
//        } else if (roleType == 0 && !registration.getStudent().getIsCampCommittee()) {
//            registration.setCampRole(new CampCommittee());
//            registration.getStudent().setIsCampCommittee(true);
//        } else {
//            throw new IllegalArgumentException("Invalid role type: " + roleType);
//        }

    }

    public void setRegistrationManager(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    public RegistrationManager getRegistrationManager() {
        return registrationManager;
    }
}
