import Source.Camp.Camp;
import Source.Enquiry.Delete;
import Source.Enquiry.Edit;
import Source.Enquiry.Enquiry;
import Source.Enquiry.EnquiryOperations;
import Source.Entity.Student;
import Source.Faculty.Faculty;
import Source.Faculty.SCSE;
import Source.Registration.Registration;
import Source.Registration.RegistrationOperations;
import Source.Registration.Withdraw;

public class test {
    public static void main(String[] args){
        Faculty SCSE = new SCSE();
        Student Edwin = new Student("Edwin", "aures", "gaylord", SCSE);
        Camp Kranji = new Camp("Kranji");

        System.out.println(Edwin.getIsCampCommittee());
        Edwin.createRegistration(Kranji, "Camp Committee");

        Camp KeatHong = new Camp("Keat Hong");
        System.out.println(Edwin.getIsCampCommittee());
        Edwin.createRegistration(KeatHong, "Camp Attendee");

        System.out.println(Edwin.getRegistrations());

        String content = "Hello World";
        String title = "Saying hello to Kranji Camp";
        Edwin.createEnquiry(Kranji, content, title);
        System.out.println(Edwin.getEnquiries().get(0).getContent());

        content = "Bye World";
        title = "Saying bye to Kranji Camp";
        Edwin.createEnquiry(Kranji, content, title);
        System.out.println(Edwin.getEnquiries().get(1).getContent());

        System.out.println(Edwin.getEnquiries());

        Enquiry helloKranji = Edwin.getEnquiries().get(0);
        EnquiryOperations delete = new Delete(helloKranji, Edwin);
        helloKranji.UseEnquiryOperations(delete);

        System.out.println(Edwin.getEnquiries());

        Registration RegKranji = Edwin.getRegistrations().get(0);
        RegistrationOperations withdraw = new Withdraw(Edwin, RegKranji);
        RegKranji.UseRegistrationOperations(withdraw);

        System.out.println(Edwin.getRegistrations());
    }
}