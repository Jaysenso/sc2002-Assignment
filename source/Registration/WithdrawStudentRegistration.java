package source.Registration;

import source.Entity.Registration;

public class WithdrawStudentRegistration implements StudentRegistrationOperations {
    private Registration registration;

    public WithdrawStudentRegistration(Registration registration){
        this.registration = registration;
    }

    @Override
    public void execute() {
        registration.getStudent().getRegistrations().remove(registration);
    }
    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
    public Registration getRegistration() {
        return registration;
    }
}