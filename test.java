import source.Controllers.EnquiryManager;
import source.Controllers.RegistrationManager;
import source.Enquiry.CreateStudentEnquiry;
import source.Entity.Camp;
import source.Enquiry.DeleteStudentEnquiry;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Faculty.Faculty;
import source.Faculty.SCSE;
import source.Entity.Registration;
import source.Registration.CreateStudentRegistration;
import source.Registration.WithdrawStudentRegistration;

public class test {
    public static void main(String[] args){
        Faculty SCSE = new SCSE();
        Student Edwin = new Student("Edwin", "aures", "gaylord", SCSE);
        Camp Kranji = new Camp("Kranji");

        System.out.println(Edwin.getIsCampCommittee());
        RegistrationManager registrationManager1 = new RegistrationManager(Edwin, Kranji);
        CreateStudentRegistration createStudentRegistration = new CreateStudentRegistration(registrationManager1);
        registrationManager1.useStudentRegistrationOperation(createStudentRegistration);


        Camp KeatHong = new Camp("Keat Hong");
        System.out.println(Edwin.getIsCampCommittee());
        RegistrationManager registrationManager2 = new RegistrationManager(Edwin, KeatHong);
        CreateStudentRegistration createStudentRegistration1 = new CreateStudentRegistration(registrationManager2);
        registrationManager2.useStudentRegistrationOperation(createStudentRegistration1);

        System.out.println(Edwin.getRegistrations());

        EnquiryManager enquiryManager = new EnquiryManager(Edwin, Kranji);
        enquiryManager.useStudentEnquiryOperation(new CreateStudentEnquiry(enquiryManager));
        System.out.println(Edwin.getEnquiries().get(0).getContent());

        EnquiryManager enquiryManager1 = new EnquiryManager(Edwin, KeatHong);
        enquiryManager1.useStudentEnquiryOperation(new CreateStudentEnquiry(enquiryManager1));
        System.out.println(Edwin.getEnquiries().get(1).getContent());

        System.out.println(Edwin.getEnquiries());

        Enquiry helloKranji = Edwin.getEnquiries().get(0);
        enquiryManager.useStudentEnquiryOperation(new DeleteStudentEnquiry(helloKranji));

        System.out.println(Edwin.getEnquiries());

        Registration RegKranji = Edwin.getRegistrations().get(0);
        registrationManager2.useStudentRegistrationOperation(new WithdrawStudentRegistration(RegKranji));

        System.out.println(Edwin.getRegistrations());
    }
}