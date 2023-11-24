package source.Registration;
import source.Entity.Student;

public class Withdraw implements RegistrationOperations {
    private Student student;
    private Registration registration;

    public Withdraw(Student student, Registration registration){
        this.student = student;
        this.registration = registration;
    }

    @Override
    public void execute() {
        student.getRegistrations().remove(registration);
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public Student getStudent() {
        return student;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
    public Registration getRegistration() {
        return registration;
    }
}