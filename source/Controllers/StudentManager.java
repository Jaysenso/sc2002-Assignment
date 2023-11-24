package source.Controllers;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Registration.Registration;
import source.Entity.Camp;

public class StudentManager {
    private Student student;

    public StudentManager(Student student){
        this.student = student;
    }

    public void createRegistration(Camp camp, String roleType){
        Registration registration = new Registration(student, camp, roleType);
        student.addRegistration(registration);
    }

    public void createEnquiry(Camp camp, String content, String title){
        Enquiry enquiry = new Enquiry(student, camp, content, title);
        student.addEnquiry(enquiry);
    }

}
