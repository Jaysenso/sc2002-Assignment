package source.Controllers;

import source.Entity.Student;
import source.Entity.Camp;
import source.Entity.Registration;
import source.Registration.StudentRegistrationOperations;

public class RegistrationManager {
    private Student student;
    private Camp camp;
    private Registration registration;

    public RegistrationManager(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    public void useStudentRegistrationOperation(StudentRegistrationOperations studentRegistrationOperations){
        studentRegistrationOperations.execute();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Registration getRegistration() {
        return registration;
    }
}
